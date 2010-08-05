import sys

def escape_time(c):
    z = complex(0, 0)
    step = 0
    while (step < 256) and (abs(z) < 4):
        z = z * z + c
        step += 1
    return step

def mandelset(width, height):
    sr = 3.0 / width
    si = 2.0 / height
    str = ""
    return [(x, y, escape_time(complex(-2.0 + sr * x, -1.0 + si * y))) for y in range(height)
                                                                            for x in range(width)]

def print_mandelset(width = 80, height = 24):
    for (x, y, v) in mandelset(width, height):
        if (x == 0): sys.stdout.write("\n")
        if (v < 256): sys.stdout.write("#")
        else:         sys.stdout.write(" ")

    sys.stdout.write("\n")

if __name__ == "__main__":
    print_mandelset()






