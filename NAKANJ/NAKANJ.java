import java.io.*;
import java.util.*;
class NAKANJ {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			boolean[][] visited = new boolean[8][8];
			String start = in.next();
			String end = in.next();
			
			int startX = start.charAt(0) - 'a';
			int startY = Integer.parseInt(start.substring(1))-1;
			
			int endX = end.charAt(0) - 'a';
			int endY = Integer.parseInt(end.substring(1))-1;
			
			Queue<Point> q = new LinkedList<Point>();
			Point current = null;
			int[] xValues = {2, 2, -2, -2, 1, 1, -1, -1};
			int[] yValues = {1, -1, 1, -1, 2, -2, 2, -2};
			q.add(new Point(startX, startY, 0));
			
			while(!q.isEmpty()){
				current = q.remove();
				if(current.x == endX && current.y == endY){
					break;
				}
				
				for(int j = 0; j < 8; j++){
					int x = current.x + xValues[j];
					int y = current.y + yValues[j];
					
					if(x >= 0 && x < 8 && y >= 0 && y < 8 && !visited[x][y]){
						visited[x][y] = true;
						q.add(new Point(x, y, current.dist+1));
					}
				}
			}
			
			string.append(current.dist + "\n");
		}
		System.out.print(string);
	}
	
	static class Point{
		int x,y,dist;
		
		public Point(int x, int y, int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
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
