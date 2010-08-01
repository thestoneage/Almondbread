package almondbread

case class Complex (protected val real: Double = 0, protected val imag: Double = 0){
  def +(that: Complex) = Complex(real + that.real, imag + that.imag)
  def +(that: Double) = Complex(real + that, imag)
  def *(that: Complex) = Complex(real * that.real - imag * that.imag,
                                 imag*that.real + real * that.imag)
  lazy val abs = scala.math.hypot(real, imag)
}
