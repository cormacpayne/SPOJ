import java.io.*;
import java.util.*;
class PYRA {	
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int vertices = in.nextInt();
			Root[] graph = new Root[vertices];
			
			for(int j = 0; j < vertices; j++){
				graph[j] = new Root(j);
			}
			
			for(int k = 0; k < vertices-1; k++){
				int a = in.nextInt();
				int b = in.nextInt();
				graph[a].conn.add(b);
				graph[b].conn.add(a);
			}
			boolean[] visited = new boolean[vertices];
			visited[0] = true;
			int total = DFS(0, visited, graph);
			for(Root r : graph){
				if(r.id != 0){
					total += r.distance;
				}
			}
			string.append(total + "\n");
			
		}
		System.out.print(string);
	}
	
	public static int DFS(int root, boolean[] visited, Root[] graph){
		for(int i = 0; i < graph[root].conn.size(); i++){
			int current = graph[root].conn.get(i);
			if(!visited[current]){
				visited[current] = true;
				graph[root].distance += DFS(current, visited, graph) + 1;
			}
		}
		graph[root].distance++;
		return (graph[root].distance);
	}
	
}

class Root{
	int id, distance;
	ArrayList<Integer> conn;
	
	public Root(int id){
		this.id = id;
		this.distance = 0;
		this.conn = new ArrayList<Integer>();
	}
}

class Parser
{
   final private int BUFFER_SIZE = 1 << 16;

   private DataInputStream din;
   private byte[] buffer;
   private int bufferPointer, bytesRead;

   public Parser(InputStream in)
   {
      din = new DataInputStream(in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
   }

   public long nextLong() throws Exception
   {
      long ret = 0;
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
   
   //reads in the next string
   public String next() throws Exception
   {
      StringBuilder ret = new StringBuilder();
      byte c = read();
      while (c <= ' ') c = read();
      do
      {
         ret = ret.append((char)c);
         c = read();
      } while (c > ' ');
      return ret.toString();
   }

   public int nextInt() throws Exception
   {
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
   
   private void fillBuffer() throws Exception
   {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) buffer[0] = -1;
   }

   private byte read() throws Exception
   {
      if (bufferPointer == bytesRead) fillBuffer();
      return buffer[bufferPointer++];
   }
}
