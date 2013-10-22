import java.io.*;
import java.util.*;
class ESCJAILA {
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
			
			string.append(bfs(startX, startY, grid)+1 + "\n");
			
			rows = in.nextInt();
			cols = in.nextInt();
		}
		System.out.print(string);
	}
	
	public static int bfs(int startX, int startY, char[][] grid){
		if(startX == grid.length-1 || startX == 0 || startY == grid[0].length-1 || startY == 0){
			return 0;
		}
		
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
		int row, col, floor, currentFloor;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(startX);
		q.add(startY);
		q.add(0);
		distance[startX][startY][0] = 0;
		
		while(!q.isEmpty()){
			row = q.remove();
			col = q.remove();
			floor = q.remove();
			
			for(int i = 0; i < 4; i++){
				int x = row + xValues[i];
				int y = col + yValues[i];
				
				if(x >= 0 && x <= grid.length-1 && y >= 0 && y <= grid[0].length-1 && grid[x][y] != 'W'){
					if(floor != 1 && grid[x][y] == 'D'){
						continue;
					}
					
					if(x == 0 || x == grid.length-1 || y == 0 || y == grid[0].length-1){
						return distance[row][col][floor] + 1;
					}
						
					if(grid[x][y] == 'O'){
						currentFloor = 1;
					}else if(grid[x][y] == 'C'){
						currentFloor = 2;
					}else{
						currentFloor = floor;
					}
					
					if(distance[x][y][currentFloor] > distance[row][col][floor] + 1){
						q.add(x);
						q.add(y);
						q.add(currentFloor);
						distance[x][y][currentFloor] = distance[row][col][floor] + 1;
						//System.out.printf("distance[%d][%d][%d] = %d\n", x, y, currentFloor, distance[x][y][currentFloor]);
					}
				}
			}
		}
		
		return -2;
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

