import cats.Monad
import cats.data.OptionT
import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.By._
import org.openqa.selenium.support.ui.FluentWait
import cats.effect._
import cats.effect.implicits._
import java.time._
import scala.annotation.tailrec
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success, Try}



object Kahuna {

  def book(driver: WebDriver, kahunaURL: String, schedule: Schedule): Unit = {

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