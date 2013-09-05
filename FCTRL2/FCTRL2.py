def fac(x):
  if x < 2: return 1
  else: return x * fac(x-1)

n = int(raw_input())
for i in range(n):
  x = int(raw_input())
  print fac(x)
