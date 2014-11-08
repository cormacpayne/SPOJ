import java.util.*;

class CLEANRBT {
	
	static char[][] grid;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		
		// keep track of the powers of two up to 1024
		List<Integer> powerOfTwo = new ArrayList<Integer>();
		for (int i = 0; i <= 10; i++) powerOfTwo.add((1 << i));
		
		int col = in.nextInt();
		int row = in.nextInt();
		while (row != 0 && col != 0) {
			grid = new char[row][col];
			int n = 0;
			Point start = null;
			Point[] dirty = new Point[11];
			
			for (int r = 0; r < row; r++) {
				String line = in.next();
				for (int c = 0; c < col; c++) {
					grid[r][c] = line.charAt(c);
					if (grid[r][c] == 'o') start = new Point(r, c);
					else if (grid[r][c] == '*') dirty[n++] = new Point(r, c);
				}
			}
			
			int max = (1 << n);
			int[][] dp = new int[max][n]; // dp[mask][i] = shortest distance visiting 1-bits in mask ending on i
			int[][] distances = new int[n][n]; // keep track of the distance between two dirty points
			for (int[] roo : dp) Arrays.fill(roo, 200000);
			for (int[] dis : distances) Arrays.fill(dis, -1);
			int ans = 200000;
			
			for (int mask = 1; mask < max; mask++) {
			  // if we have a mask where only one point is visited, find the distance from the start to that point
				if (powerOfTwo.contains(mask)) {
					int i = powerOfTwo.indexOf(mask);
					dp[mask][i] = distance(start, dirty[i]);
					// if we cannot reach that point, return -1
					if (dp[mask][i] == 200000) {
						ans = -1;
						break;
					}
				}
				
				// for every dirty point
				for (int i = 0; i < n; i++) {
				  // if we have not visited the dirty point
					if ((mask & (1 << i)) == 0) {
					  // go through every other dirty point
						for (int j = 0; j < n; j++) {
							int dist = -1;
							// see if we have already calculated the distance from i to j or vice versa
							if (distances[i][j] == -1) distances[i][j] = distances[j][i] = distance(dirty[i], dirty[j]);
							// the new bitmask ending on i will have the minimum between the the subset ending on i (if calculated)
							// and going from the current position to the next position
							if (i != j) dp[mask + (1 << i)][i] = Math.min(dp[mask + (1 << i)][i], dp[mask][j] + distances[i][j]);
						}
					}
				}
			}
			
			if (ans == -1) string.append("-1\n");
			else {
			  // for every completed bitmask, see which position is best to end on
				for (int k = 0; k < n; k++) {
					ans = Math.min(ans, dp[(1 << n) - 1][k]);
				}
				string.append(ans + "\n");
			}
			
			col = in.nextInt();
			row = in.nextInt();
		}
		System.out.print(string);
	}
	
	public static int distance(Point p1, Point p2) {
		int row = grid.length;
		int col = grid[0].length;
		boolean[][] visited = new boolean[row][col];
		visited[p1.x][p1.y] = true;
		Queue<Point> queue = new LinkedList<Point>();
		p1.dist = 0;
		queue.add(p1);
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		Point curr = null;
		while (!queue.isEmpty()) {
			curr = queue.remove();
			if (curr.x == p2.x && curr.y == p2.y) break;
			for (int i = 0; i < 4; i++) {
				int x = curr.x + dx[i];
				int y = curr.y + dy[i];
				if (x >= 0 && x < row && y >= 0 && y < col && !visited[x][y] && grid[x][y] != 'x') {
					visited[x][y] = true;
					Point p = new Point(x, y);
					p.dist = curr.dist + 1;
					queue.add(p);
				}
			}
		}
		
		if (curr.x == p2.x && curr.y == p2.y) return curr.dist;
		return 200000;
	}
}

class Point {
	int x, y, dist;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
