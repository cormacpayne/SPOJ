import java.util.*;
import java.io.*;
class STAMPS {
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		for(int x = 1; x <= num; x++){
			string.append("Scenario #" + x + ":");
			int need = in.nextInt();
			int friends = in.nextInt();
			ArrayList<Integer> list = new ArrayList<Integer>();			
			for(int y = 0; y < friends; y++){
				list.add(in.nextInt());
			}			
			Collections.sort(list);
			Collections.reverse(list);			
			int total = 0;
			int count = 0;			
			while(need > total){
				total += list.get(count);
				count++;
				if(total >= need){
					break;
				}
				if(count == friends){
					break;
				}
			}
			if(count != friends || total >= need){
				string.append("\n" + count + "\n\n");
			}else{
				string.append("\nimpossible\n\n");
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
