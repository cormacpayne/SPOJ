b, g = [int(x) for x in raw_input().split()]
while True:
    if b == -1: break
    if b == 0: print g
    elif g == 0: print b
    elif b == g: print 1    
    else:
        if b > g: 
            if float(b)/(g+1) == float(int(b/(g+1))): print b/(g+1)
            else: print (int(b/(g+1)) + 1)
        else:
            if float(g)/(b+1) == float(int(g/(b+1))): print g/(b+1)
            else: print (int(g/(b+1)) + 1)
    b, g = [int(x) for x in raw_input().split()]
