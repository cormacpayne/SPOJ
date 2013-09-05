L = list()
n = 1
while n < 100000000000000:
	L.append(n)
	n *= 2
num = input()
if num in L or num < 1: print 'TAK'
else : print 'NIE'
