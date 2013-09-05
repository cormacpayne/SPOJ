import java.util.*;
class CPRMT {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		while(in.hasNext()){
			String one = in.next();
			String two = in.next();
			int[] aone = new int[26];
			int[] atwo = new int[26];
			for(int x = 0; x < one.length(); x++){
				aone[(int)one.charAt(x)-97]++;
			}
			for(int y = 0; y < two.length(); y++){
				atwo[(int)two.charAt(y)-97]++;
			}
			for(int z = 0; z < 26; z++){
				if(aone[z] > 0 && atwo[z] > 0){
					if(aone[z] > atwo[z]){
						for(int w = 0; w < atwo[z]; w++){
							string.append((char)(z+97));
						}
					}else{
						for(int w = 0; w < aone[z]; w++){
							string.append((char)(z+97));
						}
					}
				}
			}
			string.append("\n");
		}
		System.out.print(string);
	}
}
