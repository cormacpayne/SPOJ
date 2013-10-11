import java.io.*;
import java.util.*;
class ACPC10D{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int rows = in.nextInt();
		int count = 1;
		while(rows != 0){
			int[][] dp = new int[rows][3];
			for(int i = 0; i < rows; i++){
				dp[i][0] = in.nextInt();
				dp[i][1] = in.nextInt();
				dp[i][2] = in.nextInt();
			}

			dp[0][2] += dp[0][1];
			dp[1][0] += dp[0][1];
			dp[1][1] += Math.min(dp[0][1], Math.min(dp[0][2], dp[1][0]));
			dp[1][2] += Math.min(dp[0][1], Math.min(dp[0][2], dp[1][1]));

			for(int j = 2; j < rows; j++){
				dp[j][0] += Math.min(dp[j-1][0], dp[j-1][1]);
				dp[j][1] += Math.min(dp[j-1][0], Math.min(dp[j-1][1], Math.min(dp[j-1][2], dp[j][0])));
				dp[j][2] += Math.min(dp[j-1][1], Math.min(dp[j-1][2], dp[j][1]));
			}

			string.append(count + ". " + dp[rows-1][1] + "\n");
			count++;
			rows = in.nextInt();
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
