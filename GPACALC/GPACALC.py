t = int(raw_input())
while t:
	t-=1
	num = int(raw_input())
	total = 0
	credits = 0
	while num:
		num-=1
		c,g = [str(x) for x in raw_input().split()]
		cr = int(c)
		credits += cr
		if g == "S":total+=10*cr
		elif g == "A":total+=9*cr
		elif g == "B":total+=8*cr
		elif g == "C":total+=7*cr
		elif g == "D":total+=6*cr
		else:total+=5*cr
	print "%.2f"%(float(total)/credits)		
