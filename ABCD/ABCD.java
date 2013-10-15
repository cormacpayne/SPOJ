import java.io.*;
import java.util.*;
class ABCD{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int n = in.nextInt();
		String word = in.next();
		char[] a = new char[2*n];
		
		for(int j = 0; j < 2*n; j+=2){
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(word.charAt(j) - 'A');
			list.add(word.charAt(j+1) - 'A');
			for(int m = 0; m < 4; m++){
				if(!list.contains(m) && a[j] == '\0'){
					a[j] = (char)(65+m);					
				}else if(!list.contains(m) && a[j] != '\0'){
					a[j+1] = (char)(65+m);
					break;
				}
			}	

			if(j > 0 && a[j] == a[j-1]){
				char temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
			}
		}

		for(int l = 0; l < 2*n; l++){
			string.append(a[l]);
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
