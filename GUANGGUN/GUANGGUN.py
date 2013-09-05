while(1):
    try:
        n = int(raw_input())
        print (n/9) * 81 + (n%9)**2
    except EOFError:break
