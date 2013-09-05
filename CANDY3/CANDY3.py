n = int(raw_input())
while n:
	n -= 1
	b = raw_input()
	k = int(raw_input())
	t = 0
	for y in range(0, k):
		l = int(raw_input())
		t += l
	if t%k != 0: print "NO"
	else: print "YES"
