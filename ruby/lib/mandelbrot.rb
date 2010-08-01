require 'complex' unless defined? Complex

module Almondbread
  class << (Mandelbrot = Object.new)
    def escape_time(c)
      z, step = Complex(0, 0), 0
      while step < 256 && z.abs < 2
        z = z * z + c
        step += 1
      end

      return step
    end

    #Print a mandelbrot set to stdout
    def print_set(width = 80, height = 24)
      (-1.0..1.0).step(2.0/(height - 1)).each do |y|
        (-2.0..0.5).step(2.5/(width - 1)) do |x|
          print escape_time(Complex(x, y)) < 255 ? '*' : ' '
        end
        puts
      end
    end

    def each_point(crange, resolution)
      r, i = crange.real.first, crange.imaginary.first
      step_r, step_i = crange.real.length / resolution.width, crange.imaginary.length / resolution.height
      resolution.height.times do |y|
        resolution.width.times do |x|
          yield escape_time(Complex(r, i)), x, y
          r += step_r
        end
        r = crange.real.first
        i += step_i
      end
    end
  end

  class ComplexRange
    attr_reader :real, :imaginary

    def initialize(real, imaginary)
      @real, @imaginary = real, imaginary
    end
  end

  class Resolution
    attr_reader :width, :height

    def initialize(width, height)
      @width, @height = width, height
    end
  end
end

class Range
  def length
    last - first
  end
end
