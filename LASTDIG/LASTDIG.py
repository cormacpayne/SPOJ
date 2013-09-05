c = int(raw_input())
while c:
  c-=1
  a, b = [int(x) for x in raw_input().split()]
  if b == 0:print 1
  else:
    b%=4
    if b == 0:b = 4
    t = 1
    for x in range (0, b):
      t *= a
    print t%10
