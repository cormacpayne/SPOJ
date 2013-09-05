n = int(raw_input())
a = [0,1]
b = [0,1]
i = 2
while i <= n:
    a.append(a[i-1] + b[i-1])
    b.append(a[i-1])
    i+=1
print a[n]+b[n]
