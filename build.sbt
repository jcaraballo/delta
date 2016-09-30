import sbt._

import sbt.Keys._

name := "delta"


lazy val delta = project.in(file("."))
  .aggregate(core, cats, scalaz, generic, argonaut, matchers)
  .settings(commonSettings: _*)

lazy val core = project.in(file("core"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.3")

lazy val cats = project.in(file("cats"))
  .settings(commonSettings: _*)
  .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1"))
  .settings(libraryDependencies += "org.typelevel" %% "cats" % "0.4.1")
  .dependsOn(core % "compile -> compile; test -> test")

lazy val scalaz = project.in(file("scalaz"))
  .settings(commonSettings: _*)
  .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1"))
  .settings(libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.2")
  .dependsOn(core % "compile -> compile; test -> test")

lazy val generic = project.in(file("generic"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies += "com.chuusai" %% "shapeless" % "2.1.0")
  .dependsOn(core % "compile -> compile; test -> test")

lazy val argonaut = project.in(file("argonaut"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies += "io.argonaut" %% "argonaut" % "6.2-M2")
  .dependsOn(core % "compile -> compile; test -> test")

lazy val matchers = project.in(file("matchers"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4")
  .settings(libraryDependencies += "io.argonaut" %% "argonaut" % "6.2-M2")
  .dependsOn(core % "compile -> compile; test -> test")
  .dependsOn(argonaut % "compile -> compile; test -> test")

lazy val commonSettings = Seq(
  organization := "com.github.stacycurl",
  scalaVersion := "2.11.7",
  maxErrors := 1,
  parallelExecution in Test := true,
  scalacOptions := Seq("-feature", "-Xfatal-warnings", "-deprecation", "-unchecked"),
  libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
