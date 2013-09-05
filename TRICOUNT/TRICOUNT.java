import java.util.*;
import java.io.*;
class TRICOUNT{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StringBuilder string = new StringBuilder();
        int num = in.nextInt();
        for(int x = 0; x < num; x++){
            long n = in.nextLong();
            string.append(((n*(n+2)*(2*n+1))/8) + "\n");
        }
        System.out.print(string);
    }
}
