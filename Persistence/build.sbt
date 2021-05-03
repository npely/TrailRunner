val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.4"
val commonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scala-lang.modules" %% "scala-swing" % "2.1.1",
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.google.inject" % "guice" % "4.1.0",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.slf4j" % "slf4j-nop" % "1.7.30",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"
)

lazy val model = ProjectRef(uri("https://github.com/npely/TrailRunner.git#rest"), "model")
lazy val persistence = (project in file(".")).dependsOn(model).aggregate(model).settings(
  name          := "TrailRunner-Persistence",
  organization  := "de.htwg.se",
  version       := "0.1",
  scalaVersion  := "2.13.3",
  libraryDependencies ++= commonDependencies,
)