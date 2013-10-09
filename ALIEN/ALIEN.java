import java.io.*;
import java.util.*;
class ALIEN {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int window = in.nextInt();
			int MAX = in.nextInt();
			int[] station = new int[window];
			int sum = 0;
			int min = 101;
			int minSum = Integer.MAX_VALUE;
			
			for(int j = 0; j < window; j++){
				station[j] = in.nextInt();
				sum += station[j];
				min = Math.min(min, station[j]);
			}
			
			if(sum <= MAX){
				string.append(sum + " " + window + "\n");
			}else{
				int start = 2;
				int end = window;
				int mid = (start + end)/2;
				
				while(true){
					sum = 0;
					minSum = Integer.MAX_VALUE;
					for(int k = 0; k < mid; k++){
						sum += station[k];
					}
					if(sum <= MAX){
						minSum = sum;
					}
					for(int l = 0; l < station.length - mid; l++){
						sum -= station[l];
						sum += station[mid+l];
						if(sum <= MAX){
							minSum = Math.min(sum, minSum);
						}
					}
						
					if(start == mid || end == 2){
						break;
					}
					
					if(minSum == Integer.MAX_VALUE){
						end = mid;
						mid = (start+end)/2;
					}else{
						start = mid;
						mid = (start+end)/2;
					}						
				}				
				
				if(mid == 2 && minSum == Integer.MAX_VALUE){
					string.append(min + " 1\n");
				}else{
					string.append(minSum + " " + mid + "\n");
				}
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
