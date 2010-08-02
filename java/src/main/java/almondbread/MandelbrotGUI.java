package almondbread;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;

final class MandelbrotGUI extends JPanel {
    private static final int width = 640;
    private static final int height = 480;
    private static final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

    public static final void main(String... args) {
        final JFrame frame = new JFrame("Mandelbrot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MandelbrotGUI());
        frame.pack();
        frame.setVisible(true);

        Mandelbrot.eachPoint(width, height, new Action3IntIntShort() {
                public void apply(final int x, final int y, final short v) {
                    image.setRGB(x, y, (byte)v);
                    frame.repaint();
                }
        });
    }

    @Override
    public final void paint(final Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public final Dimension getPreferredSize() {
        return new java.awt.Dimension(image.getWidth(), image.getHeight());
    }
}
