import java.io.*;
import java.util.*;
class PT07Y{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		int nodes = magic(in);
		int edges = magic(in);
		if(nodes != edges+1){
			System.out.print("NO");
		}else{
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			for(int y = 0; y <= nodes; y++){
				graph.add(new ArrayList<Integer>());
			}
			for(int x = 0; x < edges; x++){
				int u = magic(in);
				int v = magic(in);
				graph.get(u).add(v);
				graph.get(v).add(u);
			}

			if(BFS(graph, nodes)){
				System.out.print("YES");
			}else{
				System.out.print("NO");
			}
		}
	}


	public static int magic(Parser s) throws Exception{
		return Integer.parseInt(s.next());
	}

	public static boolean BFS(ArrayList<ArrayList<Integer>> graph, int nodes){
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] vertexHasBeenVisited = new boolean[nodes+1];
		Arrays.fill(vertexHasBeenVisited, false);
		q.add(1);
		vertexHasBeenVisited[1] = true;
		int currentVertex;
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
		for(int x = 1; x < vertexHasBeenVisited.length; x++){
			if(!vertexHasBeenVisited[x]){
				return false;
			}
		}
		return true;
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
      StringBuilder ret =  new StringBuilder();
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
