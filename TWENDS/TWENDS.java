import java.util.*;
import java.io.*;

class TWENDS {

	public static int[] array, sums;
	public static int[][] memo;

	public static int solve(int left, int right) {
		if (right - left == 1) {
			return Math.max(array[left], array[right]);
		}

		if (memo[left][right] != -1) {
			return memo[left][right];
		}

		int ol, or, tl, tr;
		ol = or = tl = tr = 0;

		if (array[left + 1] >= array[right]) {
			ol = 1;
		} else {
			or = -1;
		}

		if (array[left] >= array[right - 1]) {
			tl = 1;
		} else {
			tr = -1;
		}

		memo[left][right] = Math.max(array[left] + solve(left + 1 + ol, right + or), array[right] + solve(left + tl, right - 1 + tr));
		return memo[left][right];
	}

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int n = in.nextInt();
		int count = 1;

		while (n != 0) {
			
			array = new int[n];
			sums = new int[n];
			memo = new int[n][n];

			for (int[] row : memo) {
				Arrays.fill(row, -1);
			}

			int sum = 0;

			for (int j = 0; j < n; j++) {
				array[j] = in.nextInt();
				sum += array[j];
				sums[j] = sum;
			}

			int ans = solve(0, n - 1);

			string.append("In game " + count + ", the greedy strategy might lose by as many as " + (ans + ans - sum) + " points.\n");

			n = in.nextInt();
			count++;
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
