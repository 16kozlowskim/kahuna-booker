name := "kahuna-booker"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium" % "selenium-java" % "4.1.0",
  "com.github.pureconfig" %% "pureconfig" % "0.17.1",
  "org.typelevel" %% "cats-effect" % "3.3.0",
  "org.typelevel" %% "cats-core" % "2.3.0"
)
