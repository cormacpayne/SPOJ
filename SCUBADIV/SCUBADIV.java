import java.util.*;
import java.io.*;

class SCUBADIV {

	public static int totalOxygen, totalNitrogen, numItems;
	public static int[] oxygen, nitrogen, weights;
	public static int[][][] memo;

	public static int solve(int ox, int ni, int curr) {
		if (ox <= 0 && ni <= 0) return 0;
		if (curr == numItems) return 999999999;

		if (ox < 0) ox = 0;
		if (ni < 0) ni = 0;

		if (memo[ox][ni][curr] != 0) return memo[ox][ni][curr];

		memo[ox][ni][curr] = Math.min(solve(ox, ni, curr + 1), weights[curr] + solve(ox - oxygen[curr], ni - nitrogen[curr], curr + 1));
		return memo[ox][ni][curr];
	}

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();

		for (int i = 0; i < t; i++) {
			totalOxygen = in.nextInt();
			totalNitrogen = in.nextInt();

			numItems = in.nextInt();

			oxygen = new int[numItems];
			nitrogen = new int[numItems];
			weights = new int[numItems];

			for (int j = 0; j < numItems; j++) {
				oxygen[j] = in.nextInt();
				nitrogen[j] = in.nextInt();
				weights[j] = in.nextInt();
			}

			memo = new int[totalOxygen + 1][totalNitrogen + 1][numItems + 1];

			int answer = solve(totalOxygen, totalNitrogen, 0);
			string.append(answer + "\n");
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
