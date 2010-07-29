package sandelbrot

import scala.collection.immutable.Range.Double

object Mandelbrot {

  def main(args: Array[String]) {
    
    each_point(80, 20, (x:Int, y:Int, v:Int) => {
        if (x==0) println
        if (v < 255) print("-") else print("#")
    })
  }
  
  def escapeTime(c: Complex) = {
    var step = 0
    var z = new Complex(0.0, 0.0)
    while (step < 256 && z.abs < 4) {
      z = (z * z) + c
      step += 1
    }
    step
  }
  
  def printMandelSet() {
    Range.Double(-1.0, 1.0, 2.0/20.0).foreach(i => {
      Range.Double(-2.0, 1.0, 3.0/80.0).foreach(r => 
        print(if (escapeTime(new Complex(r,i)) < 255) '-' else '*'))
        println
      })
  }
  // More Functional approach
  def each_point(width:Int, height:Int, func: (Int, Int, Int) => Unit) {
    val rs = 3.0/width
    val is = 2.0/height
    Range.Int(0, height, 1).map(y => (-1.0 + is * y, y)).foreach((i) => {
        Range.Int(0, width, 1).map(x => (-2.0 + rs * x, x)).foreach((r) => {
            func(r._2, i._2, escapeTime(new Complex(r._1, i._1)))
      })
    })
  }

  // More Procedural
  def foreach_point(width:Int, height:Int, func: (Int, Int, Int) => Unit) {
    var r = -2.0
    var i = -1.0
    Range.Int(0, height, 1).foreach((y:Int) => {
        Range.Int(0, width, 1).foreach((x:Int) => {
            func(x, y, escapeTime(new Complex(r, i)))
            r += (3.0/width)
      })
      r = -2.0
      i += (2.0/height)
    })
  }  


}
