import sys
case = 1
for s in sys.stdin:
  numbers = sorted(map(int, s.split()))
  print "Case %d: %d" % (case, numbers[2] + numbers[3])
  case+=1  
