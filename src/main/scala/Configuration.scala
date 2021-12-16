import pureconfig._
import pureconfig.generic.auto._
import scala.concurrent.duration.{DurationInt, FiniteDuration}

case class Configuration(schedule: Schedule,
                         kahunaURL: String,
                         retryTime: Int)

case class Schedule(monday: List[Int],
                    tuesday: List[Int],
                    wednesday: List[Int],
                    thursday: List[Int],
                    friday: List[Int],
                    saturday: List[Int],
                    sunday: List[Int])

