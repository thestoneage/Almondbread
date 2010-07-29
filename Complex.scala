package sandelbrot

case class Complex (protected val real: Double, protected val imag: Double){
  def +(that: Complex) = Complex(real + that.real, imag + that.imag)
  def +(that: Double) = Complex(real + that, imag)
  def *(that: Complex) = Complex(real * that.real - imag * that.imag,
        imag*that.real + real * that.imag)
  val abs = scala.math.hypot(real, imag)
}
