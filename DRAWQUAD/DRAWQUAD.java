import java.util.*;
import java.io.*;

class Test {
	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();		
		int n = in.nextInt();
		while (n != -1) {
			Point[] points = new Point[n];
			for (int i = 0; i < n; i++) {
				long x = (long)in.nextInt();
				long y = (long)in.nextInt();
				points[i] = new Point(x, y, i);
			}
			
			Point[] hull = convexHull(points);
			int nn = hull.length;
			
			if (nn == 3) {
				double max = Double.MIN_VALUE;
				for (int i = 0; i < hull.length; i++) {
					for (int j = 0; j < hull.length; j++) {
						for (int k = 0; k < hull.length; k++) {
							for (int l = 0; l < points.length; l++) {
								if (i != j && j != k && i != k) {
									Point ii = hull[i];
									Point jj = hull[j];
									Point kk = hull[k];
									Point ll = points[l];
									if (ll.id == ii.id || ll.id == jj.id || ll.id == kk.id) continue;
									
									max = Math.max(max, area(ii, jj, kk, ll));
									max = Math.max(max, area(ii, jj, ll, kk));
									max = Math.max(max, area(ii, ll, jj, kk));
									max = Math.max(max, area(ll, ii, jj, kk));
								}
							}
						}
					}
				}
				System.out.printf("%.1f\n", max);
			} else {
				int a = 0;
				int b = 1;
				int c = 2;
				int d = 3;
				
				int aa = a;
				int bb = b;
				int cc = c;
				int dd = d;
				
				while (true) {
					while (true) {
						while (true) {
							while (area(hull[a], hull[b], hull[c], hull[d]) <= area(hull[a], hull[b], hull[c], hull[(d+1) % nn])) {
								d = (d + 1) % nn;
							}
							
							if (area(hull[a], hull[b], hull[c], hull[d]) <= area(hull[a], hull[b], hull[(c+1) % nn], hull[d])) {
								c = (c + 1) % nn;
								continue;
							} else {
								break;
							}
						}
						
						if (area(hull[a], hull[b], hull[c], hull[d]) <= area(hull[a], hull[(b+1) % nn], hull[c], hull[d])) {
							b = (b + 1) % nn;
							continue;
						} else {
							break;
						}					
					}
					if (area(hull[a], hull[b], hull[c], hull[d]) > area(hull[aa], hull[bb], hull[cc], hull[dd])) {
						aa = a;
						bb = b;
						cc = c;
						dd = d;
					}
					
					a = (a+1) % nn;
					if (a == b) b = (b+1) % nn;
					if (b == c) c = (c+1) % nn;
					if (c == d) d = (d+1) % nn;
					if (a == 0) break;
				}
				
				System.out.printf("%.1f\n", area(hull[aa], hull[bb], hull[cc], hull[dd]));
			}			
			n = in.nextInt();
		}
	}
	
	public static double area(Point p0, Point p1, Point p2, Point p3) {
		Point[] p = {p0, p1, p2, p3};
		double ans = 0;
		for (int i = 0; i < 4; i++) {
			ans += p[i].x * p[(i+1) % 4].y;
			ans -= p[(i+1) % 4].x * p[i].y;
		}
		return Math.abs(ans / 2.0);
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
    	return ccw(a, b, c) >= 0;
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
