package almondbread

import scala.swing._

object MandelbrotGUI extends scala.swing.SimpleSwingApplication {
  private val width = 640
  private val height = 480

  private val image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_BYTE_BINARY)

  def top = new scala.swing.MainFrame {
    title = "Mandelbrot"
    contents = new Panel {
      override def paint(g:scala.swing.Graphics2D) = {
        super.paint(g)
        g.drawImage(image, 0, 0, null)
      }
      preferredSize = new Dimension(width, height)
    }
  }

  override def startup(args: Array[String]) = {
    super.startup(args)
    Mandelbrot.eachPoint(width, height, (x, y, v) => {
        image.setRGB(x, y, v.toByte)
      })
  }
}
