import java.io.*;
import java.util.*;
class YELBRICK{
    public static void main(String[] args) throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int num = in.nextInt();
        while(num != 0){
            long[][] array = new long[num][3];
            long sum = 0;
            boolean status = false;
            long r = (long)in.nextInt();
            long s = (long)in.nextInt();
            long t = (long)in.nextInt();
            array[0][0] = r;
            array[0][1] = s;
            array[0][2] = t;
            long gcd = r;
            gcd = gcd(t, gcd(s, gcd));
            for(int x = 1; x < num; x++){
                long a = (long)in.nextInt();
                long b = (long)in.nextInt();
                long c = (long)in.nextInt();
                array[x][0] = a;
                array[x][1] = b;
                array[x][2] = c;
                gcd = gcd(a, gcd(b, gcd(c, gcd)));
            }
            for(int y = 0; y < num; y++){
                sum += (array[y][0]/gcd)*(array[y][1]/gcd)*(array[y][2]/gcd);
            }
            string.append(sum + "\n");
            num = in.nextInt();
        }
        System.out.print(string);
    }


    public static long gcd(long a, long b){
        while(b != 0){
            long temp = b;
            b = a%b;
            a = temp;
        }
        return a;
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
