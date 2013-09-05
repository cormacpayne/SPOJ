c = input()
while c:
  c-=1
  a, b = [int(x) for x in raw_input().split()]
  if b == 1: print 'Pagfloyd wins.'
  else: print 'Airborne wins.'
