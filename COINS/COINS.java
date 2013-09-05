import java.util.*;
class COINS{

	public static long[] a;

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		a = new long[1000001];
		while(in.hasNextLong()){
			string.append(magic(in.nextLong()) + "\n");
		}
		System.out.print(string);
	}

	public static long magic(long n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		if(n == 2) return 2;
		if(n < 1000001 && a[(int)n] != 0) return a[(int)n];
		else{
			long s = magic(n/2) + magic(n/3) + magic(n/4);
			if(s > n){
				if(n < 1000001) a[(int)n] = s;
				return s;
			}else{
				if(n < 1000001) a[(int)n] = n;
				return n;
			}
		}
	}
}
