a = [1, 3]
for i in range(2, 25):
	a.append(2*a[i-1] + a[i-2])
n = int(raw_input())
print a[n]
