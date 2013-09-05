from math import sqrt

L = list()
c = input()
L.append(0)
L.append(1)
for x in range (2, c+1):
    L.append(L[x-1])
    L[x] += 1
    for y in range (2, int(sqrt(x))+1):
        if x%y == 0 : L[x] += 1
print L[c] 
