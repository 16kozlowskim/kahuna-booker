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
  @tailrec
  def tryIOFor[A](fa: IO[A])(duration: FiniteDuration): IO[A] = {
    if (duration <= 0) fa
    else {
      val delay = duration min 1.second
      fa.delayBy(delay).handleErrorWith(_ => tryIOFor(fa)(duration - delay))
    }
  }

  def book(driver: WebDriver, kahunaURL: String, schedule: Schedule): IO[Unit] = {

    for {
      _ <- IO {driver.get(kahunaURL)}
      _ <-

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