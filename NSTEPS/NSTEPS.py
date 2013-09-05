c = int(raw_input())
while c:
    c-=1
    a,b = [int(x) for x in raw_input().split()]
    if a%2 == 0 and (a == b or a == b+2):
        t = a + b
        print t
    elif a%2 == 1 and (a == b or a == b+2):
        s = a + b - 1
        print s
    else: print "No Number"
