import java.io.*;
import java.util.*;
class QUADRATE{
    public static void main(String[] args) throws Exception{
        Parser in = new Parser(System.in);
        StringBuilder string = new StringBuilder();
        int num = in.nextInt();
        for(int x = 0; x < num; x++){
            String[] line = in.next().split("\\*");
            double a, b, c;
            if(line.length == 4){
            
                a = Double.parseDouble(line[0]);
                
                if(line[2].charAt(1) == '+'){
                    b = Double.parseDouble(line[2].substring(2));
                }else{
                    b = -1 * Double.parseDouble(line[2].substring(2));
                }
                
                if(line[3].charAt(1) == '+'){
                    c = Double.parseDouble(line[3].substring(2, line[3].indexOf("=")));
                }else if(line[3].charAt(1) == '-'){
                    c = -1 * Double.parseDouble(line[3].substring(2, line[3].indexOf("=")));
                }else{
                    c = 0.0;
                }
                
            }else if(line.length == 3 && line[0].charAt(0) != 'x'){
                
                a = Double.parseDouble(line[0]);
                
               if(line[2].charAt(2) == 'x'){
                   if(line[2].charAt(1) == '+'){
                       b = 1.0;
                   }else{
                       b = -1.0;
                   }
                   if(line[2].charAt(3) == '+'){
                       c = Double.parseDouble(line[2].substring(4, line[2].indexOf("=")));
                   }else if(line[2].charAt(3) == '-'){
                       c = -1 * Double.parseDouble(line[2].substring(4, line[2].indexOf("=")));
                   }else{
                       c = 0.0;
                   }
                   
               }else{
               
                   b = 0.0;               
                   if(line[2].charAt(1) == '+'){
                       c = Double.parseDouble(line[2].substring(2, line[2].indexOf("=")));
                   }else if(line[2].charAt(1) == '-'){
                       c = -1 * Double.parseDouble(line[2].substring(2, line[2].indexOf("=")));
                   }else{
                       c = 0.0;
                   }                        
               } 
                
            }else if(line.length == 3 && line[0].charAt(0) == 'x'){
                
                a = 1.0;
                
                if(line[1].charAt(1) == '+'){
                    b = Double.parseDouble(line[1].substring(2));
                }else{
                    b = -1 * Double.parseDouble(line[1].substring(2));
                }
                
                if(line[2].charAt(1) == '+'){
                    c = Double.parseDouble(line[2].substring(2, line[2].indexOf("=")));
                }else if(line[2].charAt(1) == '-'){
                    c = -1 * Double.parseDouble(line[2].substring(2, line[2].indexOf("=")));
                }else{
                    c = 0.0;
                }
                
            }else{
               
               a = 1.0;
               
               if(line[1].charAt(2) == 'x'){
                   if(line[1].charAt(1) == '+'){
                       b = 1.0;
                   }else{
                       b = -1.0;
                   }
                   if(line[1].charAt(3) == '+'){
                       c = Double.parseDouble(line[1].substring(4, line[1].indexOf("=")));
                   }else if(line[1].charAt(3) == '-'){
                       c = -1 * Double.parseDouble(line[1].substring(4, line[1].indexOf("=")));
                   }else{
                       c = 0.0;
                   }
                   
               }else{
               
                   b = 0.0;               
                   if(line[1].charAt(1) == '+'){
                       c = Double.parseDouble(line[1].substring(2, line[1].indexOf("=")));
                   }else if(line[1].charAt(1) == '-'){
                       c = -1 * Double.parseDouble(line[1].substring(2, line[1].indexOf("=")));
                   }else{
                       c = 0.0;
                   }                        
               }                                   
            }                  
            
            double d = (b * b) - (4 * a * c);
            if(d > 0){
                string.append("Distinct real roots.\n\n");
            }else if(d == 0){
                string.append("Equal roots.\n\n");
            }else{
                string.append("Imaginary roots.\n\n");
            }   
        }
        System.out.print(string);
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
