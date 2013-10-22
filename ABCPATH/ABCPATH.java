import java.io.*;
import java.util.*;
class ABCPATH {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int rows = in.nextInt();
		int cols = in.nextInt();
		int count = 1;
		while(rows != 0 && cols != 0){
			char[][] grid = new char[rows+2][cols+2];
			boolean[][] visited = new boolean[rows+2][cols+2];
			Queue<Letter> q = new LinkedList<Letter>();
			for(int i = 0; i < cols+2; i++){
				grid[0][i] = '#';
				grid[rows+1][i] = '#';
			}
			
			for(int j = 1; j < rows+1; j++){
				grid[j][0] = '#';
				String line = in.next();
				for(int k = 0; k < line.length(); k++){
					grid[j][k+1] = line.charAt(k);
					if(grid[j][k+1] == 'A'){
						q.add(new Letter(j, k+1, 1, 0));
						visited[j][k+1] = true;
					}
				}
				grid[j][cols+1] = '#';
			}
			
			int[] xValues = {0,1,-1,0,1,1,-1,-1};
			int[] yValues = {1,0,0,-1,1,-1,1,-1};
			Letter current = null;
			
			while(!q.isEmpty()){
				current = q.remove();
				for(int m = 0; m < 8; m++){
					int x = xValues[m] + current.x;
					int y = yValues[m] + current.y;
					
					if(grid[x][y]-'A' == current.charValue+1 && !visited[x][y]){
						visited[x][y] = true;
						q.add(new Letter(x,y,current.dist+1, grid[x][y]-'A'));
					}
				}
			}
			
			if(current == null){
				string.append("Case " + count + ": 0\n");
			}else{
				string.append("Case " + count + ": " + current.dist + "\n");
			}
			
			rows = in.nextInt();
			cols = in.nextInt();
			count++;
		}
		System.out.print(string);
	}
}

class Letter{
	int x,y,dist,charValue;
	
	public Letter(int x, int y, int dist, int charValue){
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.charValue = charValue;
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
