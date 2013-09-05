import java.io.*;
import java.util.*;
class PT07Z{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		int nodes = in.nextInt();
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for(int x = 0; x <= nodes; x++){
			graph.add(new ArrayList<Integer>());
		}
		for(int y = 1; y < nodes; y++){
			int u = in.nextInt();
			int v = in.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		int lastNode = BFS(graph, nodes);
		System.out.println(BFSLength(graph, lastNode, nodes));

	}

	public static int BFS(ArrayList<ArrayList<Integer>> graph, int nodes){
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] vertexHasBeenVisited = new boolean[nodes+1];
		Arrays.fill(vertexHasBeenVisited, false);
		q.add(1);
		vertexHasBeenVisited[1] = true;
		int currentVertex = 0;
		while(!q.isEmpty()){
			currentVertex = q.remove();
			for(int i = 0; i < graph.get(currentVertex).size(); i++){
				int index = graph.get(currentVertex).get(i);
				if(!vertexHasBeenVisited[index]){
					vertexHasBeenVisited[index] = true;
					q.add(index);
				}
			}
		}
		return currentVertex;		
	}

	public static int BFSLength(ArrayList<ArrayList<Integer>> graph, int node, int nodes){
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] vertexHasBeenVisited = new boolean[nodes+1];
		Arrays.fill(vertexHasBeenVisited, false);
		q.add(node);
		vertexHasBeenVisited[node] = true;
		int[] distance = new int[nodes+1];
		int currentVertex = 0;
		while(!q.isEmpty()){
			currentVertex = q.remove();
			for(int i = 0; i < graph.get(currentVertex).size(); i++){
				int index = graph.get(currentVertex).get(i);
				if(!vertexHasBeenVisited[index]){
					vertexHasBeenVisited[index] = true;
					q.add(index);
					distance[index] = distance[currentVertex] + 1;
				}
			}
		}
		return distance[currentVertex];		
	}
}

class Parser{
	final private int BUFFER_SIZE = 1 << 16;
	 
	private DataInputStream din;
	private byte[] buffer;
	private int bufferPointer, bytesRead;
	 
	public Parser(InputStream in){
		din = new DataInputStream(in);
		buffer = new byte[BUFFER_SIZE];
		bufferPointer = bytesRead = 0;
	}
	 
	public int nextInt() throws Exception{
		int ret = 0;
	    byte c = read();
	    while (c <= ' ') c = read();
	    boolean neg = c == '-';
	    if (neg) c = read();
	    do
	    {
	       ret = ret * 10 + c - '0';
	       c = read();
	    } while (c > ' ');
	    if (neg) return -ret;
	    return ret;
	}
	 
	private void fillBuffer() throws Exception{
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
	    if (bytesRead == -1) buffer[0] = -1;
	}
	 
	private byte read() throws Exception{
		if (bufferPointer == bytesRead) fillBuffer();
	    return buffer[bufferPointer++];
	}
}
