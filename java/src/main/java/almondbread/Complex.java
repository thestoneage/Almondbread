package almondbread;

final class Complex {
    final static Complex ZERO = new Complex();
    final double real;
    final double imag;
    final double abs;

    Complex(final double real, final double imag) {
        this.real = real;
        this.imag = imag;
        this.abs = Math.sqrt(real * real + imag * imag);
    }

    Complex(final double real) {
        this.real = real;
        this.imag = 0;
        this.abs = real;
    }

    Complex() {
        this.real = 0;
        this.imag = 0;
        this.abs = 0;
    }

    final Complex add(final Complex other) {
        return new Complex(real + other.real, imag + other.imag);
    }

    final Complex add(final double other) {
        return new Complex(real + other, imag);
    }

    final Complex mul(final Complex other) {
        return new Complex(real * other.real - imag * other.imag,
                imag * other.real + real * other.imag);
    }
}
