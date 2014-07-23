import java.util.*;
import java.io.*;

class HIGHWAYS {

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		int t = in.nextInt();

		for (int i = 0; i < t; i++) {
			int nodes = in.nextInt();
			int edges = in.nextInt();
			int start = in.nextInt();
			int finish = in.nextInt();

			ArrayList<Edge>[] graph = new ArrayList[nodes + 1];

			for (int j = 1; j <= nodes; j++) {
				graph[j] = new ArrayList<Edge>();
			}

			for (int k = 0; k < edges; k++) {
				int a = in.nextInt();
				int b = in.nextInt();
				int cost = in.nextInt();
				graph[a].add(new Edge(b, cost));
				graph[b].add(new Edge(a, cost));
			}

			int[] distances = new int[nodes + 1];
			boolean[] visited = new boolean[nodes + 1];
			Arrays.fill(distances, -1);

			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			pq.add(new Edge(start, 0));

			Edge current;
			int id, weight;

			while (!pq.isEmpty()) {
				current = pq.remove();
				id = current.end;
				weight = current.distance;

				if (visited[id]) continue;
				visited[id] = true;
				distances[id] = weight;

				if (id == finish) break;

				for (Edge edge : graph[id]) {
					if (!visited[edge.end]) {
						pq.add(new Edge(edge.end, weight + edge.distance));
					}
				}
			}

			if (distances[finish] == -1) string.append("NONE\n");
			else string.append(distances[finish] + "\n");
		}
		System.out.print(string);
	}
}

class Edge implements Comparable<Edge> {
	int end, distance;

	public Edge(int end, int distance) {
		this.end = end;
		this.distance = distance;
	}

	public int compareTo(Edge edge) {
		return this.distance - edge.distance;
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
