import java.io.*;
import java.util.*;
class MMAXPER {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		int n = in.nextInt();
		int[][] dp = new int[n][2];
		int[][] side = new int[n][2];
		
		for(int j = 0; j < n; j++){
			side[j][0] = in.nextInt();
			side[j][1] = in.nextInt();
			if(side[j][0] > side[j][1]){
				int temp = side[j][0];
				side[j][0] = side[j][1];
				side[j][1] = temp;
			}
			
		}
		
		dp[0][0] = side[0][0];
		dp[0][1] = side[0][1];
		
		for(int i = 1; i < n; i++){
			dp[i][0] = Math.max(dp[i-1][0] + side[i][0] + Math.abs(side[i][1]-side[i-1][1]), dp[i-1][1] + side[i][0] + Math.abs(side[i][1]-side[i-1][0]));
			dp[i][1] = Math.max(dp[i-1][0] + side[i][1] + Math.abs(side[i][0]-side[i-1][1]), dp[i-1][1] + side[i][1] + Math.abs(side[i][0]-side[i-1][0]));
		}
		System.out.print(Math.max(dp[n-1][0], dp[n-1][1]));
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


