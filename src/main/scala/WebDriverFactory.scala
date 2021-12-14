import cats.effect.IO
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.safari.{SafariDriver, SafariOptions}

import scala.util.Try


object WebDriverFactory {

  def chromeDriver(): IO[ChromeDriver] = {
    IO {
      throw new Exception("dupa")
      new ChromeDriver()
    }
  }
  def safariDriver(): IO[SafariDriver] = {
    IO(new SafariDriver())
  }

}