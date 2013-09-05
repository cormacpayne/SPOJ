f = int(raw_input())
while f:
	f-=1
	m = int(raw_input())
	s = 0
	c = 0
	L = [int(x) for x in raw_input().split()]
	for y in range (0, m):
		s += L[y]
		c = c ^ L[y]
	if s == m:
		if s%2 == 0: print 'John'
		else: print 'Brother'
	else:
		if c == 0: print 'Brother'
		else: print 'John'
