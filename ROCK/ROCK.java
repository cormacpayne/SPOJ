import java.util.*;
import java.io.*;

class ROCK {

	public static int[] sums;
	public static int[][] memo;

	public static int find(int left, int right) {
		if (sums[right + 1] - sums[left] > 0) {
			return right - left + 1;
		} 

		if (memo[left][right] != -1) {
			return memo[left][right];
		}

		int ans = 0;

		for (int i = left; i < right; i++) {
			ans = Math.max(ans, find(left, i) + find(i + 1, right));
		}
		memo[left][right] = ans;
		return ans;
	}

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		int t = in.nextInt();

		for (int i = 0; i < t; i++) {
			int len = in.nextInt();
			String rock = in.next();

			sums = new int[len + 1];
			memo = new int[len][len];

			for (int[] row : memo) {
				Arrays.fill(row, -1);
			}

			sums[0] = 0;

			for (int j = 0; j < len; j++) {
				char c = rock.charAt(j);
				if (c == '1') sums[j + 1] = sums[j] + 1;
				else sums[j + 1] = sums[j] - 1;
			}

			int ans = find(0, len - 1);
			string.append(ans + "\n");
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
