import java.util.*;
import java.math.*;
import java.io.*;
class FCTRL2 {
	
	static HashMap<Integer, BigInteger> map = new HashMap<Integer, BigInteger>();
	
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		for(int x = 0; x < num; x++){
			BigInteger t = factorial(in.nextInt());
			string.append(t + "\n");
		}
		System.out.println(string);
	}
	
	public static BigInteger factorial(int n){
		BigInteger ret;
		
		if(n == 0){
			return BigInteger.ONE;
		}
		if(null != (ret = map.get(n))){
			return ret;
		}
		ret = BigInteger.valueOf(n).multiply(factorial(n-1));
		map.put(n, ret);
		return ret;
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
