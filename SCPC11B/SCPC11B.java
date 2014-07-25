import java.util.*;
import java.io.*;

class SCPC11B {

	public static final int LENGTH = 2844;

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		int n = in.nextInt();

		while (n != 0) {
			int[] stops = new int[2*n];

			for (int j = 0; j < n; j++) {
				stops[j] = in.nextInt();
				stops[j + n] = (LENGTH) - stops[j];
			}

			Arrays.sort(stops);
			
			int index = 0;

			while (stops[index] < LENGTH - 200) {
				if (index == 2*n - 1) break;
				if (stops[index + 1] - stops[index] > 200) break;

				index++;
			}

			if (stops[index] < LENGTH - 200) string.append("IMPOSSIBLE\n");
			else string.append("POSSIBLE\n");

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
      StringBuilder ret = new StringBuilder();
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
