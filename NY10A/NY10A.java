import java.io.*;
import java.util.*;
class NY10A{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int x = 0; x < t; x++){
			int n = in.nextInt();
			String l = in.next();
			int[] a = new int[8];
			for(int i = 0; i < 38; i++){
				String o = l.substring(i, i+3);
				if(o.equals("TTT")){
					a[0]++;
				}else if(o.equals("TTH")){
					a[1]++;
				}else if(o.equals("THT")){
					a[2]++;
				}else if(o.equals("THH")){
					a[3]++;
				}else if(o.equals("HTT")){
					a[4]++;
				}else if(o.equals("HTH")){
					a[5]++;
				}else if(o.equals("HHT")){
					a[6]++;
				}else{
					a[7]++;
				}
			}
			string.append(n + " ");
			for(int j = 0; j < 8; j++){
				string.append(a[j] + " ");
			}
			string.append("\n");
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
