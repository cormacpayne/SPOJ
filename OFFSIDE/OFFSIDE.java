import java.util.*;
import java.io.*;
class OFFSIDE {
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		StringBuilder string = new StringBuilder();
		while(a != 0 && b != 0){
			ArrayList<Integer> attack = new ArrayList<Integer>();
			ArrayList<Integer> defend = new ArrayList<Integer>();
			
			for(int x = 0; x < a; x++){
				attack.add(in.nextInt());
			}
			for(int y = 0; y < b; y++){
				defend.add(in.nextInt());
			}
			
			Collections.sort(attack);
			Collections.sort(defend);
			
			if(attack.get(0) < defend.get(1)){
				string.append("Y\n");
			}else{
				string.append("N\n");
			}
			
			a = in.nextInt();
			b = in.nextInt();
		}
		System.out.println(string);
	}
}
	
	//you need to import java.io and throw exceptions in the methods that call nextInt()

	class Parser{
	   final private int BUFFER_SIZE = 1 << 16;
	 
	   private DataInputStream din;
	   private byte[] buffer;
	   private int bufferPointer, bytesRead;
	 
	   public Parser(InputStream in){
	      din = new DataInputStream(in);
	      buffer = new byte[BUFFER_SIZE];
	      bufferPointer = bytesRead = 0;
	   }
	 
	   public int nextInt() throws Exception{
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
	 
	   private void fillBuffer() throws Exception{
	      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
	      if (bytesRead == -1) buffer[0] = -1;
	   }
	 
	   private byte read() throws Exception{
	      if (bufferPointer == bytesRead) fillBuffer();
	      return buffer[bufferPointer++];
	   }
	}
