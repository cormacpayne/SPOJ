import java.util.*;
import java.io.*;

class LCA {

	public static ArrayList<ArrayList<Integer>> graph;
	public static int[] a, e, first;
	public static int idx;

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		
		for (int z = 1; z <= t; z++) {
			int n = in.nextInt();
			graph = new ArrayList<ArrayList<Integer>>();
			a = new int[2 * n + 1];
			e = new int[2 * n + 1];
			first = new int[n + 1];
			Arrays.fill(first, -1);
			idx = 0;

			for (int i = 0; i <= n; i++) graph.add(new ArrayList<Integer>());
			for (int j = 1; j <= n; j++) {
				int b = in.nextInt();
				for (int k = 0; k < b; k++) {
					int c = in.nextInt();
					graph.get(j).add(c);
				}
			}

			dfs(1, 0);

			SegmentTree st = new SegmentTree(idx, a);

			int q = in.nextInt();

			string.append("Case " + z + ":\n");

			for (int y = 0; y < q; y++) {
				int r = in.nextInt();
				int s = in.nextInt();

				int rIdx = Math.min(first[r], first[s]);
				int sIdx = Math.max(first[r], first[s]);

				idx = st.rmq(rIdx, sIdx);

				string.append(e[idx] + "\n");
			}
		}

		System.out.print(string);
	}

	public static void dfs(int x, int level) {
		if (first[x] == -1) first[x] = idx;
		a[idx] = level;
		e[idx++] = x;
		for (Integer i : graph.get(x)) {
			dfs(i, level + 1);
			a[idx] = level;
			e[idx++] = x;
		}
	}
}

// the segment tree is stored like a heap array
class SegmentTree {
	int n;
	int[] s, a;

	public SegmentTree(int n, int[] temp) {
		this.n = n;
		this.a = temp;
		this.s = new int[4*n];
		build(1, 0, n - 1);
	}

	// same as binary heap operations
	public int left(int p) { return p << 1; }
	public int right(int p) { return (p << 1) + 1; }

	// O(n)
	public void build(int p, int l, int r) {
		if (l == r) s[p] = l; // store the index
		else { // recursively compute the values
			build(left(p), l, (l + r) / 2);
			build(right(p), ((l + r) / 2) + 1, r);
			int p1 = s[left(p)];
			int p2 = s[right(p)];
			s[p] = a[p1] <= a[p2] ? p1 : p2;
		}
	}

	// O(logn)
	public int rmq(int p, int l, int r, int i, int j) {
		if (i > r || j < l) return -1; // segment outside query range
		if (l >= i && r <= j) return s[p];      // inside query range

		// compute the min position in the left and right
		// part of the interval
		int p1 = rmq(left(p), l, (l + r) / 2, i, j);
		int p2 = rmq(right(p), ((l + r) / 2) + 1, r, i, j);

		if (p1 == -1) return p2; // if we try to access segement outside query
		if (p2 == -1) return p1; // same as above

		return a[p1] <= a[p2] ? p1 : p2; // as in build routine
	}

	public int rmq(int i, int j) { return rmq(1, 0, n - 1, i, j); }
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
