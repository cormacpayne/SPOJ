import java.io.*;
import java.util.*;
class SHOP{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int cols = in.nextInt();
		int rows = in.nextInt();
		while(rows != 0 && cols != 0){
			int[][] grid = new int[rows+2][cols+2];
			boolean[][] visited = new boolean[rows+2][cols+2];
			int startX = -1;
			int startY = -1;
			int endX = -1;
			int endY = -1;

			for(int i = 0; i < cols+2; i++){
				grid[0][i] = -1;
				grid[rows+1][i] = -1;
			}

			for(int j = 1; j < rows+1; j++){
				grid[j][0] = -1;
				String line = in.next();
				for(int k = 1; k < cols+1; k++){
					if(line.charAt(k-1) == 'X'){
						grid[j][k] = -1;
					}else if(line.charAt(k-1) == 'S'){
						startX = j;
						startY = k;
						grid[j][k] = 0;
					}else if(line.charAt(k-1) == 'D'){
						endX = j;
						endY = k;
						grid[j][k] = 0;
					}else{
						grid[j][k] = Integer.parseInt(line.substring(k-1, k));
					}
				}
				grid[j][cols+1] = -1;
			}

			int[] xValues = {1, 0, -1, 0};
            		int[] yValues = {0, -1, 0, 1};
			PriorityQueue<Point> pq = new PriorityQueue<Point>();
			Point current = null;
			pq.add(new Point(startX, startY, 0));
			visited[startX][startY] = true;

			while(pq.size() != 0){
				current = pq.remove();
				if(current.x == endX && current.y == endY){
					break;
				}
				for(int f = 0; f < 4; f++){
					int x = xValues[f] + current.x;
					int y = yValues[f] + current.y;
					if(grid[x][y] != -1){
						if(!visited[x][y]){
							visited[x][y] = true;
							pq.add(new Point(x,y,current.dist+grid[x][y]));
						}
					}
				}
			}

			string.append(current.dist + "\n");

			cols = in.nextInt();
			rows = in.nextInt();
		}
		System.out.print(string);
	}

	static class Point implements Comparable<Point>{
		int x,y,dist;

		public Point(int x, int y, int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		public int compareTo(Point p){
			return (this.dist > p.dist) ? 1 : -1;
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
