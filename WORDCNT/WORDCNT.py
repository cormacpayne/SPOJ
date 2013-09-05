t = int(raw_input())
while t:
    t-=1
    L = [str(x) for x in raw_input().split()]
    count = 0
    temp = 0
    for x in range(0, len(L)):
        if x == len(L)-1:
            temp += 1
            if temp > count: 
                count = temp
            break
        if len(L[x]) == len(L[x+1]):temp+=1
        else:
            temp += 1
            if temp > count: 
                count = temp
            temp = 0
    print count
