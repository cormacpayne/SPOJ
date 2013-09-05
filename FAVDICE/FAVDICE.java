import java.io.*;
import java.util.*;
class FAVDICE{
	
	public static void main(String[] args) throws Exception{
		
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		double[] numbers = new double[1000];
		numbers[0] = 1.00;
		double diff = 2;
		for(int y = 1; y < 1000; y++){
			numbers[y] = numbers[y-1] + diff;
			diff += (double)1/(y+1);
		}
		int num = in.nextInt();
		for(int x = 0; x < num; x++){
			string.append(String.format("%.2f\n", numbers[in.nextInt()-1]));
		}
		System.out.print(string);
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
