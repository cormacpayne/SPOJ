import java.io.*;
import java.util.*;
class RPLD{
    public static void main(String[] args) throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int num = in.nextInt();
        for(int x = 1; x <= num; x++){
            int id = in.nextInt();
            int n = in.nextInt();
            boolean status = false;
            ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
            for(int y = 0; y < id; y++){
                list.add(new ArrayList<Integer>());
            }
            for(int z = 0; z < n; z++){
                int i = in.nextInt();
                int d = in.nextInt();
                if(!status){
                    if(list.get(i-1).contains(d)){
                        status = true;
                    }else if(!list.get(i-1).contains(d)){
                        list.get(i-1).add(d);
                    }
                }
            }            
            if(status){
                string.append("Scenario #" + x + ": impossible\n\n");
            }else{
                string.append("Scenario #" + x + ": possible\n\n");
            }
        }
        System.out.print(string);
    }
}

class Parser{
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
