import java.io.*;
import java.util.*;

class MTWALK {  

	// for commenting purposes: start = grid[0][0] , finish = grid[n - 1][n - 1]

	// for this problem, I used a "window algorithm" where I tried to find a "window" ([min, max]) that would contain the minimum and maximum points along a
	// path from start to finish, while minimizing the size of the window....to do this, we binary search (BS) to find the size of the window, and then we
	// create a window and see if all the points along a path from start to finish will fit inside of this window inclusively...we know that the min of the
	// window can only go up to the min of start and finish, because anything greater will not include such min, so we loop through [0...min(start,finish)],
	// setting that as our min of the window, and allowing the max of the window to be min + d, where d is the size of the window that we BS for...when we can
	// minimize d and still get a window that can get us from start to finish, we have our answer.

	public static int[][] grid;

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		int n = in.nextInt();

		grid = new int[n][n];

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				grid[r][c] = in.nextInt();
			}
		}

		boolean[][] visited;
		int[] xMoves = {0, 0, -1, 1};
		int[] yMoves = {1, -1, 0, 0};

		// use BS to find the minimum difference between the min and max grid point along a path from start to finish

		int high = 110;                                         // the greatest difference between start and finish can be 110 (0 and 110)
		int low = Math.abs(grid[0][0] - grid[n - 1][n - 1]);    // the minimum difference between start and finish is the absolute value of their difference
		int min = Math.min(grid[0][0], grid[n - 1][n - 1]);     // the minimum of start and finish will be the "maximum min" of any window

		// start BS here
		while (low <= high) {
			int mid = (low + high) / 2;

			boolean flag = false; // flag keeps track of if we have made it to the finish with any window of size d (current mid)

			for (int k = 0; k <= min; k++) {   // k will be the min of the window, kk will be the max of the window
				int kk = k + mid;

				visited = new boolean[n][n];

				Queue<Point> q = new LinkedList<Point>();
				Point current = null;
				q.add(new Point(0, 0, grid[0][0], grid[0][0])); // start from start and add it to the queue
				visited[0][0] = true;

				while (!q.isEmpty()) {
					current = q.remove();
					
					if (current.x == n - 1 && current.y == n - 1) break; // if we have made it to the finish, break out of the loop

					for (int i = 0; i < 4; i++) {
						int x = current.x + xMoves[i];
						int y = current.y + yMoves[i];

						if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
							visited[x][y] = true;
							int tempMax = Math.max(current.max, grid[x][y]); // tempMax and tempMin determine what will be max and min if we were to move
							int tempMin = Math.min(current.min, grid[x][y]); // to this next point, so we see if they fit in the window

							if (tempMax <= kk && tempMin >= k) {          // IMPORTANT: if the tempMin and tempMax fit inside of the window we are looking at,
								q.add(new Point(x, y, tempMin, tempMax)); // we can continue with the path to the finish
							}
						}
					}
				}

				if (current.x == n - 1 && current.y == n - 1) { // we broke out of the loop if the current point was the finish
					flag = true;
					break;
				} 
			}

			if (flag) {          // if we were able to get to the finish with any window of size d (mid), decrease the size of high (decrease window size)
				high = mid - 1;
			} else {			 // if we were unable to get to the finish with any window of size d (mind), increase the size of low (increase window size)
				low = mid + 1;
			}
		}

		System.out.print(high+1);
	}
}

class Point {
	int x, y, min, max, diff;

	public Point(int x, int y, int min, int max) {
		this.x = x;
		this.y = y;
		this.min = min;
		this.max = max;
		this.diff = max - min;
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
