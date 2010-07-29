package sandelbrot

import scala.swing._
import event._


object MandelbrotGUI extends scala.swing.SimpleSwingApplication {
  val image = new java.awt.image.BufferedImage(641, 481, java.awt.image.BufferedImage.TYPE_INT_RGB)
  def top = new scala.swing.MainFrame {
    title = "Mandelbrot"
    val button = new Button {
      text = "Start"
    } 
    val panel = new Panel {
      override def paint(g:scala.swing.Graphics2D) = {
        g.drawImage(image, 0, 0, null)
      }
      preferredSize = new Dimension(640, 480)
    }
    contents = new BorderPanel() {
      add(panel, BorderPanel.Position.Center)
      add(button, BorderPanel.Position.South)
    }
    listenTo(button)
    reactions += {
      case ButtonClicked(b) =>
        Mandelbrot.each_point(640, 480, (x, y, v) => {
          image.setRGB(x, y, v + (v << 8) + (v << 16))
        })
        panel.repaint
        
    }
  }
}