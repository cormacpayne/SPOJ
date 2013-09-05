from math import asin, acos

t = int(raw_input())
for i in range(1, t+1):
	t-=1
	d,v = [int(x) for x in raw_input().split()]
	if d*9.806/v**2 > 1:
		print "Scenario #%d: -1"%(i)
	else:
		print "Scenario #%d: %.2f"%(i, asin(d*9.806/v**2)*180/(4*acos(0.0)))
