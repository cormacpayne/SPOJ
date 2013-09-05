c = int(raw_input())
while c:
    c-=1
    t = int(raw_input())
    t-=1
    n1 = t/3
    n2 = t/5
    n3 = t/15
    sum1 = 3*((n1*(n1+1))/2)
    sum2 = 5*((n2*(n2+1))/2)
    sum3 = 15*((n3*(n3+1))/2)
    print int(sum1+sum2-sum3)
