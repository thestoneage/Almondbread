package sandelbrot

class Complex (val real: Double, val imag: Double){
  def +(that: Complex) = new Complex(real + that.real, imag + that.imag)
  def +(that: Double) = new Complex(real + that, imag)
  def *(that: Complex) = new Complex(real * that.real - imag * that.imag,
        imag*that.real + real * that.imag)
  def abs = scala.math.hypot(real, imag)
}

