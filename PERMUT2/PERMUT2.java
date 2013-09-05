import java.io.*;
import java.util.*;
class PERMUT2{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int n = in.nextInt();
		while(n != 0){
			int[] array = new int[n];
			for(int x = 0; x < n; x++){
				int num = in.nextInt();
				array[x] = num;
			}
			int[] newArray = new int[n];
			for(int y = 1; y <= n; y++){
				int pos = array[y-1];
				newArray[pos-1] = y;
			}
			boolean status = true;
			for(int z = 0; z < n; z++){
				if(array[z] != newArray[z]){
					status = false;
					break;
				}
			}
			if(status){
				string.append("ambiguous\n");
			}else{
				string.append("not ambiguous\n");
			}
			n = in.nextInt();
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
