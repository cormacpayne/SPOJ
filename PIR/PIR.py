from math import sqrt
 
t = int(raw_input())
while t:
    t-=1
    U,V,w,W,v,u = [int(x) for x in raw_input().split()]
    X = (w-U+v)*(U+v+w)
    x = (U-v+w)*(v-w+U)
    Y = (u-V+w)*(V+w+u)
    y = (V-w+u)*(w-u+V)
    Z = (v-W+u)*(W+u+v)
    z = (W-u+v)*(u-v+W)
    a = sqrt(x*Y*Z)
    b = sqrt(y*Z*X)
    c = sqrt(z*X*Y)
    d = sqrt(x*y*z)
    print"%.4f"%((sqrt((b+c+d-a)*(a-b+c+d)*(a+b-c+d)*(a+b+c-d)))/(192*u*v*w))
