import java.util.*;
import java.io.*;

class PARTY {

	public static int weight, items;
	public static int[] weights, values, dp;
	public static int[][] memo;

	public static int sack(int remainingWeight, int id) {
		if (id == items || remainingWeight == 0) {
			return 0;
		}

		if (memo[remainingWeight][id] != -1) {
			return memo[remainingWeight][id];
		}

		if (weights[id] > remainingWeight) {
			return sack(remainingWeight, id + 1);
		}

		memo[remainingWeight][id] = Math.max(sack(remainingWeight, id + 1), values[id] + sack(remainingWeight - weights[id], id + 1));
		return memo[remainingWeight][id];
	}

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		weight = in.nextInt();
		items = in.nextInt();

		while (weight != 0 && items != 0) {

			weights = new int[items];
			values = new int[items];
         		dp = new int[weight + 1];
			memo = new int[weight + 1][items + 1];

			for (int[] row : memo) {
				Arrays.fill(row, -1);
			}

			for (int i = 0; i < items; i++) {
				weights[i] = in.nextInt();
				values[i] = in.nextInt();
			}

			int fun = -1;

		         for (int k = 0; k <= weight; k++) {
		            dp[k] = sack(k, 0);
		            fun = Math.max(dp[k], fun);
		         }

		         for (int j = 0; j <= weight; j++) {
		            if (dp[j] == fun) {
		               string.append(j + " " + dp[j] + "\n");
		               break;
		            }
		         }

			weight = in.nextInt();
			items = in.nextInt();
		}

		System.out.print(string);
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
