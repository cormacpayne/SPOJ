L = []
n = raw_input()
c = 0
while True:
	s = 0
	for x in range(0, len(n)):
		s += int(n[x:x+1])**2
	if s in L:
		c = -1
		break
	elif s == 1:
		c+=1
		break
	else:
		L.append(s)
		n = str(s)
		c+= 1
print c
