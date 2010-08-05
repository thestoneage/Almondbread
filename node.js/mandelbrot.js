var sys = require('sys');

function Complex(r, i) {
    this.real = r;
    this.imag = i;
}

Complex.add = function (a, b) {
    return new Complex(a.real + b.real, a.imag + b.imag)
}

Complex.mul = function (a, b) {
    return new Complex(a.real * b.real - a.imag * b.imag, a.real * b.imag + a.imag * b.real);
}

Complex.abs = function (c) {
    return Math.sqrt(c.real * c.real + c.imag * c.imag);
}

function escape_time(c) {
    var step = 0,
        z = new Complex(0, 0);
    while (step < 256 && Complex.abs(z) < 2) {
        z = Complex.add(Complex.mul(z, z), c);
        step += 1;
    }
    return step
}

function each_point(width, height, func) {
    var sr = 3.0 / width,
        si = 2.0 / height;
    for (var y = 0; y < height; y += 1) {
        for (var x = 0; x < width; x += 1) {
            func(x, y, escape_time(new Complex(-2.0 + sr * x, -1.0 + si * y)));
        }
    }
}

each_point(80, 24, function(x, y, v) {
    if (x == 0)
        sys.print("\n");
    if (v < 256)
        sys.print("#");
    else
        sys.print(" ");
});
sys.print("\n");

