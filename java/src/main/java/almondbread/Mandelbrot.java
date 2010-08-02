package almondbread;

final class Mandelbrot {
    private static final int width = 80;
    private static final int height = 20;

    public static final void main(String... args) {
        eachPoint(width, height, new Action3IntIntShort() {
                public void apply(final int x, final int y, final short v) {
                    if (x == 0) {
                        System.out.println();
                    }
                    System.out.print(v < 255 ? "-" : "#");
                }
        });
        System.out.println();
        printMandelSet(width, height);
        System.exit(0);
    }

    private static final short escapeTime(final Complex c) {
        short step = 0;
        Complex z = Complex.ZERO;
        while (step++ < 256 && z.abs < 4) {
            z = z.mul(z).add(c);
        }
        return step;
    }

    private static final short escapeTime2(final Complex c) {
        escapeTime2Recursive(c, Complex.ZERO, 0)
    }

    private static final short escapeTime2Recursive(final Complex c, final Complex z, final short step) {
        if (step > 256 || z.abs > 4) {
            return step;
        }
        return escapeTime2Recursive(c, z.mul(z).add(c), (short)(step + 1));
    }

    private static final void printMandelSet(final int width, final int height) {
        for (double i = -1.0; i < 1.0; i += 2.0 / height) {
            for (double r = -2.0; r < 0.5; r += 2.5 / width) {
                System.out.print(escapeTime2(new Complex(r, i)) < 255 ? "*" : " ");
            }
            System.out.println();
        }
    }

    static final void eachPoint(final int width, final int height, final Action3IntIntShort func) {
        double r = -2.0;
        double i = -1.0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                func.apply(x, y, escapeTime2(new Complex(r, i)));
                r += 3.0 / width;
            }
            r = -2.0;
            i += 2.0 / height;
        }
    }
}
