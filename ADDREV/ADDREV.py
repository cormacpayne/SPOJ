t = int(raw_input())
while t:
    t-=1
    a,b = [str(x) for x in raw_input().split()]
    print str(int(a[::-1].lstrip("0"))+int(b[::-1].lstrip("0")))[::-1].lstrip("0") 
