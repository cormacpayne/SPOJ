import java.io.*;
import java.util.*;
class CHICAGO{
	
	public static void main(String[] args) throws Exception{
	
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int vertices = in.nextInt();		
		while(vertices != 0){
			int edges = in.nextInt();
			Node[] graph = new Node[vertices+1];
			double[][] costs = new double[vertices+1][vertices+1];
			for(int i = 1; i <= vertices; i++){
				graph[i] = new Node(i);
			}
			
			for(int y = 0; y < edges; y++){
				int a = in.nextInt();
				int b = in.nextInt();
				graph[a].conn.add(b);
				graph[b].conn.add(a);
				double cost = (double)in.nextInt();
				costs[a][b] = cost/100;
				costs[b][a] = cost/100;
			}
			
			double result = doSomeMagic(graph, costs, vertices);
			
			System.out.printf("%.6f", result);	
			System.out.println(" percent");	
			vertices = in.nextInt();
		}
	}
	
	public static double doSomeMagic(Node[] graph, double[][] costs, int vertices){
		Node current = graph[1];
		double[] distances = new double[vertices+1];
		boolean[] visited = new boolean[vertices+1];		
		
		Arrays.fill(visited, false);
		Arrays.fill(distances, 0);
		distances[1] = 100.0;
		
		while(!visited[vertices]){
			boolean done = true;
			for(int y = 1; y <= vertices; y++){
				if(!visited[y]){
					done = false;
					break;
				}
			}
			if(done){
				break;
			}
			visited[current.id] = true;
			List<Integer> neighbors = current.conn;
			for(int n = 0; n < neighbors.size(); n++){
				double newDistance = distances[current.id] * costs[current.id][neighbors.get(n)];
				if(newDistance > distances[neighbors.get(n)]){
					distances[neighbors.get(n)] = newDistance;
				}
			}
			
			int index = -1;
			double max = 0;
			for(int i = 1; i <= vertices; i++){
				if(distances[i] > max && !visited[i]){
					index = i;
					max = distances[i];
				}
			}
			if(index == -1){
			    break;
			}
			current = graph[index];
		}
		
		return distances[vertices];
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

class Node{
	public List<Integer> conn;
	public int id;
	
	public Node(int id){
		conn = new ArrayList<Integer>();
		this.id = id;
	}		
}
