from math import tan, acos, cos

s, N = [2*int(x) for x in raw_input().split()]
while True:
    if s == 0 and N == 0:break
    else:
        a = s/(2*tan(acos(-1)/N))
        print("%.2f")%(a/cos(acos(-1)/N))
        s, N = [2*int(x) for x in raw_input().split()]
