L = list()
num = 1
count = 0
while num < 1000000000:
    num += count
    count += 6
    L.append(num)
while True:
    n = input()
    if n == -1 : break
    else : print 'Y' if n in L else 'N'  
