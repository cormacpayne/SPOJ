import java.io.*;
import java.util.*;
class PCPC12J {

	public static void main(String[] args) throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int x = 0; x < t; x++){
			int n = in.nextInt();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i = 0; i < n; i++){
				int s = in.nextInt();
				if(map.containsKey(s)){
					map.put(s, map.get(s)+1);
				}else{
					map.put(s, 1);
				}
			}
			ArrayList<Integer> values = new ArrayList<Integer>();
			for(Integer j : map.keySet()){
				values.add(map.get(j));
			}
			Collections.sort(values);
			Collections.reverse(values);
			int index = 0;
			LOOP:while(true){
				if(index == values.size()){
					string.append("-1\n");
					break LOOP;
				}
				for(Integer j : map.keySet()){
					if(map.get(j) == values.get(index)){
						if(map.get(j)%j == 0){
							string.append(j + "\n");
							break LOOP;
						}
					}
				}
				index++;
			}
			string.append("\n");
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
