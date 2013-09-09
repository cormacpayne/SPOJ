from collections import deque

def BFS(graph, vertices):
	queue = deque()
	visited = [False]*(vertices+1)
	queue.appendleft(1)
	current = -1
	visited[1] = True
	while len(queue) != 0:
		current = queue.pop()
		for i in graph[current]:
			if not visited[i]:
				visited[i] = True
				queue.appendleft(i)
	return current

def BFSLength(graph, node, vertices):
	queue = deque()
	visited = [False]*(vertices+1)
	distance = [0]*(vertices+1)
	queue.appendleft(node)
	visited[node] = True
	current = -1
	while len(queue) != 0:
		current = queue.pop()
		for i in graph[current]:
			if not visited[i]:
				visited[i] = True
				queue.appendleft(i)
				distance[i] = distance[current]+1
	return distance[current]

graph = []
vertices = int(raw_input())
for i in range(vertices+1):
	graph.append([])
for j in range(vertices-1):
	start, end = [int(x) for x in raw_input().split()]
	graph[start].append(end)
	graph[end].append(start)
lastNode = BFS(graph, vertices)
print BFSLength(graph, lastNode, vertices)
