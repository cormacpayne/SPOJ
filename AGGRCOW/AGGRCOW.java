import java.util.*;
import java.io.*;
class AGGRCOW{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		
		for(int x = 0; x < num; x++){
			int stalls = in.nextInt();
			int cows = in.nextInt();
			int[] s = new int[stalls];
			for(int y =0; y < stalls; y++){
				s[y] = in.nextInt();
			}			
			Arrays.sort(s);
			if(cows == 2){
				string.append(s[s.length-1] - s[0] + "\n");
			}else{	
				int max = (s[s.length-1] - s[0])/(cows-1);
				int min = 1;
				int mid = (max + min)/2;
				boolean status = true;
				
				while(status){
					int count = 1;
					int lastCow = 0;
					int c = 0;
					
					while(count < cows){
						c = lastCow + 1;
						
						while((c <= s.length-1) && ((s[c] - s[lastCow]) < mid)){
							c++;
						}
																		
						if((c > s.length-1)){
							max = mid - 1;
							break;
						}
						lastCow = c;
						count++;
					}
					if(c <= s.length-1){
						min = mid + 1;
					}
					if(min > max){
						status = false;
					}
					mid = (max + min)/2;
				}
				
				string.append(mid + "\n");
			}
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
