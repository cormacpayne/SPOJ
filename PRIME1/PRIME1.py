primes = [True]*32000
for i in range(2, 180):
	start = i*i
	while start < 32000:
		primes[start] = False
		start += i
p = []
for k in range(2, 32000):
	if primes[k]:p.append(k)

t = int(raw_input())
while t:
	t-=1
	n,m = [int(x) for x in raw_input().split()]
	L = [0]*(m-n+1)
	for pr in p:
		if pr*pr > m:break
		lower = n/pr
		lower *= pr
		for l in range(lower, m+1, pr):
			if l != pr and l >= n:L[l-n] = 1

	for num in range(0, m-n+1):
		if L[num] == 0 and n+num != 1:print num+n
	print ""
