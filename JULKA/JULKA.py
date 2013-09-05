for x in range(0, 10):
	t = int(raw_input())
	d = int(raw_input())
	ht = t/2
	hd = d/2
	a = ht+hd
	b = ht-hd
	if t%2 == 1:
		a += 1
		print a
	else:
		print a
	print b
