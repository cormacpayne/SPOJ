import java.io.*;
import java.util.*;
class BENEFACT{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int vertices = in.nextInt();
			Node[] graph = new Node[vertices+1];

			for(int j = 0; j <= vertices; j++){
				graph[j] = new Node(j);
			}

			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			for(int k = 0; k < vertices-1; k++){
				int start = in.nextInt();
				int end = in.nextInt();
				int length = in.nextInt();
				graph[start].neighbors.add(new Pair(start, end, length));
				graph[end].neighbors.add(new Pair(end, start, length));

				if(pq.size() == 0){
					pq.add(graph[start]);
				}
			}

			boolean[] visited = new boolean[vertices+1];
			Node current = null;
			while(pq.size() != 0){
				current = pq.remove();
				visited[current.id] = true;
				for(int e = 0; e < current.neighbors.size(); e++){
					if(!visited[current.neighbors.get(e).end]){
						Node temp = graph[current.neighbors.get(e).end];
						temp.dist = current.neighbors.get(e).length + current.dist;
						pq.add(temp);
					}
				}
			}

			visited = new boolean[vertices+1];
			pq.add(graph[current.id]);
			current.dist = 0;
			while(pq.size() != 0){
				current = pq.remove();
				visited[current.id] = true;
				for(int e = 0; e < current.neighbors.size(); e++){
					if(!visited[current.neighbors.get(e).end]){
						Node temp = graph[current.neighbors.get(e).end];
						temp.dist = current.neighbors.get(e).length + current.dist;
						pq.add(temp);
					}
				}
			}
			string.append(current.dist + "\n");
		}
		System.out.print(string);
	}

	static class Node implements Comparable<Node>{
		int id, dist;
		ArrayList<Pair> neighbors;

		public Node(int id){
			this.id = id;
			this.dist = 0;
			neighbors = new ArrayList<Pair>();
		}

		public int compareTo(Node n){
			return (this.dist > n.dist) ? 1 : -1;
		}
	}

	static class Pair{
		int start, end, length;

		public Pair(int start, int end, int length){
			this.start = start;
			this.end = end;
			this.length = length;
		}
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
