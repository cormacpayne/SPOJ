t = int(raw_input())
while t:
	t-=1
	h,a = [int(x) for x in raw_input().split()]
	count = 0;
	while h > 0 and a > 0:
		h+=3
		a+=2
		count+=1
		if h-5 > 0 and a-10 > 0:
			h-=5
			a-=10
		else:
			h-= 20
			a+=5
		if h > 0 and a > 0: count+=1
	print count
