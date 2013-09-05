import java.io.*;
import java.util.*;
class MFLAR10 {

	public static void main(String[] args) throws Exception{
		//Parser in = new Parser(System.in);
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		String line = in.nextLine().toLowerCase();
		while(!line.equals("*")){
			String[] words = line.split(" ");
			boolean status = true;
			char l = words[0].charAt(0);
			if(words.length == 1){
				string.append("Y\n");
			}else{
				for(int x = 1; x < words.length; x++){
					if(words[x].charAt(0) != l){
						status = false;
					}
				}
				if(status){
					string.append("Y\n\n");
				}else{
					string.append("N\n\n");
				}
			}
			line = in.nextLine().toLowerCase();
		}
		
		System.out.println(string);
	}	
}	
