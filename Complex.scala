package sandelbrot

case class Complex (val real: Double, val imag: Double){
  def +(that: Complex) = Complex(real + that.real, imag + that.imag)
  def +(that: Double) = Complex(real + that, imag)
  def *(that: Complex) = Complex(real * that.real - imag * that.imag,
        imag*that.real + real * that.imag)
  def abs = scala.math.hypot(real, imag)
}
