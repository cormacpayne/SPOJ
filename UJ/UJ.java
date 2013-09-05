import java.util.*;
import java.math.*;
import java.io.*;
class UJ {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int a = Integer.parseInt(in.next());
		int b = Integer.parseInt(in.next());
		
		while(a != 0){
			BigInteger one = BigInteger.valueOf(a);
			BigInteger total = one.pow(b);
			System.out.println(total);
			a = Integer.parseInt(in.next());
			b = Integer.parseInt(in.next());	
		}
	}	
}
