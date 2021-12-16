import cats.{Monad, MonadError}
import cats.data.OptionT
import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.By._
import org.openqa.selenium.support.ui.FluentWait
import cats.effect._
import cats.effect.implicits._

import java.time._
import scala.annotation.tailrec
import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success, Try}



object Kahuna {
  def retryIOFor[A](fa: IO[A])(duration: FiniteDuration): IO[A] = {
    if (duration <= 0) fa
    else {
      val delay = duration min 1.second
      fa.delayBy(delay).attempt.flatMap {
        case Left(_) => retryIOFor(fa)(duration - delay)
        case Right(a) => IO(a)
      }
    }
  }

  def book(driver: WebDriver, kahunaURL: String, schedule: Schedule, retryTime: Int): IO[Unit] = {

    val retryTimeSeconds = retryTime.seconds
    for {
      _ <- IO(driver.get(kahunaURL))
      _ <- retryIOFor(IO(driver.switchTo().frame(0)))(retryTimeSeconds)
      onCalTabs <- retryIOFor(IO(driver.findElement(new ById("online-calendar-tabs"))))(retryTimeSeconds)
      badminton <- retryIOFor(IO(onCalTabs.findElement(new ByLinkText("Badminton"))))(retryTimeSeconds)
      _ <- retryIOFor(IO(badminton.click()))(retryTimeSeconds)
      onCalTable <- retryIOFor(IO(driver.findElement(new ById("online-calendar-table"))))(retryTimeSeconds)
      table <- retryIOFor(IO(onCalTable.findElement(new ByCssSelector("tbody"))))(retryTimeSeconds)
      tableRows <- retryIOFor
    } yield ()
    IO {
      driver.get(kahunaURL)
      driver.switchTo().frame(0)
      driver
        .findElement(new ById("online-calendar-tabs"))
        .findElement(new ByLinkText("Badminton"))
        .click()
      val table = driver
        .findElement(new ById("online-calendar-table"))
        .findElement(new ByCssSelector("tbody"))
      val tableRows = table.findElements(new ByCssSelector("tr")).asScala.toList
      val tableRowMajor = tableRows.map(_.findElements(new ByCssSelector("td")).asScala.toList)
      val tableColMajor = Functions.rowMajorToMinor(tableRowMajor)

      print(ZonedDateTime.now(ZoneId.of("CET")).getHour)
    }
  }
}