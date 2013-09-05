import java.io.*;
import java.util.*;
class HEADSHOT {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		String line = in.next();
		int num0, num1, after0, after1;
		num0 = num1 = after0 = after1 = 0;
		for(int i = 0; i < line.length()-1; i++){
			char c = line.charAt(i);
			if(c == '0'){
				num0++;
				if(line.charAt(i+1) == '0'){
					after0++;
				}else{
					after1++;
				}
			}else{
				num1++;
			}
		}
		if(line.charAt(line.length()-1) == '0'){
			num0++;
			if(line.charAt(0) == '0'){
				after0++;
			}else{
				after1++;
			}
		}else{
			num1++;
		}
		
		double num = (double)num0/(num0+num1);
		double after = (double)after0/(after0+after1);
		if(num == after){
			System.out.print("EQUAL");
		}else if(after > num){
			System.out.print("SHOOT");
		}else{
			System.out.print("ROTATE");
		}
	}
}
