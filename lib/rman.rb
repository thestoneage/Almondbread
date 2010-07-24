require 'complex'

module Mandelbrot
    def self.escape_time(c)
        z, step = 0, 0
        while (step < 256 and z.abs < 2)
            z = z * z + c
            step += 1
        end

        return step
    end

    #Print a mandelbrot set to std_out
    def self.print_set(width = 80, height = 24)
        (-1.0..1.0).step(2.0/(height - 1)).each do |y|
            (-2.0..0.5).step(2.5/(width - 1)) do |x|
                print escape_time(Complex(x, y)) < 256 ? '*' : ' '
            end
            puts
        end
    end

    def self.each_point(width, height)
        ((-1.0..1.0).step(2.0/(height - 1))).with_index do |b, y|
            ((-2.0..0.5).step(2.5/(width - 1))).with_index do |a, x|
                yield escape_time(Complex(a,b)), x, y
            end
        end
    end

end

Mandelbrot::each_point(10, 10) { |value, x, y| puts "x: #{x} y: #{y} value: #{value}"}
