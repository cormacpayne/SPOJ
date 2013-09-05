import java.io.*;
import java.util.*;
class SBETS {
	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int x = 0; x < t; x++){
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int y = 0; y < 16; y++){
				String team1 = in.next();
				String team2 = in.next();
				int t1 = in.nextInt();
				int t2 = in.nextInt();
				if(t1 > t2){
					if(map.containsKey(team1)){
						map.put(team1, map.get(team1)+1);
					}else{
						map.put(team1, 1);
					}
				}else{
					if(map.containsKey(team2)){
						map.put(team2, map.get(team2)+1);
					}else{
						map.put(team2, 1);
					}
				}
			}
			for(String s : map.keySet()){
				if(map.get(s) == 4){
					string.append(s + "\n");
					break;
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
