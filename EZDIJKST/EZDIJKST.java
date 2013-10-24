import java.io.*;
import java.util.*;
class EZDIJKST{

	public static void main(String[] args) throws Exception{
	
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		for(int x = 0; x < num; x++){
			int vertices = in.nextInt();
			int edges = in.nextInt();
			Node[] graph = new Node[vertices+1];
			int[][] costs = new int[vertices+1][vertices+1];
			for(int i = 1; i <= vertices; i++){
				graph[i] = new Node(i);
			}
			
			for(int y = 0; y < edges; y++){
				int a = in.nextInt();
				int b = in.nextInt();
				graph[a].conn.add(b);
				costs[a][b] = in.nextInt();
			}
			
			int source = in.nextInt();
			int destination = in.nextInt();			
			int result = doSomeMagic(graph, source, destination, costs, vertices);
			
			if(result == -1){
				string.append("NO\n");
			}else{
				string.append(result + "\n");
			}		
		}
		System.out.print(string);
	}
	
	public static int doSomeMagic(Node[] graph, int source, int destination, int[][] costs, int vertices){
		Node current = graph[source];
		int[] distances = new int[vertices+1];
		boolean[] visited = new boolean[vertices+1];
		
		Arrays.fill(visited, false);
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[current.id] = 0;
			
		while(!visited[destination]){	
			visited[current.id] = true;								
			List<Integer> neighbors = current.conn;
			for(int n = 0; n < neighbors.size(); n++){
				int newDistance = distances[current.id] + costs[current.id][neighbors.get(n)];
				if(newDistance < distances[neighbors.get(n)]){
					distances[neighbors.get(n)] = newDistance;
				}		
			}
			
			int index = -1;
			int min = Integer.MAX_VALUE;
			for(int i = 1; i <= vertices; i++){
				if(distances[i] < min && !visited[i]){
					index = i;
					min = distances[i];
				}
			}
			if(min == Integer.MAX_VALUE){
				break;
			}						
			current = graph[index];			
		}	
				
		if(distances[destination] == Integer.MAX_VALUE){
			return -1;
		}else{
			return distances[destination];	
		}
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
