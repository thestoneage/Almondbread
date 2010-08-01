require 'rman'
require 'java'

include_class javax.swing.JFrame
include_class java.awt.image.BufferedImage

class ImagePanel < javax.swing.JPanel
    def initialize(image)
        super()
        @image = image
    end

    def paint(g)
        g.draw_image(@image, 0, 0, self)
    end

    def getPreferredSize()
        java.awt.Dimension.new(@image.width, @image.height)
    end
end

image = BufferedImage.new(640, 480, BufferedImage::TYPE_INT_RGB)
frame = JFrame.new("Mandelbrot")
frame.content_pane.add(ImagePanel.new(image))
frame.pack
frame.show(true)


Mandelbrot.each_point(ComplexRange.new((-2.0..0.5), (-1.0..1.0)), Resolution.new(image.width, image.height)) { |value, x, y|
    image.setRGB(x, y, value + (value << 8) + (value << 16))
    frame.repaint
}

