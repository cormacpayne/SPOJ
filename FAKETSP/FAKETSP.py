from math import sqrt
import sys
start = sys.stdin.readline()
firstP = start.index('(')
newStart = start[firstP:]
index = newStart.index(',')
firstNumber = float(newStart[1:index])
lastP = newStart.index(')')
lastNumber = float(newStart[index+1:lastP])
total = 0

for s in sys.stdin:
	firstP = s.index('(')
	newS = s[firstP:]
	index = newS.index(',')
	newFirstNumber = float(newS[1:index])
	lastP = newS.index(')')
	newLastNumber = float(newS[index+1:lastP])
	total += sqrt((firstNumber - newFirstNumber)**2 + (lastNumber - newLastNumber)**2)
	firstNumber = newFirstNumber
	lastNumber = newLastNumber
	print "The salesman has traveled a total of %.3f kilometers."%(total)
