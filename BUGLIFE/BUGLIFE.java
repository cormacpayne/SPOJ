import java.io.*;
import java.util.*;
class BUGLIFE{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		for(int x = 1; x <= num; x++){
			string.append("Scenario #" + x + ":\n");
			int bugs = in.nextInt();
			int interactions = in.nextInt();
			boolean[][] graph = new boolean[bugs+1][bugs+1];
			
			for(int z = 0; z < interactions; z++){
				int a = in.nextInt();
				int b = in.nextInt();
				graph[a][b] = true;
				graph[b][a] = true;
			}
			int[] array = new int[bugs+1];
			boolean status = true;

			LOOP:for(int z = 1; z <= bugs; z++){
        			if(array[z] == 0){
               				array[z] = 1;
            			}
				for(int c = 1; c <= bugs; c++){
					if(graph[z][c]){
                  				if(array[c] == 0){
                     					if(array[z] == 1){
                        					array[c] = 2;
                     					}else{
                        					array[c] = 1;
                     					}
                  				}else if(array[c] == array[z]){
                     					status = false; 
                     					break LOOP;
                  				}
               				}
				}
			}
			if(status){
				string.append("No suspicious bugs found!\n");
			}else{
				string.append("Suspicious bugs found!\n");
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
