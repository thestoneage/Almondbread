package sandelbread

import scala.swing._
import event._

object MandelbrotGUI extends scala.swing.SimpleSwingApplication {
  private val width = 640
  private val height = 480

  private val image = new java.awt.image.BufferedImage(width,height, java.awt.image.BufferedImage.TYPE_INT_RGB)

  def top = new scala.swing.MainFrame {
    title = "Mandelbrot"
    val button = new Button {
      text = "Start"
    }
    val panel = new Panel {
      override def paint(g:scala.swing.Graphics2D) = {
        g.drawImage(image, 0, 0, null)
      }
      preferredSize = new Dimension(width, height)
    }
    contents = new BorderPanel {
      add(panel, BorderPanel.Position.Center)
      add(button, BorderPanel.Position.South)
    }
    listenTo(button)
    reactions += {
      case ButtonClicked(b) =>
        Mandelbrot.eachPoint(width, height, (x, y, v) => {
            image.setRGB(x, y, v + (v << 8) + (v << 16))
          })
        panel.repaint
    }
  }
}
