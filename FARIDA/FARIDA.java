import java.util.*;
import java.io.*;
class FARIDA {
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int x = 1; x <= t; x++){
			int n = in.nextInt();
			int[] dp = new int[n];			
			for(int i = 0; i < n; i++){
				dp[i] = in.nextInt();
			}
			if(n == 0) string.append("Case " + x + ": 0\n");
			else if(n == 1) string.append("Case " + x + ": " + dp[0] + "\n");
			else if(n == 2) string.append("Case " + x + ": " + Math.max(dp[0], dp[1]) + "\n");
			else{
				long[] sum = new long[n];
				sum[n-1] = dp[n-1];
				sum[n-2] = Math.max(dp[n-2], dp[n-1]);
				for(int j = n-3; j >= 0; j--){
					sum[j] = Math.max(dp[j] + sum[j+2], sum[j+1]);
				}
				string.append("Case " + x + ": " + sum[0] + "\n");
			}
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
