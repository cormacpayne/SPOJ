from collections import deque

def BFS(graph, vertices):
	queue = deque()
	visited = [False]*(vertices+1)
	queue.appendleft(1)
	current = -1
	while len(queue) != 0:
		current = queue.pop()
		for i in graph[current]:
			if not visited[i]:
				visited[i] = True
				queue.appendleft(i)
	for x in range(1, vertices+1):
		if not visited[x]:
			return False
	return True

graph = []
vertices, edges = [int(x) for x in raw_input().split()]
if vertices != edges+1:print "NO"
else:
	for i in range(vertices+1):
		graph.append([])
	for j in range(edges):
		start, end = [int(x) for x in raw_input().split()]
		graph[start].append(end)
		graph[end].append(start)
	if BFS(graph, vertices):print "YES"
	else:print "NO"
