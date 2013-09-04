import java.util.*;
import java.math.*;
public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		int num = Integer.parseInt(in.next());
		for(int x = 0; x < num; x++){
			String blank = in.next();
			String line = blank + in.nextLine();
			int e = line.indexOf('=');
			int p = line.indexOf('+');
			int m = line.indexOf('m');
	
			if(m > e){
				int a = Integer.parseInt(line.substring(0, p-1));
				int b = Integer.parseInt(line.substring(p+2, e-1));
				int c = a + b;
				string.append(a + " + " + b + " = " + c + "\n");
			}else if(m < p){
				int b = Integer.parseInt(line.substring(p+2, e-1));
				int c = Integer.parseInt(line.substring(e+2));
				int a = c - b;
				string.append(a + " + " + b + " = " + c + "\n");
			}else{
				int a = Integer.parseInt(line.substring(0, p-1));
				int c = Integer.parseInt(line.substring(e+2));
				int b = c - a;
				string.append(a + " + " + b + " = " + c + "\n");
			}
			
		}
		System.out.println(string);
	}
}
