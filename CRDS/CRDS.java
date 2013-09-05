import java.io.*;
import java.util.*;
class CRDS {
    public static void main(String[] args) throws Exception{
        
        long[] list = new long[1000001];
        list[0] = 0;
        list[1] = 2;
        for(int x = 2; x <= 1000000; x++){
            list[x] = list[x-1] + (3*x - 1);
        }
        StringBuilder string = new StringBuilder();
        Parser in = new Parser(System.in);
        int num = in.nextInt();
        for(int y = 0; y < num; y++){
            long answer = list[in.nextInt()]%1000007;
            string.append(answer + "\n");
        }
        System.out.println(string);
    }
    
    static class Parser{
	   final private int BUFFER_SIZE = 1 << 16;
	 
	   private DataInputStream din;
	   private byte[] buffer;
	   private int bufferPointer, bytesRead;
	 
	   public Parser(InputStream in){
	      din = new DataInputStream(in);
	      buffer = new byte[BUFFER_SIZE];
	      bufferPointer = bytesRead = 0;
	   }
	 
	   public int nextInt() throws Exception{
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
	 
	   private void fillBuffer() throws Exception{
	      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
	      if (bytesRead == -1) buffer[0] = -1;
	   }
	 
	   private byte read() throws Exception{
	      if (bufferPointer == bytesRead) fillBuffer();
	      return buffer[bufferPointer++];
	   }
	}    
}
