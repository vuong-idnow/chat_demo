name := "untitled"
 
version := "1.0" 
      
lazy val `untitled` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += "local maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies ++= Seq(
  "org.atmosphere" % "atmosphere-play" % "2.3.1-SNAPSHOT",
  "com.typesafe.play" %% "play-java" % "2.6.20",
  "com.typesafe.play" % "play-enhancer" % "1.2.2"

)

