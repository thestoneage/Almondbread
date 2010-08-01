import sbt._

class AlmondbreadProject(info: ProjectInfo) extends DefaultProject(info) {
  override def mainClass = Some("almondbread.MandelbrotGUI")
  val swing = "org.scala-lang" % "scala-swing" % "2.8.0"
}
