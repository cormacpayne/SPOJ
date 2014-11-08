import java.util.*;
import java.io.*;

class BSHEEP {
	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		while (t-->0) {
			int n = in.nextInt();
			TreeSet<Point> treeset = new TreeSet<Point>();
			for (int i = 0; i < n; i++) {
				long x = (long)in.nextInt();
				long y = (long)in.nextInt();
				treeset.add(new Point(x, y, i+1));
			}
			
			Point[] points = new Point[treeset.size()];
			int idx = 0;
			while (!treeset.isEmpty()) points[idx++] = treeset.pollFirst();
			
			Point[] hull = convexHull(points);
			double length = 0;
			for (int j = 0; j < hull.length; j++) {
				length += distance(hull[j], hull[(j+1) % hull.length]);
			}
			string.append(String.format("%.2f\n", length));
			string.append(hull[0].id);
			for (int k = 1; k < hull.length; k++) {
				string.append(" " + hull[k].id);
			}
			string.append("\n\n");
		}
		System.out.print(string);
	}
	
	public static double distance(Point a, Point b) {
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public static Point[] convexHull(Point[] points) {
    	if (points.length == 1) return points;
    	Arrays.sort(points);//sort by least x, then by least y for ties.

    	Point[] upper = new Point[points.length];
    	int upperIdx = 0;//Current position in upper.
    	for (int i = 0; i < points.length; i++) {
    		while (upperIdx >= 2 
    			&& !leftOrStraight(upper[upperIdx-2], upper[upperIdx-1], points[i])) 
    			upperIdx--;
    		upper[upperIdx++] = points[i];
    	}

    	Point[] lower = new Point[points.length];
    	int lowerIdx = 0;
    	for (int i = points.length - 1; i >= 0; i--) {
    		while (lowerIdx >= 2
    			&& !leftOrStraight(lower[lowerIdx-2], lower[lowerIdx-1], points[i]))
    			lowerIdx--;
    		lower[lowerIdx++] = points[i];
    	}

    	Point[] hull = new Point[upperIdx+lowerIdx-2];
    	for (int i = 0; i < upperIdx; i++) hull[i] = upper[i];
    	int next = upperIdx;
    	for (int i = 1; i < lowerIdx-1; i++) hull[next++] = lower[i];
    	/*System.out.println("UPPER: " + Arrays.toString(upper));
    	System.out.println("LOWER: " + Arrays.toString(lower));
    	System.out.println("HULL: " + Arrays.toString(hull));*/
    	return hull;
    }

    public static boolean leftOrStraight(Point a, Point b, Point c) {
    	return ccw(a, b, c) > 0;
    }

    public static long ccw(Point a, Point b, Point c) {//=0 is collinear, > 0 is left, <0 is right.
    	return (b.x-a.x)*(c.y-b.y)-(c.x-b.x)*(b.y-a.y);
    }
}

class Point implements Comparable<Point>{
	long x,y,id;
	
	public Point(long x, long y, long id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public int compareTo(Point p) {
		if (this.y == p.y) return (int)(this.x - p.x);
		return (int)(this.y - p.y);
	}
}

