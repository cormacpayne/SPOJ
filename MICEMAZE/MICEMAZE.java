import java.io.*;
import java.util.*;
class MICEMAZE{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		int vertices = in.nextInt();
		int exit = in.nextInt();
		int time = in.nextInt();
		int edges = in.nextInt();

		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		int[][] costs = new int[vertices+1][vertices+1];
		for(int i = 0; i <= vertices; i++){
			graph.add(new ArrayList<Integer>());
		}

		for(int j = 0; j < edges; j++){
			int a = in.nextInt();
			int b = in.nextInt();
			int cost = in.nextInt();
			costs[b][a] = cost;
			graph.get(b).add(a);
		}

		int[] distances = new int[vertices+1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		Point current = null;
		distances[exit] = 0;
		pq.add(new Point(exit, 0));

		while(!pq.isEmpty()){
			current = pq.remove();
			for(int k = 0; k < graph.get(current.id).size(); k++){
				int neighbor = graph.get(current.id).get(k);
				int distance = costs[current.id][neighbor] + current.dist;
				if(distance < distances[neighbor]){
					distances[neighbor] = distance;
					pq.add(new Point(neighbor, distance));
				}
			}
		}

		int count = 0;
		for(int f = 1; f <= vertices; f++){
			if(distances[f] <= time){
				count++;
			}
		}

		System.out.println(count);
		
	}

	static class Point implements Comparable<Point>{
		int id, dist;

		public Point(int id, int dist){
			this.id = id;
			this.dist = dist;
		}

		public int compareTo(Point p){
			return (this.dist > p.dist) ? 1 : -1;
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
