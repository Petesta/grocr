import net.litola.SassPlugin

name := "grocr"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)

play.Project.playScalaSettings ++ SassPlugin.sassSettings
