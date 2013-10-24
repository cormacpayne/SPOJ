import java.io.*;
import java.util.*;
class DCEPC706 {
	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int rows = in.nextInt();
			int cols = in.nextInt();
			char[][] grid = new char[rows+2][cols+2];
			
			PriorityQueue<Point> pq1 = new PriorityQueue<Point>();
			PriorityQueue<Point> pq2 = new PriorityQueue<Point>();
			PriorityQueue<Point> pq3 = new PriorityQueue<Point>();
			
			int[][] dist1 = new int[rows+2][cols+2];
			int[][] dist2 = new int[rows+2][cols+2];
			int[][] dist3 = new int[rows+2][cols+2];
			
			boolean[][] visited1 = new boolean[rows+2][cols+2];
			boolean[][] visited2 = new boolean[rows+2][cols+2];
			boolean[][] visited3 = new boolean[rows+2][cols+2];
			
			for(int j = 0; j < cols+2; j++){
				grid[0][j] = '.';
				grid[rows+1][j] = '.';
			}
			
			for(int r = 1; r < rows+1; r++){
				grid[r][0] = '.';
				String line = in.next();
				for(int c = 1; c < cols+1; c++){
					grid[r][c] = line.charAt(c-1);
					
					if(grid[r][c] == '1'){
						pq1.add(new Point(r,c,0));
						visited1[r][c] = true;
						grid[r][c] = '.';
						
					}else if(grid[r][c] == '2'){
						pq2.add(new Point(r,c,0));
						visited2[r][c] = true;
						grid[r][c] = '.';
						
					}else if(grid[r][c] == '3'){
						pq3.add(new Point(r,c,0));
						visited3[r][c] = true;
						grid[r][c] = '.';
					}
				}
				grid[r][cols+1] = '.';
			}
			
			Point current = null;
			int[] xValues = {1,0,-1,0};
			int[] yValues = {0,-1,0,1};
			
			while(!pq1.isEmpty()){
				current = pq1.remove();
				dist1[current.x][current.y] = current.dist;
				for(int k = 0; k < 4; k++){
					int x = current.x + xValues[k];
					int y = current.y + yValues[k];
					if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '.' && !visited1[x][y]){
						visited1[x][y] = true;
						pq1.add(new Point(x,y,current.dist+1));
					}
				}
			}
			
			while(!pq2.isEmpty()){
				current = pq2.remove();
				dist2[current.x][current.y] = current.dist;
				for(int k = 0; k < 4; k++){
					int x = current.x + xValues[k];
					int y = current.y + yValues[k];
					if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '.' && !visited2[x][y]){
						visited2[x][y] = true;
						pq2.add(new Point(x,y,current.dist+1));
					}
				}
			}
			
			while(!pq3.isEmpty()){
				current = pq3.remove();
				dist3[current.x][current.y] = current.dist;
				for(int k = 0; k < 4; k++){
					int x = current.x + xValues[k];
					int y = current.y + yValues[k];
					if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '.' && !visited3[x][y]){
						visited3[x][y] = true;
						pq3.add(new Point(x,y,current.dist+1));
					}
				}
			}
			
			int min = 500;
			for(int row = 0; row < rows+2; row++){
				for(int col = 0; col < cols+2; col++){
					if(grid[row][col] == '.' && visited1[row][col] && visited2[row][col] && visited3[row][col]){
						int c = Math.min(min, Math.max(dist1[row][col], Math.max(dist2[row][col], dist3[row][col])));
						if(c < min){
							min = c;
						}
					}
				}
			}
			string.append(min + "\n");			
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
