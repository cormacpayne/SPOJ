from math import sqrt

k = int(raw_input())
while k:
	k-=1
	x,y,z = [int(x) for x in raw_input().split()]
	n = int(sqrt(x*x*z*z + y*y*z*z + 2*y*z*z*z))
	status = False
	t = -1
	top = x*z + n
	bottom = y + 2*z
	
	if top%bottom == 0:
		status = True
		t = top/bottom
	
	if status:print t
	else:print "Not this time."
