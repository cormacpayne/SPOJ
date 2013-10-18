import java.io.*;
import java.util.*;
class CSTREET{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int a = 0; a < t; a++){
			int price = in.nextInt();
			int vertices = in.nextInt();
			int edg = in.nextInt();
			int[][] lengths = new int[vertices+1][vertices+1];

			for(int k = 0; k < edg; k++){
				int start = in.nextInt();
				int end = in.nextInt();
				int length = in.nextInt();

				lengths[start][end] = length;
				lengths[end][start] = length;
			}

			List<Edge>[] edges = new List[vertices+1];
			for(int i = 0; i <= vertices; i++){
				edges[i] = new ArrayList<Edge>();
				for(int j = 1; j <= vertices; j++){
					if(lengths[i][j] != 0){
						edges[i].add(new Edge(j, lengths[i][j]));
					}
				}
			}

			int[] pred = new int[lengths.length];
			string.append(mst(edges, pred)*price + "\n");
		}
		System.out.print(string);
	}

       //Prim's algorithm for minimum spanning tree
	public static long mst(List<Edge>[] edges, int[] pred) {
        int n = edges.length;
        Arrays.fill(pred, -1);
        boolean[] used = new boolean[n];
        int[] prio = new int[n];
        Arrays.fill(prio, Integer.MAX_VALUE);
        prio[1] = 0;
        PriorityQueue<Long> q = new PriorityQueue<Long>();
        q.add(1L);
        long res = 0;

        while (!q.isEmpty()) {
            long cur = q.poll();
            int u = (int) cur;
            if (used[u])
                continue;
            used[u] = true;
            res += cur >>> 32;
            for (Edge e : edges[u]) {
                int v = e.t;
                if (!used[v] && prio[v] > e.cost) {
                    prio[v] = e.cost;
                    pred[v] = u;
                    q.add(((long) prio[v] << 32) + v);
                }
            }
        }
        return res;
    }

    static class Edge {
        int t, cost;

        public Edge(int t, int cost) {
            this.t = t;
            this.cost = cost;
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
