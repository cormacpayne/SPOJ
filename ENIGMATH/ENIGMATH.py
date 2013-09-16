def gcd(a, b):
   if b==0:return a
   return gcd(b,a%b)


t = int(raw_input())
while t:
	t-=1
	a,b = [int(x) for x in raw_input().split()]
	i = gcd(a,b)
	print "%d %d"%(b/i,a/i)
