def factors(n):
    f = []
    d = 2
    while n > 1:
        while n%d == 0:
            f.append(d)
            n /= d
        d = d+1
    return len(set(f))

L = [0]
for i in range(30, 2665):
    if factors(i) > 2:
        L.append(i)
t = int(raw_input())
while t:
    t-=1
    num = int(raw_input())
    print L[num]
