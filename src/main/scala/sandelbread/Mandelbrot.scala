package sandelbread

object Mandelbrot {
  private val width = 80
  private val height = 20

  def main(args: Array[String]) {
    
    eachPoint(width, height, (x, y, v) => {
        if (x==0) println
        if (v < 255) print("-") else print("#")
    })
  }
  
  def escapeTime(c: Complex) = {
    var step = 0
    var z = Complex()
    while (step < 256 && z.abs < 4) {
      z = (z * z) + c
      step += 1
    }
    step
  }
  
  def printMandelSet(width:Int = width, height:Int = height) {
    for (i <- -1.0 to 1.0 by 2.0/height) {
      for (r <- -2.0 to 1.0 by 3.0/width)
        print(if (escapeTime(Complex(r,i)) < 255) '-' else '*')
      println
    }
  }
  // More Functional approach
  def eachPoint(width:Int = width, height:Int = height, func: (Int, Int, Int) => Unit) {
    val rs = 3.0/width
    val is = 2.0/height
    for ((ir, ii) <- (0 until height).map(y => (-1.0 + is * y, y)))
        for ((rr, ri) <- (0 until width).map(x => (-2.0 + rs * x, x)))
            func(ri, ii, escapeTime(Complex(rr, ir)))
  }

  // More Procedural
  def eachPoint2(width:Int = width, height:Int = height, func: (Int, Int, Int) => Unit) {
    var r = -2.0
    var i = -1.0
    for (y <- 0 until height) {
        for (x <- 0 until width) {
            func(x, y, escapeTime(Complex(r, i)))
            r += (3.0/width)
        }
      r = -2.0
      i += (2.0/height)
    }
  }  

  // Nested Iteration
  def eachPoint3(width:Int = width, height:Int = height, func: (Int, Int, Int) => Unit) {
    val rs = 3.0/width
    val is = 2.0/height
    for ((ir, ii) <- (0 until height).map(y => (-1.0 + is * y, y));
         (rr, ri) <- (0 until width ).map(x => (-2.0 + rs * x, x))
    ) func(ri, ii, escapeTime(Complex(rr, ir)))
  }
  
}
