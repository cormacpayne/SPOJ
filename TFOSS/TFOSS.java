import java.util.*;
import java.io.*;

class TFOSS {
	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();

		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			Point[] points = new Point[n];

			for (int j = 0; j < n; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				points[j] = new Point((long)x, (long)y);
			}

			if (n == 0 || n == 1) string.append("0\n");
			else if (n == 2) string.append(dist(points[0], points[1]) + "\n");
			else {
				Point[] hull = convexHull(points);
				string.append(farthestPoints(hull) + "\n");
			}
		}

		System.out.print(string);
	}

	public static double area(Point a, Point b, Point c) {
		return 0.5 * ((a.x*b.y - b.x*a.y) + (b.x*c.y - c.x*b.y) + (c.x*a.y - a.x*c.y));
	}

	public static long farthestPoints(Point[] hull) {
		long maxDist = -1;
		int i = 0;
		int k = (i + 2) % hull.length;

		for ( ; i < hull.length; i++) {
			int j = (i + 1) % hull.length;
			maxDist = Math.max(maxDist, Math.max(dist(hull[i], hull[k]), dist(hull[i], hull[j])));

			double curArea = area(hull[i], hull[j], hull[k]);

			while (area(hull[i], hull[j], hull[(k + 1) % hull.length]) >= curArea) {
				curArea = area(hull[i], hull[j], hull[(k + 1) % hull.length]);
				k = (k + 1) % hull.length;
				maxDist = Math.max(maxDist, dist(hull[i], hull[k]));
			}
		}
		return maxDist;
	}

	public static long dist(Point a, Point b) {
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return dx*dx + dy*dy;
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
	long x, y;

	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point p) {
		if ((int)this.x == (int)p.x) return (int)this.y - (int)p.y;
		return (int)this.x - (int)p.x;
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
