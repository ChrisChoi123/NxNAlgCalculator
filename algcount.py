import math
wings = 12
centres = 9.75
print("Press any letter and enter to exit the program")
def changeCen(i):
    centres = i
def changeWin(i):
    wings = i
def func(n):
    if (n%2 == 1):
        val = (n-3)/2
        algs = val*wings+(val*(val+1))*centres+4.12+6.18
        return(algs)
    else:
        val = (n-2)/2
        algs = val*wings+(val*val)*((16+2*3.5/math.pi*math.atan(.87566*n-4)))/2+4.12
        return(algs)
def Tomfunc(n):
    if (n%2 == 1):
        algs = ((((n-3)/2)*24)+(((((n-2)*(n-2))-1)*6)*.875)+(8)+(12))/2
    else:
        algs = ((((n-2)/2)*24)+((((n-2)*(n-2))*6)*.75)+(8))/2
    return(algs)
while True:
    num = input("Enter a puzzle layer amount: ")
    print("Tom's formula: ")
    print(Tomfunc(float(num)))
    print("Chris's formula: ")
    print(func(float(num)))
