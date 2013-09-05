def choose(n, k):
    ntok = 1
    ktok = 1
    for t in xrange(1, min(k, n-k)+1):
        ntok *= n
        ktok *= t
        n -= 1
    return ntok/ktok

c = int(raw_input())
while c:
    c-=1
    n,k = [int(x) for x in raw_input().split()]
    if n == k: print "1"
    else: print choose(n-1, k-1)
