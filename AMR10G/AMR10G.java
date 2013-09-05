import java.util.*;
import java.io.*;
class AMR10G {
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		
		for(int x = 0; x < num; x++){
			int n = in.nextInt();
			int k = in.nextInt();
			ArrayList<Integer> kids = new ArrayList<Integer>();		
			
			for(int y = 0; y < n; y++){
				kids.add(in.nextInt());
			}
			
			Collections.sort(kids);
			if(k == 1){
				string.append("0\n");
			}else if(k == n){
				string.append(kids.get(n-1) - kids.get(0) + "\n");
			}else{
				int min = Integer.MAX_VALUE;
				for(int z = 0; z < kids.size()-k+1; z++){
					if(kids.get(z+k-1)-kids.get(z) < min){
						min = kids.get(z+k-1)-kids.get(z);
					}
				}
				string.append(min + "\n");
			}			
		}		
		System.out.println(string);		
	}
}

	
	//you need to import java.io and throw exceptions in the methods that call nextInt()

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
