a,b = [int(x) for x in raw_input().split()]
while a != 0 and b != 0:
	A = [int(y) for y in raw_input().split()]
	D = [int(z) for z in raw_input().split()]
	A.sort()
	D.sort()
	if A[0] < D[1]: print 'Y'
	else: print 'N'
	a,b = [int(x) for x in raw_input().split()]
