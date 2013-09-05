from math import sqrt

t = int(raw_input())
while t:
    t-=1
    a,b,c,d = (float(x) for x in raw_input().split())
    s = 0.5*(a+b+c+d)
    print"%.2f"%(sqrt((s-a)*(s-b)*(s-c)*(s-d)))
