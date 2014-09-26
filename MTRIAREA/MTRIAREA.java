import java.util.*;
import java.io.*;

class MTRIAREA {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n != -1) {
			Point[] points = new Point[n];
			for (int i = 0; i < n; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				points[i] = new Point(x, y);
			}

			Point[] hull = convexHull(points);

			int nn = hull.length;

			int a = 0;
			int b = 1;
			int c = 2;

			int aa = a;
			int bb = b;
			int cc = c;

			while (true) {

				while (true) {
					while (area(hull[a], hull[b], hull[c]) <= area(hull[a], hull[b], hull[(c + 1) % nn])) {
						c = (c + 1) % nn;
					}

					if (area(hull[a], hull[b], hull[c]) <= area(hull[a], hull[(b + 1) % nn], hull[c])) {
						b = (b + 1) % nn;
						continue;
					} else {
						break;
					}
				}

				if (area(hull[a], hull[b], hull[c]) > area(hull[aa], hull[bb], hull[cc])) {
					aa = a;
					bb = b;
					cc = c;
				}

				a = (a + 1) % nn;
				if (a == b) b = (b + 1) % nn;
				if (b == c) c = (c + 1) % nn;
				if (a == 0) break;
			}

			System.out.printf("%.2f\n", area(hull[aa], hull[bb], hull[cc]));

			n = in.nextInt();
		}
	}

	public static double area(Point a, Point b, Point c) {
		double aa = dist(a, b);
		double bb = dist(a, c);
		double cc = dist(b, c);

		double s = (aa + bb + cc) / 2;

		return Math.sqrt(s * (s - aa) * (s - bb) * (s - cc));
	}

	public static double dist(Point a, Point b) {
		int dx = a.x - b.x;
		int dy = a.y - b.y;
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

class Point implements Comparable<Point> {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point p) {
		if (this.x - p.x == 0) {
			return this.y - p.y;
		}
		return this.x - p.x;
	}
}
