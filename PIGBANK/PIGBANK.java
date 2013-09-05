import java.io.*;
import java.util.*;
class PIGBANK {
    public static void main(String[] args)throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int[] dp = new int[10001];
        int t = in.nextInt();
        for(int x = 0; x < t; x++){
            int E = in.nextInt();
            int F = in.nextInt();
            int T = F-E;
            int n = in.nextInt();
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for(int i = 0; i < n; i++){
                int v = in.nextInt();
                int w = in.nextInt();
                for(int j = 0; j+w <= T; j++){
                    if(dp[j] != Integer.MAX_VALUE && dp[j+w] > dp[j]+v){
                        dp[j+w] = dp[j] + v;
                    }
                }                
            }
            if(dp[T] != Integer.MAX_VALUE){
                string.append("The minimum amount of money in the piggy-bank is " + dp[T] + ".\n");
            }else{
                string.append("This is impossible.\n");
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
