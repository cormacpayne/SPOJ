import java.io.*;
import java.util.*;
class EGYPIZZA{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		int num = in.nextInt();
		int[] a = new int[3];
		int count = 1;
		for(int i = 0; i < num; i++){
			String p = in.next();
			if(p.equals("1/4")){
				a[0]++;
			}else if(p.equals("1/2")){
				a[1]++;
			}else{
				a[2]++;
			}
		}
		
		//System.out.println("1. COUNT = " + count + ", 1/4 = " + a[0] + ", 1/2 = " + a[1] + ", 3/4 = " + a[2]);	

		int temp = -1;

		temp = Math.min(a[0], a[2]);
		count+=temp;
		a[0]-=temp;
		a[2]-=temp;

		//System.out.println("2. COUNT = " + count + ", 1/4 = " + a[0] + ", 1/2 = " + a[1] + ", 3/4 = " + a[2]);

		count+=a[1]/2;
		a[1]%=2;

		//System.out.println("3. COUNT = " + count + ", 1/4 = " + a[0] + ", 1/2 = " + a[1] + ", 3/4 = " + a[2]);

		if(a[0] > 1){
			temp = Math.min(a[0]/2, a[1]);
			count+=temp;
			a[1]-=temp;
			a[0]-=temp*2;
			//System.out.println("4. COUNT = " + count + ", 1/4 = " + a[0] + ", 1/2 = " + a[1] + ", 3/4 = " + a[2]);
		}else{
			temp = Math.min(a[0], a[1]);
			count+=temp;
			a[0]-=temp;
			a[1]-=temp;
			//System.out.println("5. COUNT = " + count + ", 1/4 = " + a[0] + ", 1/2 = " + a[1] + ", 3/4 = " + a[2]);
		}

		if(a[0]%4 > 0){
			count++;
		}

		//System.out.println("6. COUNT = " + count + ", 1/4 = " + a[0] + ", 1/2 = " + a[1] + ", 3/4 = " + a[2]);

		count+=a[0]/4 + a[1] + a[2];

		System.out.print(count);
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
