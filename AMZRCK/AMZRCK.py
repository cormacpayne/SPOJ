L = [0, 2, 3]
for i in range(3, 43):
    L.append(L[i-2]+L[i-1])
t = int(raw_input())
while t:
    t-=1
    num = int(raw_input())
    print L[num]
