name := """RumataFirstBot"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "com.github.mukel" %% "telegrambot4s" % "v1.2.2"
)

herokuAppName in Compile := "rumata-first-telegram-bot"

enablePlugins(JavaAppPackaging)
