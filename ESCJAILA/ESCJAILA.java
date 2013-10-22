import java.io.*;
import java.util.*;
public class ESCJAILA {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int rows = in.nextInt();
		int cols = in.nextInt();
		
		while(rows != -1 && cols != -1){
			char[][] grid = new char[rows][cols];
			int startX = -1;
			int startY = -1;
			
			for(int i = 0; i < rows; i++){
				String line = in.next();
				for(int j = 0; j < cols; j++){
					grid[i][j] = line.charAt(j);
					if(grid[i][j] == 'H'){
						startX = i;
						startY = j;
					}
				}
			}
			
			string.append(bfs(startX, startY, grid) + "\n");
			
			rows = in.nextInt();
			cols = in.nextInt();
		}
		System.out.print(string);
	}
	
	public static int bfs(int startX, int startY, char[][] grid){
		//if the starting position has no wall on at least one side, break out
		if(startX == grid.length-1 || startX == 0 || startY == grid[0].length-1 || startY == 0){
			return 1;
		}
		
		//keep track of the total distance traveled on each floor
		int[][][] distance = new int[grid.length][grid[0].length][3];
		for (int[][] row: distance)
		{
		    for (int[] innerRow: row)
		    {
		        Arrays.fill(innerRow, Integer.MAX_VALUE);
		    }
		}
		
		int[] xValues = {-1, 1, 0, 0};
		int[] yValues = {0, 0, -1, 1};
		
		int currentFloor;
		Point current;
		Queue<Point> q = new LinkedList<Point>();
		
		q.add(new Point(startX, startY, 0));
		distance[startX][startY][0] = 0;
		
		while(!q.isEmpty()){
			current = q.remove();
			
			for(int i = 0; i < 4; i++){
				int x = current.row + xValues[i];
				int y = current.col + yValues[i];
				
				//if the adjacent point isn't out of bounds and isn't a wall
				if(x >= 0 && x <= grid.length-1 && y >= 0 && y <= grid[0].length-1 && grid[x][y] != 'W'){
					//if the adjacent point is a door and we are on a closed floor, continue in the for loop 
					if(current.floor != 1 && grid[x][y] == 'D'){
						continue;
					}
					//if the adjacent point is on the boundaries (Harry can escape), move to that point
					//and then escape from jail, resulting in 2 moves
					if(x == 0 || x == grid.length-1 || y == 0 || y == grid[0].length-1){
						return distance[current.row][current.col][current.floor] + 2;
					}
						
					//if the adjacent point is an "open-door space", switch floors
					if(grid[x][y] == 'O'){
						currentFloor = 1;
					//if the adjacent point is a "close-door space", switch floors
					}else if(grid[x][y] == 'C'){
						currentFloor = 2;
					//if the adjacent point is a "normal space", stay on the current floor
					}else{
						currentFloor = current.floor;
					}
					//if the current distance + 1 is smaller than the adjacent point distance, set the adjacent
					//point's distance to the current distance + 1 and add it to the queue
					if(distance[x][y][currentFloor] > distance[current.row][current.col][current.floor] + 1){
						q.add(new Point(x,y,currentFloor));
						distance[x][y][currentFloor] = distance[current.row][current.col][current.floor] + 1;
					}
				}
			}
		}		
		return -1;
	}
}

class Point{
	int row, col, floor;
	
	public Point(int row, int col, int floor){
		this.row = row;
		this.col = col;
		this.floor = floor;
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
