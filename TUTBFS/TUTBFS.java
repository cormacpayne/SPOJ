import java.io.*;
import java.util.*;
class TUTBFS{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int n = in.nextInt();
			int m = in.nextInt();
			char[][] graph = new char[n+2][m+2];
			boolean[][] visited = new boolean[n+2][m+2];
			int[] xValues = {1, -1, 1, -1, 0, 0, -1, 1};
			int[] yValues = {1, -1, 0, 0, -1, 1, 1, -1};
			for(int a = 0; a < m+2; a++){
				graph[0][a] = '#';
				graph[n+1][a] = '#';
			}
			for(int j = 1; j < n+1; j++){
				String line = in.next();
				graph[j][0] = '#';
				for(int k = 1; k < m+1; k++){
					graph[j][k] = line.charAt(k-1);
				}
				graph[j][m+1] = '#';
			}
			Queue<Point> queue = new LinkedList<Point>();
			queue.add(new Point(1,1,0));
			Point current = null;
			while(!queue.isEmpty()){
				current = queue.remove();
				visited[current.x][current.y] = true;
				for(int z = 0; z < 8; z++){
					int x = current.x+xValues[z];
					int y = current.y+yValues[z];
					if(!visited[x][y] && graph[x][y] == '.'){
						queue.add(new Point(x,y,current.distance+1));
						visited[x][y] = true;
					}
				}
			}
			string.append(current.distance + "\n");
		}
		System.out.print(string);
	}
}

class Point{
	int x,y,distance;
	
	public Point(int x, int y, int distance){
		this.x = x;
		this.y = y;
		this.distance = distance;
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
