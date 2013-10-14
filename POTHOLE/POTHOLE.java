import java.io.*;
import java.util.*;
class POTHOLE {
	public static void main(String[] args)throws Exception{
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();
		int t = in.nextInt();
		for(int i = 0; i < t; i++){
			int n = in.nextInt();
			int[][] cap = new int[n][n];
			for(int j = 0; j < n-1; j++){
				int e = in.nextInt();
				for(int k = 0; k < e; k++){
					int v = in.nextInt()-1;
					if(j == 0 || v == n-1){
						cap[j][v] = 1;
					}else{
						cap[j][v] = 40000;
					}
				}
			}
			string.append(maxFlow(cap, 0, n-1) + "\n");
		}
		System.out.print(string);
	}
	
	public static int maxFlow(int[][] cap, int s, int t){
		for(int flow = 0;;){
			int df = findPath(cap, new boolean[cap.length], s, t, Integer.MAX_VALUE);
			if(df == 0){
				return flow;
			}
			flow += df;
		}
	}
	
	public static int findPath(int[][] cap, boolean[] vis, int u, int t, int f){
		if(u == t){
			return f;
		}
		vis[u] = true;
		for(int v = 0; v < vis.length; v++){
			if(!vis[v] && cap[u][v] > 0){
				int df = findPath(cap, vis, v, t, Math.min(f, cap[u][v]));
				if(df > 0){
					cap[u][v] -= df;
					cap[v][u] += df;
					return df;
				}
			}
		}
		return 0;
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
