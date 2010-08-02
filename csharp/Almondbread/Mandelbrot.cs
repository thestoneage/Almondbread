using System;

namespace Almondbread
{
    internal sealed class Mandelbrot
    {
        private const int width = 80;
        private const int height = 20;

        private static void Main(string[] args)
        {
            EachPoint(width, height, (x, y, v) =>
            {
                if (x == 0)
                {
                    Console.WriteLine();
                }
                Console.Write(v < 255 ? "-" : "#");
            });
            Console.WriteLine();
            PrintMandelSet();
        }

        private static short EscapeTime(Complex c)
        {
            short step = 0;
            var z = Complex.ZERO;
            while (step++ < 256 && z < 4)
            {
                z = z * z + c;
            }
            return step;
        }

        private static short EscapeTime2(Complex c)
        {
            return EscapeTime2Recursive(c, Complex.ZERO);
        }

        private static short EscapeTime2Recursive(Complex c, Complex z, short step = 0)
        {
            if (step > 256 || z > 4)
            {
                return step;
            }
            return EscapeTime2Recursive(c, z * z + c, (short)(step + 1));
        }

        private static void PrintMandelSet(int width = width, int height = height)
        {
            for (double i = -1.0; i < 1.0; i += 2.0 / height)
            {
                for (double r = -2.0; r < 0.5; r += 2.5 / width)
                {
                    Console.Write(EscapeTime2(new Complex(r, i)) < 255 ? "*" : " ");
                }
                Console.WriteLine();
            }
        }

        internal static void EachPoint(int width, int height, Action<int, int, short> func)
        {
            var r = -2.0;
            var i = -1.0;
            for (var y = 0; y < height; y++)
            {
                for (var x = 0; x < width; x++)
                {
                    func(x, y, EscapeTime2(new Complex(r, i)));
                    r += 3.0 / width;
                }
                r = -2.0;
                i += 2.0 / height;
            }
        }
    }
}
