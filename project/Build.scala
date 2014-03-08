import sbt._
import Keys._

object BuildSettings
{
	val lwjglVersion = "2.9.0"

	val buildSettings = Defaults.defaultSettings ++ Seq(
			organization := "w3d.cc",
			version      := "0.0.1",
			scalaVersion := "2.10.1",
			scalacOptions ++= Seq()
	)

	lazy val lwjgl = LWJGLPlugin.lwjglSettings ++ Seq(
			LWJGLPlugin.lwjgl.version := lwjglVersion)
}

object MyBuild extends Build
{
	val projectName = "Warp"
	def name(module: String) = projectName + "_" + module
	import BuildSettings._


	lazy val root: Project = Project(
		projectName,
		file("."),
		settings = buildSettings ++ lwjgl
	)  dependsOn (maths, graph, mecha)

	lazy val maths: Project = Project(
		name ("Math"),
		file("modules/math"),
		settings = buildSettings
	)

	lazy val graph: Project = Project(
		name ("Graph"),
		file("modules/graph"),
		settings = buildSettings ++ lwjgl
	)  dependsOn maths

	lazy val mecha: Project = Project(
		name ("Mecha"),
		file("modules/mecha"),
		settings = buildSettings ++ lwjgl
	)  dependsOn graph

}