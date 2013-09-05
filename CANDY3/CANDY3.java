import java.util.*;
import java.math.*;
import java.io.*;
class CANDY3 {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		int num = Integer.parseInt(in.next());
		for(int x= 0; x < num; x++){
			int kids = Integer.parseInt(in.next());
			BigInteger total = BigInteger.ZERO;
			BigInteger[] list = new BigInteger[kids];
			for(int y = 0; y < kids; y++){
				BigInteger kid = in.nextBigInteger();
				total = total.add(kid);
				list[y] = kid;
			}
			BigInteger mod = total.mod(BigInteger.valueOf(kids));
			int m = mod.intValue();
			if(m != 0){
				string.append("NO\n");
			}else{
				string.append("YES\n");
			}
		}		
		System.out.println(string);
	}
}
