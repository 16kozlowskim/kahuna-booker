
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
    conf <- EitherT.fromEither[IO](ConfigSource.default.load[Configuration])
    driver <- EitherT.right[ConfigReaderFailures](WebDriverFactory.safariDriver())
    driver2 <- EitherT.right[ConfigReaderFailures](WebDriverFactory.chromeDriver())
    //_ <- EitherT.right(Kahuna.book(driver, conf.kahunaURL, conf.schedule))
  } yield driver.close()

  val meEitherT = MonadError[EitherT[IO, ConfigReaderFailures, *], ConfigReaderFailures]
  val meIO = MonadError[EitherT[IO, ConfigReaderFailures, *], Throwable]

  //meIO.handleError(program)(_.printStackTrace()).value.unsafeRunSync()
  meEitherT.handleError(program)().value.unsafeRunSync()
}
