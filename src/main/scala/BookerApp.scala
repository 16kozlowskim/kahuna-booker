
import cats.MonadError
import cats.effect.IO
import org.openqa.selenium.WebDriver
import pureconfig._
import pureconfig.generic.auto._

import scala.util.Try
import cats.implicits._
import cats.data.EitherT
import cats.MonadError
import cats.effect.unsafe.implicits.global
import org.openqa.selenium.chrome.ChromeDriver
import pureconfig.error.ConfigReaderFailures

import java.lang.Exception
import scala.util.control.Exception

object BookerApp extends App {

  val program = for {
    conf <- IO(ConfigSource.default.loadOrThrow[Configuration])
    _ <-
      WebDriverFactory.chromeDriver()
        .bracket { driver =>
          Kahuna.book(driver, conf.kahunaURL, conf.schedule)
        } (driver => IO(driver.close()))
  } yield ()

  program.unsafeRunSync()

}
