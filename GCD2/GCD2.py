def gcd(a,b):
    while b != 0:
        temp = b
        b = a%b
        a = temp
    return a
    
t = int(input())
while t:
    t-=1
    a,b = [int(x) for x in input().split()]
    print(gcd(a,b))
