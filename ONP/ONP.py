t = int(raw_input())
while t:
    t-=1
    line = raw_input()
    stack = []    
    string = ""
    for y in range(0, len(line)):
        c = line[y]
        if c == '+' or c == '-' or c == '*' or c == '/' or c == '^':
            stack.append(c)
        elif c == ')':
            string += stack.pop()
        elif c == '(':
            continue
        else:
            string += c
    print string    
