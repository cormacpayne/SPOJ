l = str(raw_input())
n = [0,0,0,0]

for i in range(0, len(l)-1):
    c = l[i]
    if c == '0':
        n[0]+=1
        if l[i+1] == '0':n[2]+=1
        else:n[3]+=1
    else:n[1]+=1
    
if l[len(l)-1] == '0':
    n[0]+=1
    if l[0] == '0':n[2]+=1
    else:n[3]+=1
else:n[1]+=1

num = float(n[0])/(n[0]+n[1])
after = float(n[2])/(n[2]+n[3])

if num == after:print "EQUAL"
elif after > num:print "SHOOT"
else:print "ROTATE"
