c = int(raw_input())
while c:
    c-=1
    num = int(raw_input())
    count = 0
    while num:
        count += num/5
        num /= 5
    print count
