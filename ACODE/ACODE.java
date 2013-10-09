import java.io.*;
import java.util.*;
class ACODE {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		String number = in.next();
		while(!number.equals("0")){
			int length = number.length();
			long[] dp = new long[length];
			dp[0] = 1;	
			if(length > 1){		
				int num = Integer.parseInt(number.substring(0,2));
				if(num > 10 && num < 27 && num != 20){
					if(2 < length){
						if(number.charAt(2) == '0'){
							dp[1] = dp[0];
						}else{
							dp[1] = dp[0] + 1;
						}
					}else{
						dp[1] = dp[0] + 1;
					}	
				}else{
					dp[1] = dp[0];
				}
				
				int index = 2;
				while(index < length){
					num = Integer.parseInt(number.substring(index-1,index+1));
					if(num > 10 && num < 27 && num != 20){
						if(index+1 < length){
							if(number.charAt(index+1) == '0'){
								dp[index] = dp[index-1];
							}else{
								dp[index] = dp[index-1] + dp[index-2];
							}
						}else{
							dp[index] = dp[index-1] + dp[index-2];
						}
					}else{
						dp[index] = dp[index-1];
					}
					index++;
					}
			}
			string.append(dp[length-1] + "\n");
			number = in.next();
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
      StringBuilder ret = new StringBuilder();
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
