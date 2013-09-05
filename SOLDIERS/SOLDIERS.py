t = int(raw_input())
while t:
	t-=1
	m,n = [int(x) for x in raw_input().split()]
	if m%2 == 0 and n%2 == 0: print m*n/2
	elif m%2 != 0 and n%2 != 0:
		if m > n: print m*(n/2+1)
		else: print n*(m/2+1)
	else:
		if m > n:
			if m%2 != 0: print n*(m/2+1)
			else: print m*(n/2+1)
		else:
			if n%2 != 0: print m*(n/2+1)
			else: print n*(m/2+1)
	print ""
