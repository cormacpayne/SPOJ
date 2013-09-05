import java.io.*;
import java.util.*;
class JNEXT{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		for(int x = 0; x < num; x++){
			int t = in.nextInt();
			int k = -1;
			int[] array = new int[t];
			array[0] = in.nextInt();
			for(int y = 1; y < t; y++){
				array[y] = in.nextInt();
				if(array[y] > array[y-1]) k = y-1;
			}
			if(k == -1) string.append("-1\n");
			else{
				for(int l = array.length-1; l > k; l--){
					if(array[l] > array[k]){
						int temp = array[l];
						array[l] = array[k];
						array[k] = temp;
						int count = 0;
						for(int a = k+1; a < (array.length+k+1)/2; a++){
							int s = array[a];
							array[a] = array[array.length-1-count];
							array[array.length-1-count] = s;
							count++;
						}
						break;
					}
				}
				for(int r = 0; r < array.length; r++){
					string.append(array[r]);
				}
				string.append("\n");
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
