import java.util.*;
import java.io.*;
class LCPC12E{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		int num = Integer.parseInt(in.next());
		for(int x = 1; x <= num; x++){
			double aX = in.nextDouble();
			double aY = in.nextDouble();
			double bX = in.nextDouble();
			double bY = in.nextDouble();
			double aR = in.nextDouble();
			double bL = in.nextDouble();
			double bR = 0.5*Math.sqrt((bL*bL) + (bL*bL));
			double d = Math.sqrt(Math.pow((aX-bX), 2.0) + Math.pow((aY-bY), 2.0));
			double s = 0.5*(aR + bR + d);
			double area = Math.sqrt(s * (s-aR) * (s-bR) * (s - d));
			if(area == 0 || area > 0 || area < 0){
				double w = 4 * area/d;		
				string.append(String.format("%d. %.3f\n", x, w));				
			}else{
				string.append(x + ". No problem\n");
			}
		}
		System.out.print(string);
	}
}
