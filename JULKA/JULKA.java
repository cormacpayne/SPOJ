import java.util.*;
import java.io.*;
import java.math.*;
class JULKA {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		for(int x = 0; x < 10; x++){
			BigInteger total = in.nextBigInteger();
			BigInteger diff = in.nextBigInteger();
			BigInteger halfTotal = total.divide(BigInteger.valueOf(2));
			BigInteger halfDiff = diff.divide(BigInteger.valueOf(2));
			
			BigInteger a = halfTotal.add(halfDiff);
			BigInteger b = halfTotal.subtract(halfDiff);
			
			if(total.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ONE) == 0){
				a = a.add(BigInteger.ONE);
			System.out.println(a);
			}else{
				System.out.println(a);
			}
			System.out.println(b);
		}
	}
}
