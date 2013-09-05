import java.util.*;
import java.io.*;
class BYTESE2{
    public static void main(String[] args) throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int t = in.nextInt();
        for(int x = 0; x < t; x++){
            int n = in.nextInt();
            int[] one = new int[n];
            int[] two = new int[n];
            int max = 0;
            int min = 10000000;
            for(int y = 0; y < n; y++){
                int start = in.nextInt();
                int end = in.nextInt();
                if(start < min) min = start;
                if(end > max) max = end;
                one[y] = start;
                two[y] = end;
            }
            int[] a = new int[max+1];
            for(int z = 0; z < n; z++){
                for(int d = one[z]; d < two[z]; d++){
                    a[d]++;
                }
            }
            int result = -1;
            for(int i = min; i < max; i++){
                if(a[i] > result) result = a[i];
            }
            string.append(result + "\n");
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
