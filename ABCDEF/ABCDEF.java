import java.util.*;
import java.io.*;

class ABCDEF {
   public static void main(String[] args) throws Exception {
      Parser in = new Parser(System.in);
      int n = in.nextInt();
      int[] num = new int[n];
      for (int i = 0; i < n; i++) num[i] = in.nextInt();
      int size = n*n*n;
      int[] first = new int[size];

      int idx = 0;
      for (int a = 0; a < n; a++){
         int aa = num[a];
         for (int b = 0; b < n; b++) {
            int bb = num[b];
            for (int c = 0; c < n; c++) {
               int cc = num[c];
               first[idx++] = aa * bb + cc;
            }
         }
      }

      Arrays.sort(first);

      int curr = first[0];
      int total = 1;

      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

      int[] counts = new int[size];

      for (int k = 1; k < size; k++) {
         if (first[k] == curr) total++;
         else {
            for (int j = k - 1; j >= k - total; j--) counts[j] = total;
            curr = first[k];
            total = 1;
         }
      }

      for (int l = size - 1; l >= size - total; l--) counts[l] = total;

      int res = 0;
      for (int d = 0; d < n; d++){
         int dd = num[d];
         if (dd == 0) continue;
         for (int e = 0; e < n; e++) {
            int ee = num[e];
            for (int f = 0; f < n; f++) {
               int ff = num[f];
               int ans = dd * (ee + ff);
               idx = Arrays.binarySearch(first, ans);
               if (idx >= 0) res += counts[idx];
            }
         }
      }

      System.out.println(res);
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
