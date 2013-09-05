import java.io.*;
import java.util.*;
class LCPC12F {

	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		for(int x = 1; x <= num; x++){
			string.append(x + ". ");
			int X = in.nextInt();
			int N = in.nextInt();
			long count = 0;
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int y = 0; y < N; y++){
				int i = in.nextInt();
				if(map.containsKey(i)) map.put(i, map.get(i) + 1);
				else map.put(i, 1);				
			}
			LOOP : for(Integer k : map.keySet()){
				if(k <= X/2){
					int Y = X-k;
					if(Y == k && map.get(Y) > 1){
						long n = map.get(k)-1;
						count += ((n*n) + n)/2;
					}else if(map.containsKey(Y) && Y != k){
						long I = map.get(k);
						long J = map.get(Y);
						count += I*J;
					}
				}
			}			
			string.append(count + "\n");
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
