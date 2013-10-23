import java.io.*;
import java.util.*;
class UCV2013H {
	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int rows = in.nextInt();
		int cols = in.nextInt();
		
		while(rows != 0 && cols != 0){
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int[][] grid = new int[rows][cols];
			boolean[][] visited = new boolean[rows][cols];
			
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < cols; c++){
					grid[r][c] = in.nextInt();
				}
			}			
			
			int[] xValues = {0,1,0,-1};
			int[] yValues = {1,0,-1,0};
			
			Queue<Point> q = new LinkedList<Point>();
			Point current = null;
			
			for(int a = 0; a < rows; a++){
				for(int b = 0; b < cols; b++){
					if(!visited[a][b] && grid[a][b] == 1){
						visited[a][b] = true;
						int result = 1;
						q.add(new Point(a,b));
						
						while(!q.isEmpty()){
							current = q.remove();
							for(int i = 0; i < 4; i++){
								int x = current.x + xValues[i];
								int y = current.y + yValues[i];
								if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]){
									result++;
									visited[x][y] = true;
									q.add(new Point(x,y));
								}
							}
						}
						
						if(map.containsKey(result)){
							map.put(result, map.get(result)+1);
						}else{
							map.put(result, 1);
						}
					}
				}
			}
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			int sum = 0;
			for(Integer key : map.keySet()){
				sum += map.get(key);
				list.add(key);
			}
			
			Collections.sort(list);
			
			string.append(sum + "\n");
			
			for(Integer l : list){
				string.append(l + " " + map.get(l) + "\n");
			}
			rows = in.nextInt();
			cols = in.nextInt();
		}
		System.out.print(string);
	}
	
	static class Point{
		int x,y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
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
