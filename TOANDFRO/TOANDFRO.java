import java.util.*;
import java.io.*;
class TOANDFRO {
	
	public static void main(String[] args) throws Exception{
		
		Parser in = new Parser(System.in);
		int col = in.nextInt();
		while(col != 0){
			String text = in.next();
			int row = text.length()/col;
			char[][] board = new char[row][col];
			int count = 0;
			int counter = 0;
			while(count < row){
				if(count%2 == 0){
					for(int c = 0; c < col; c++){
						board[count][c] = text.charAt(counter);
						counter++;
					}
					count++;
				}else{
					for(int c = col-1; c >= 0; c--){
						board[count][c] = text.charAt(counter);
						counter++;
					}
					count++;
				}
			}
			for(int x = 0; x < col; x++){
				for(int y = 0; y < row; y++){
					System.out.print(board[y][x]);
				}
			}
			System.out.println();
			col = in.nextInt();
		}
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
