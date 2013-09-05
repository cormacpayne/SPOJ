c = int(raw_input())
while c:
	c-=1
	n,k,t,f = [int(x) for x in raw_input().split()]
	print n + (k*(f-n))/(k-1)
