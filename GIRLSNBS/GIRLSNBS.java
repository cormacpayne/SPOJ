import java.io.*;
import java.util.*;
class GIRLSNBS{
    public static void main(String[] args) throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int b =  in.nextInt();
        int g = in.nextInt();
        while(b != -1 && g != -1){
            if(b == 0){
                string.append(g + "\n");
            }else if(g == 0){
                string.append(b + "\n");
            }else if(b == g){
                string.append("1\n");
            }else{
                if(g > b){
                    if((double)g/(b+1) == (double)(g/(b+1))){
                        string.append(g/(b+1) + "\n");
                    }else{
                        string.append((g/(b+1) + 1) + "\n");
                    }
                }else{
                    if((double)b/(g+1) == (double)(b/(g+1))){
                        string.append(b/(g+1) + "\n");
                    }else{
                        string.append((b/(g+1) + 1) + "\n");
                    }
                }
            }
            b = in.nextInt();
            g = in.nextInt();
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
