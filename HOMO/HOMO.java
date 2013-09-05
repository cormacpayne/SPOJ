import java.util.*;
import java.io.*;
class HOMO{
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int num = in.nextInt();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		String current = "neither";
		int count = 0;
		for(int x = 0; x < num; x++){
			String line = in.next();
			int n = in.nextInt();
			
			if(line.equals("insert")){
				if(map.containsKey(n)){
					map.put(n, map.get(n)+1);
					if(map.get(n) == 2){
						if(current.equals("neither")){
							current = "homo";
							count++;
						}else{
							current = "both";
							count++;
						}
					}				
				}else{
					boolean status = map.isEmpty();
					map.put(n, 1);
					if(status){
						current = "neither";
					}else{
						if(current.equals("homo") || current.equals("both")){
							current = "both";
						}else{
							current = "hetero";
						}						
					}
				}				
			}else{
				if(map.containsKey(n)){
					if(map.get(n) == 1){
						map.remove(n);
						if(map.size() <= 1){
							if(count == 1){
								current = "homo";
							}else{
								current = "neither";
							}
						}
					}else{
						map.put(n, map.get(n)-1);
						if(map.get(n) == 1){
							count--;
							if(current.equals("homo")){
								current = "neither";
							}else if(current.equals("both")){
								current = "hetero";
								if(count > 0){
									current = "both";
								}
							}
						}else{
							
						}
					}
				}else{}
				
			}
			string.append(current + "\n");
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
