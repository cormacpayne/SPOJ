import java.util.*;
import java.io.*;

class STREETR {

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		int n = in.nextInt();
		int[] nums = new int[n];
		int[] diffs = new int[n - 1];
		nums[0] = in.nextInt();

		int num = nums[0];

		for (int i = 1; i < n; i++) {
			nums[i] = in.nextInt();
			diffs[i - 1] = nums[i] - num;
			num = nums[i];
		}

		int g = gcd(diffs[0], diffs[1]);

		for (int j = 2; j < n - 1; j++) {
			g = gcd(g, diffs[j]);
		}

		int range = nums[n - 1] - nums[0];
		System.out.print((range / g) - n + 1);
	}

	public static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
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
