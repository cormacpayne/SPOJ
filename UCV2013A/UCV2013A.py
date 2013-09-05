r, n = [int(x) for x in raw_input().split()]
while True:
    if r == 0 and n == 0:break
    else:
        print ((r*(1-r**n))/(1-r))%(1000000000+7)
        r, n = [int(x) for x in raw_input().split()]
