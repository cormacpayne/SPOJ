from math import log

c = int(raw_input())
for i in range(1, c+1):
    p0, pt, t, p = [int(x) for x in raw_input().split()]
    k = log(float(pt)/p0)/t
    print("Scenario #%d: %.2f")%(i, log(float(p)/p0)/k) 
