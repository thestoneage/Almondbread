import sbt._

class SandelbreadProject(info: ProjectInfo) extends DefaultProject(info) {
  override def mainClass = Some("sandelbread.MandelbrotGUI")
  val swing = "org.scala-lang" % "scala-swing" % "2.8.0"
}
