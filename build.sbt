name := "play2"

version := "1.0"

lazy val `play2` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test, "com.typesafe.play" %% "anorm" % "2.5.0", "mysql" % "mysql-connector-java" % "6.0.4" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  