package sandelbrot

class Complex (val real: Double, val imag: Double){
  def +(that: Complex):Complex = new Complex(real + that.real, imag + that.imag)
  def +(that: Double): Complex = new Complex(real + that, imag)
  def *(that: Complex):Complex = new Complex(real * that.real - imag * that.imag,
        imag*that.real + real * that.imag)
  def abs(): Double = scala.math.hypot(real, imag)
}

