import java.util.*;
import java.io.*;

class FISHER {

	public static Point[][] memo;
	public static int[][] times, costs;
	public static int n, t;

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		times = new int[55][55];
		costs = new int[55][55];

		while (true) {
			n = in.nextInt();
			t = in.nextInt();

			if (n == 0 && t == 0) break;

			memo = new Point[n + 1][t + 1];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					times[i][j] = in.nextInt();
				}
			}

			for (int k = 0; k < n; k++) {
				for (int l = 0; l < n; l++) {
					costs[k][l] = in.nextInt();
				}
			}

			Point ans = go(0, t);
			string.append(ans.cost + " " + ans.time + "\n");
		}

		System.out.print(string);
	}

	public static Point go(int current, int timeLeft) {
		if (timeLeft < 0) return new Point(999999999, 999999999);

		if (current == n - 1) return new Point(0, 0);

		if (memo[current][timeLeft] != null) return memo[current][timeLeft];

		Point ans = new Point(999999999, 999999999);

		for (int x = 0; x < n; x++) {
			if (x != current) {
				Point result = go(x, timeLeft - times[current][x]);
				if (result.cost + costs[current][x] < ans.cost) {
					ans.cost = result.cost + costs[current][x];
					ans.time = result.time + times[current][x];
				}
			}
		}

		memo[current][timeLeft] = ans;
		return ans;
	}

}

class Point {
	int cost, time;

	public Point(int cost, int time) {
		this.cost = cost;
		this.time = time;
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
