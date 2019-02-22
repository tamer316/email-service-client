libraryDependencies ++= {
  val playV = "2.5.12"
  Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "org.clapper" %% "grizzled-slf4j" % "1.3.0",
    "net.codingwell" %% "scala-guice" % "4.0.1",
    "com.typesafe.play" % "play-ws_2.11" % playV,
    "org.specs2" % "specs2-core_2.11" % "3.8.8" % Test,
    "org.specs2" % "specs2-mock_2.11" % "3.8.8" % Test,
    "com.typesafe.play" % "play-server_2.11" % playV % Test,
    "com.typesafe.play" % "play-netty-server_2.11" % playV % Test
  )
}

Revolver.settings