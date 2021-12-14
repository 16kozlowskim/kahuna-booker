import cats.effect.IO
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.{SafariDriver, SafariOptions}

import scala.util.Try


object WebDriverFactory {

  def chromeDriver(): IO[ChromeDriver] = {
    IO(new ChromeDriver())
  }
  def safariDriver(): IO[SafariDriver] = {
    IO(new SafariDriver())
  }
  def firefoxDriver(): IO[FirefoxDriver] = {
    IO(new FirefoxDriver())
  }

}