import java.util.*;
class EDIT{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		while(in.hasNext()){
			String s = in.next();
			int a = 0;
			int b = 0;
			for(int i = 0; i < s.length(); i++){
				int c = i%2;
				if(s.charAt(i) >= 'a'){
					if(c == 0){
						a++;
					}else{
						b++;
					}
				}else{
					if(c == 0){
						b++;
					}else{
						a++;
					}
				}
			}
			if(a > b){
				string.append(b + "\n");
			}else{
				string.append(a + "\n");
			}
		}
		System.out.print(string);
	}
}
