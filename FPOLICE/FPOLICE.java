import java.util.*;
import java.io.*;

class FPOLICE {

	public static int[][] costs, times;
	public static Point[][] memo;
	public static int n, time;
	public static boolean flag;

	public static Point go(int curr, int timeRemaining) {
		if (timeRemaining < 0) return new Point(999999999, 999999999);
		if (curr == n - 1) {
			flag = true;
			return new Point(0, 0);
		}
		if (memo[curr][timeRemaining] != null) return memo[curr][timeRemaining];

		Point ans = new Point(999999999, 999999999);

		for (int i = 0; i < n; i++) {
			if (i != curr) {
				Point p = go(i, timeRemaining - times[curr][i]);
				int newCost = p.cost + costs[curr][i];

				if (newCost < ans.cost) {
					ans.cost = newCost;
					ans.time = p.time + times[curr][i];
				} else if (newCost == ans.cost) {
					ans.time = Math.max(p.time + times[curr][i], ans.time);
				}
			}
		}

		memo[curr][timeRemaining] = ans;
		return ans;
	}

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();

		for (int i = 0; i < t; i++) {
			n = in.nextInt();
			time = in.nextInt();

			int j, k;

			costs = new int[n][n];
			times = new int[n][n];
			memo = new Point[n + 1][time + 1];
			flag = false;

			for (j = 0; j < n; j++) {
				for (k = 0; k < n; k++) {
					times[j][k] = in.nextInt();
				}
			}

			for (j = 0; j < n; j++) {
				for (k = 0; k < n; k++) {
					costs[j][k] = in.nextInt();
				}
			}

			Point ans = go(0, time);
			if (flag) string.append(ans.cost + " " + ans.time + "\n");
			else string.append("-1\n");
		}
		System.out.print(string);
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
