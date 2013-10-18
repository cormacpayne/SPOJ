import java.io.*;
import java.util.*;
class BYTESE1{
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int rows = in.nextInt();
			int cols = in.nextInt();
			int[][] grid = new int[rows+2][cols+2];
			//int[][] sums = new int[rows+2][cols+2];
			boolean[][] visited = new boolean[rows+2][cols+2];

			for(int j = 0; j < cols+2; j++){
				grid[0][j] = -1;
				grid[rows+1][j] = -1;
			}

			for(int k = 1; k < rows+1; k++){
				grid[k][0] = -1;
				for(int m = 1; m < cols+1; m++){
					grid[k][m] = in.nextInt();
					//sums[k][m] = Integer.MAX_VALUE;
				}
				grid[k][cols+1] = -1;
			}

			int destinationX = in.nextInt();
			int destinationY = in.nextInt();
			int time = in.nextInt();

			int[] xValues = {1, 0, -1, 0};
			int[] yValues = {0, -1, 0, 1};

			PriorityQueue<Room> pq = new PriorityQueue<Room>();
			Room current = null;
			pq.add(new Room(1,1, grid[1][1]));
			//sums[1][1] = grid[1][1];
			while(pq.size() != 0){
				current = pq.remove();
				//System.out.printf("CURRENT = (%d, %d), CURRENT.DIST = %d\n", current.x, current.y, current.dist);
				if(current.x == destinationX && current.y == destinationY){
					break;
				}
				for(int f = 0; f < 4; f++){
					int x = xValues[f] + current.x;
					int y = yValues[f] + current.y;
					if(grid[x][y] != -1){
						//sums[x][y] = Math.min(sums[x][y], sums[current.x][current.y] + grid[x][y]);
						//System.out.printf("SUMS[%d][%d] = %d\n", x, y, sums[x][y]);
						if(!visited[x][y]){
							visited[x][y] = true;
							pq.add(new Room(x,y,current.dist+grid[x][y]));
						}
					}
				}
			}
			//System.out.printf("TIME = %d, SUMS[%d][%d] = %d\n", time, destinationX, destinationY, sums[destinationX][destinationY]);
			int result = time - current.dist;
			if(result > 0){
				string.append("YES\n" + result + "\n");
			}else{
				string.append("NO\n");
			}
		}
		System.out.print(string);
	}

	static class Room implements Comparable<Room>{
		int x,y,dist;

		public Room(int x, int y, int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		public int compareTo(Room r){
			return (this.dist > r.dist) ? 1 : -1;
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
