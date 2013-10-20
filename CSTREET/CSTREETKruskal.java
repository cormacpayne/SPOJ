import java.util.*;
import java.io.*;
class CSTREETKruskal{
	public static void main (String args[]) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		
		int numCases = in.nextInt();
		while (numCases-- > 0) {
			int price = in.nextInt();
			int vertices = in.nextInt();
			int edg = in.nextInt();
			
			Edge [] edges = new Edge[edg];
			PriorityQueue<Edge> queEdge = new PriorityQueue<Edge>();
			PriorityQueue<Integer> queInt = new PriorityQueue<Integer>();
			
			for (int i = 0; i < edg; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int c = in.nextInt();
				edges[i] = new Edge(x, y, c);
				queEdge.add(edges[i]);
			}
			
			int [] inSet = new int [vertices+1];
			for (int i = 1; i <= vertices; i++) {
				inSet[i] = i;
			}
			
			int sum = 0;
			while (!queEdge.isEmpty()) {
				Edge cur = queEdge.poll();
				int xSet = findSet(inSet, cur.x);
				int ySet = findSet(inSet, cur.y);
				if (xSet != ySet) {
					inSet[ySet] = xSet;
					sum += cur.cost;
				}
			}
			string.append(sum*price + "\n");
		}
		System.out.print(string);
	}
	public static int findSet(int [] inSet, int i) {
		if (inSet[i] == i) {
			return i;
		}
		return findSet(inSet, inSet[i]);
	} 
}
class Edge implements Comparable<Edge>{
	int x; 
	int y; 
	int cost;
	
	public Edge(int a, int b, int c) {
		x = a;
		y = b;
		cost = c;	
	}
	
	public int compareTo(Edge e) {
		return cost - e.cost;
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
