using System;

namespace Almondbread
{
    internal struct Complex
    {
        internal static Complex ZERO = new Complex();
        private double real;
        private double imag;
        private double abs;

        internal Complex(double real = 0, double imag = 0)
        {
            this.real = real;
            this.imag = imag;
            this.abs = Math.Sqrt(real * real + imag * imag);
        }

        public static Complex operator +(Complex a, Complex b)
        {
            return new Complex(a.real + b.real, a.imag + b.imag);
        }

        public static Complex operator +(Complex a, double b)
        {
            return new Complex(a.real + b, a.imag);
        }

        public static Complex operator +(double b, Complex a)
        {
            return new Complex(a.real + b, a.imag);
        }

        public static Complex operator *(Complex a, Complex b)
        {
            return new Complex(a.real * b.real - a.imag * b.imag,
                    a.imag * b.real + a.real * b.imag);
        }

        public static implicit operator double(Complex c)
        {
            return c.abs;
        }
    }
}
