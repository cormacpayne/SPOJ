import java.io.*;
import java.util.*;
class BITMAP{
   public static void main(String[] args) throws Exception{
      Parser in = new Parser(System.in);
      StringBuilder string = new StringBuilder();
      int num = in.nextInt();      
      for(int z = 0; z < num; z++){
         int rows = in.nextInt();
         int cols = in.nextInt();
         char[][] grid = new char[rows+2][cols+2];
         int[][] result = new int[rows+2][cols+2];
         int[] xValues = {1, -1, 0, 0};
         int[] yValues = {0, 0, 1, -1};

         for(int a = 0; a < cols+2; a++){
            grid[0][a] = '#';
            grid[rows+1][a] = '#';
         }

         Queue<Bit> queue = new LinkedList<Bit>();
         Bit current = null;
         boolean[][]visited = new boolean[rows+2][cols+2];

         for(int j = 1; j < rows+1; j++){
            String line = in.next();
            grid[j][0] = '#';
            for(int k = 1; k < cols+1; k++){
               grid[j][k] = line.charAt(k-1);
               result[j][k] = 500;
               if(grid[j][k] == '1'){
                  queue.add(new Bit(j,k,0));
                  visited[j][k] = true;
               }
            }
            grid[j][cols+1] = '#';
         }              
         
         while(!queue.isEmpty()){
            current = queue.remove();
            result[current.x][current.y] = Math.min(current.distance, result[current.x][current.y]);
            for(int m = 0; m < 4; m++){
               int x = current.x+xValues[m];
               int y = current.y+yValues[m];
               if(!visited[x][y] && grid[x][y] != '#'){
                  queue.add(new Bit(x,y,current.distance+1));
                  visited[x][y] = true;
               }
            }
         }

         for(int rowNum = 1; rowNum < rows+1; rowNum++){
            for(int colNum = 1; colNum < cols+1; colNum++){
               if(colNum == cols){
                  string.append(result[rowNum][colNum] + "\n");
               }else{
                  string.append(result[rowNum][colNum] + " ");
               }
            }
         }
         string.append("\n");
      }
      System.out.print(string);
   }
   
   static class Bit{
      public int x;
      public int y;
      public int distance;
      
      public Bit(int x, int y, int distance){
         this.x = x;
         this.y = y;
         this.distance = distance;
      }
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
