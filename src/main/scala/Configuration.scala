import pureconfig._
import pureconfig.generic.auto._

case class Configuration(schedule: Schedule,
                         kahunaURL: String)

case class Schedule(monday: List[Int],
                    tuesday: List[Int],
                    wednesday: List[Int],
                    thursday: List[Int],
                    friday: List[Int],
                    saturday: List[Int],
                    sunday: List[Int])

