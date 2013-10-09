import java.io.*;
import java.util.*;
class PRIME1{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		boolean[] primes = new boolean[32000];
		Arrays.fill(primes, true);

		for(int i = 2; i < 180; i++){
			int start = i*i;
			while(start < 32000){
				primes[start] = false;
				start += i;
			}
		}

		int[] p = new int[3432];
		int index = 0;
		for(int k = 2; k < 32000; k++){
			if(primes[k]){
				p[index] = k;
				index++;
			}
		}

		int t = in.nextInt();
		for(int z = 0; z < t; z++){
			int n = in.nextInt();
			int m = in.nextInt();

			int[] list = new int[m-n+1];
			for(int q = 0; q < p.length; q++){
				int pr = p[q];
				if(pr*pr > m){
					break;
				}

				int lower = (n/pr)*pr;

				for(int l = lower; l <= m; l+=pr){
					if(l != pr && l >= n){
						list[l-n] = 1;
					}
				}
			}

			for(int num = 0; num < m-n+1; num++){
				if(list[num] == 0 && n+num != 1){
					int result = num+n;
					string.append(result + "\n");
				}
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
