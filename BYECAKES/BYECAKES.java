import java.util.*;
import java.io.*;

class BYECAKES {

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int e, f, s, m, ee, ff, ss, mm, eeee, ffff, ssss, mmmm;
		double eee, fff, sss, mmm;
	
		e = in.nextInt();

		while (e != -1) {
			f = in.nextInt();
			s = in.nextInt();
			m = in.nextInt();
			ee = in.nextInt();
			ff = in.nextInt();
			ss = in.nextInt();
			mm = in.nextInt();

			eee = (1.0*e) / ee;
			fff = (1.0*f) / ff;
			sss = (1.0*s) / ss;
			mmm = (1.0*m) / mm;

			double temp = Math.max(eee, Math.max(fff, Math.max(sss, mmm)));
			int factor = (int)Math.ceil(temp);

			if (e == 0 && e == f && f == s && s == m) {
				eeee = 0;
				ffff = 0;
				ssss = 0;
				mmmm = 0;
			} else if (factor == 0) {
				eeee = ee - e;
				ffff = ff - f;
				ssss = ss - s;
				mmmm = mm - m;
			} else {
				eeee = (factor * ee) - e;
				ffff = (factor * ff) - f;
				ssss = (factor * ss) - s;
				mmmm = (factor * mm) - m;
			}			

			string.append(eeee + " " + ffff + " " + ssss + " " + mmmm + "\n");

			e = in.nextInt();
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
