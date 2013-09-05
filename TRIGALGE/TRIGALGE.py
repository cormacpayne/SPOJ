from math import sin

t = int(raw_input())
while t:
    t-=1
    a,b,c = [float(x) for x in raw_input().split()]
    mid = 0.0
    low = 0.0
    high = float(c)
    e = 0.0000001
    
    while high-low > e:
        mid = (high+low)/2
        
        if(a*mid + b*sin(mid) > c):high = mid
        else:low = mid
        
    print"%.6f"%(mid)
