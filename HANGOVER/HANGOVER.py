n = float(raw_input())
while n != 0.00:
	s = 0.0
	t = 0
	while s <= n and t < 276:
		t += 1
		s += 1.0/(1+t)
	print ("%d card(s)")%(t)
	n = float(raw_input())
