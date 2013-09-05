import java.util.*;
import java.io.*;
class FACEFRND {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		Set<Integer> friends = new HashSet<Integer>();
		Set<Integer> fofs = new HashSet<Integer>();
		int f = in.nextInt();
		for(int i = 0; i < f; i++){
			int friend = in.nextInt();
			friends.add(friend);
			if(fofs.contains(friend)){
				fofs.remove(friend);
			}
			int count = in.nextInt();
			for(int j = 0; j < count; j++){
				int fr = in.nextInt();
				if(!friends.contains(fr)){
					fofs.add(fr);
				}
			}
		}
		System.out.print(fofs.size());
	}
}

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
