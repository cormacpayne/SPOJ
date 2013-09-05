import java.io.*;
import java.util.*;
class PARTY{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		for(;;){
			int budget = in.nextInt();
			int n = in.nextInt();
			if(budget == 0 && n == 0) break;
			int[] dp = new int[budget+1];
			dp[0] = 1;
			while(n-- != 0){
				int cost = in.nextInt();
				int fun = in.nextInt();
				for(int i = budget-cost; i >= 0; i--){
					if(dp[i] != 0){
						int nfun = fun + dp[i];
						int ncost = i + cost;
						if(dp[ncost] < nfun) dp[ncost] = nfun;
					}
				}
			}
			int best = -1;
			int spent = 0;
			for(int i = 0; i <= budget; i++){
				if(dp[i] > best){
					best = dp[i];
					spent = i;
				}
			}
			string.append(spent + " " + (best-1) + "\n");
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
