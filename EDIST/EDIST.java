import java.io.*;
import java.util.*;
class EDIST {
    public static void main(String[] args)throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int c = in.nextInt();
        for(int x = 0; x < c; x++){
            String s = in.next();
            String t = in.next();
            string.append(LevenshteinDistance(s,t) + "\n");
        }
        System.out.print(string);
    }
    
    public static int LevenshteinDistance(String s, String t){
        if(s.equals(t)) return 0;
        if(s.length() == 0) return t.length();
        if(t.length() == 0) return s.length();
        
        int[] v0 = new int[t.length()+1];
        int[] v1 = new int[t.length()+1];
        
        for(int i = 0; i < v0.length; i++){
            v0[i] = i;
        }
        
        for(int i = 0; i < s.length(); i++){
            v1[0] = i+1;
            for(int j = 0; j < t.length(); j++){
                int cost = (s.charAt(i) == t.charAt(j) ? 0:1);
                v1[j+1] = Math.min(v1[j]+1, Math.min(v0[j+1]+1, v0[j]+cost));
            }
            for(int j = 0; j < v0.length; j++){
                v0[j] = v1[j];
            }
        }
        return v1[t.length()];
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
