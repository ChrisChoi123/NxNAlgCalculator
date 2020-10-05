import math

WINGS, CENTRES = 12, 9.83285

def make_func(odd_centers: float, even_centers: "function", v1: float, v2: float) -> "function":
    """ Makes a function which calculates the number of algs. """
    def f(n: int) -> float:
        """ Actually calculates the number of algs for a NxN. """
        if n % 2 == 1:
            val = (n - 3)/2
            algs = val*(val + 1)*odd_centers + v1
        else:
            val = (n - 2)/2
            algs = val*val*even_centers(n)
        return val*WINGS + algs + v2

    return f

# chris's arctangent trick for even centers
even_centers = lambda n: (19.6657*n - 49.5021)/(2*(n - 2.1659))
chris_func = make_func(CENTRES, even_centers, 6.18, 4.12)
tom_func = make_func(10.5, lambda n: 9, 6, 4)

print("Press any letter and enter to exit the program")
while True:
    num = float(input("Enter a puzzle layer amount: "))
    print(f"  Tom's formula: {tom_func(num)}")
    print(f"Chris's formula: {chris_func(num)} ")
