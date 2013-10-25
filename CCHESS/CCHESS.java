import java.io.*;
import java.util.*;
class CCHESS {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		while(in.hasNext()){
			int startX = in.nextInt();
			int startY = in.nextInt();
			int endX = in.nextInt();
			int endY = in.nextInt();
			
			boolean[][] visited = new boolean[8][8];
			int[] xValues = {2, 2, -2, -2, 1, 1, -1, -1};
			int[] yValues = {1, -1, 1, -1, 2, -2, 2, -2};
			
			visited[startX][startY] = true;
			PriorityQueue<Point> pq = new PriorityQueue<Point>();
			Point current = null;
			pq.add(new Point(startX, startY, 0));
			
			while(!pq.isEmpty()){
				current = pq.remove();
				visited[current.x][current.y] = true;
				//System.out.printf("current.x = %d, current.y = %d, current.dist = %d\n", current.x, current.y, current.dist);
				if(current.x == endX && current.y == endY){
					break;
				}
				
				for(int i = 0; i < 8; i++){
					int x = current.x + xValues[i];
					int y = current.y + yValues[i];
					
					if(x >= 0 && x < 8 && y >= 0 && y < 8 && !visited[x][y]){
						//System.out.printf("\tx = %d, y = %d, dist = %d\n", x, y, current.dist + (current.x * x) + (current.y * y));
						pq.add(new Point(x, y, current.dist + (current.x * x) + (current.y * y)));
					}
				}
			}
			
			if(current.x != endX && current.y != endY){
				string.append("-1\n");
				//System.out.println("-1");
			}else{
				string.append(current.dist + "\n");
				//System.out.println(current.dist);
			}
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
