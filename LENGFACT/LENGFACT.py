from math import floor, log, pi, e

t = int(raw_input())
while t:
	t-=1
	n = int(raw_input())
	if n == 0 or n == 1:print "1"
	else:print int(floor((log(2*pi*n, e)/2 + n*(log(n, e)-1))/log(10, e))+1)
