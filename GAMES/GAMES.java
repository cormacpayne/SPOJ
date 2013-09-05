import java.io.*;
import java.util.*;

class GAMES{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		double eps = 0.000000001;
		int num = in.nextInt();
		for(int x = 0; x < num; x++){
			double n = Double.parseDouble(in.next());
			if(n/(int)n == 1.0){
				string.append("1\n");
			}else{
				String temp = Double.toString(n);
				int length = temp.substring(temp.indexOf(".") + 1).length();
				int number = Integer.parseInt(temp.substring(temp.indexOf(".")+1));		
				int power = (int)Math.pow(10, length);
				int result = lcm(number, power);
				int answer = result/number;
				string.append(answer + "\n");		
			}
			string.append("\n");
		}
		System.out.print(string);
	}
	
	public static int gcd(int a, int b){
		while(b > 0){
			int temp = b; 
			b = a%b;
			a = temp;
		}
		return a;
	}
	
	public static int lcm(int a, int b){
		return a * (b / gcd(a, b));
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
   
    public double nextDouble() throws Exception
   {
      double ret = 0;
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
