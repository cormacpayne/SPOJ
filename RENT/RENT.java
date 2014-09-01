import java.util.*;
import java.io.*;

class RENT {

	public static int n, maxStart;
	public static Time[] times;
	public static int[] memo;

	public static int solve(int index) {
      if (index >= n) return 0;
      if (memo[index] != -1) return memo[index];
      if (times[index].end > maxStart) {
         memo[index] = Math.max(times[index].cost, solve(index + 1));
      } else {
         int next = binarySearch(index + 1, n - 1, times[index].end);
         memo[index] = Math.max(times[index].cost + solve(next), solve(index + 1));
      }
      return memo[index];
   }

   public static int binarySearch(int left, int right, int value) {
      while (true) {
         if (right - left < 2) {
            return times[left].start >= value ? left : right;
         }
         int mid = (left + right) / 2;
         if (times[mid].start > value) right = mid;
         else if (times[mid].start < value) left = mid + 1;
         else right = mid;
      }
   }

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();

		for (int i = 0; i < t; i++) {
			n = in.nextInt();
			times = new Time[n];
			memo = new int[n];

         maxStart = -1;

			Arrays.fill(memo, -1);

			for (int j = 0; j < n; j++) {
				int start = in.nextInt();
				int duration = in.nextInt();
				int cost = in.nextInt();
				times[j] = new Time(start, start + duration, cost);
            if (start > maxStart) maxStart = start;
			}

         Arrays.sort(times);

			string.append(solve(0) + "\n");
		}
		System.out.print(string);
	}
}

class Time implements Comparable<Time> {
	int start, end, cost;

	public Time(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

   public int compareTo(Time t) {
      return this.start - t.start;
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
