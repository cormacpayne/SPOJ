import java.util.*;
import java.io.*;

class MISERMAN {

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		int row = in.nextInt();
		int col = in.nextInt();

		int[][] grid = new int[row + 2][col + 2];

		Arrays.fill(grid[0], 999999);
		Arrays.fill(grid[row + 1], 999999);

		for (int r = 1; r <= row; r++) {
			grid[r][0] = 999999;
			for (int c = 1; c <= col; c++) {
				grid[r][c] = in.nextInt();
			}
			grid[r][col + 1] = 999999;
		}

		int[][] ans = new int[row + 2][col + 2];

		for (int i = 0; i <= col + 1; i++) ans[1][i] = grid[1][i];

		for (int m = 2; m <= row; m++) {
			ans[m][0] = 999999;
			ans[m][col + 1] = 999999;
		}

		for (int j = 2; j <= row; j++) {
			for (int k = 1; k <= col; k++) {
				ans[j][k] += grid[j][k] + Math.min(ans[j - 1][k - 1], Math.min(ans[j - 1][k], ans[j - 1][k + 1]));
			}
		}

		int result = 999999;

		for (int l = 1; l <= col; l++) {
			result = Math.min(result, ans[row][l]);
		}

		System.out.print(result);
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
