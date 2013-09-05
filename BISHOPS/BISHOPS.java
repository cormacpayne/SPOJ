import java.util.*;
import java.io.*;
import java.math.*;
class BISHOPS {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		while(in.hasNext()){
			BigInteger num = in.nextBigInteger();
			if(num.intValue() == 1){
				string.append("1\n");
			}else{
				num = num.subtract(BigInteger.ONE);
				BigInteger result = num.multiply(BigInteger.valueOf(2));
				string.append(result + "\n");
			}
		}
		System.out.println(string);
	}
}
