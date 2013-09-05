import java.io.*;
import java.util.*;
class GUESSTHE {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		String line = in.next();
		StringBuilder string = new StringBuilder();
		while(!line.equals("*")){
			char[] i = new char[line.length()];
			for(int x = 0; x < line.length(); x++){
				i[x] = line.charAt(x);
			}			
			long num = 1;
			if(i[0] == 'N'){
				num = -1;
			}else{
				for(int y = 1; y < i.length; y++){	
					if(i[y] == 'Y'){
						num = lcm(num, y+1);
					}
				}
			}
			for(int z = 0; z < i.length; z++){
				if(i[z] == 'N' && num%(z+1) == 0){
					num = -1;
					break;
				}
			}
			string.append(num + "\n");
			line = in.next();
		}
		System.out.println(string);
	}
	
	public static long gcd(long a, long b){
		while(b != 0){
			long temp = b;
			b = a%b;
			a = temp;
		}
		return a;
	}
	
	public static long lcm(long a, long b){
		long g = gcd(a, b);
		long m = a * b;
		return m/g;
	}
}
