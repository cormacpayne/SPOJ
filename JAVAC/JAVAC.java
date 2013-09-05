import java.util.*;
class JAVAC{
	
	public static void main(String[] args){
	
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		try{
			while(in.hasNext()){			
				String line = in.next();
				if(line.indexOf("_") == -1){
					String result = "";
					if(line.substring(0, 1).equals(line.toUpperCase().substring(0,1))){
						result = "Error!";
					}else{
						for(int x = 0; x < line.length(); x++){
							if(line.substring(x, x+1).equals(line.toLowerCase().substring(x, x+1))){
								result += line.substring(x, x+1);
							}else{
								result += "_" + line.toLowerCase().substring(x, x+1);
							}
						}
					}				
					//string.append(result + "\n");
					System.out.println(result);
				}else{
					String result = "";
					if(line.substring(0, 1).equals(line.toUpperCase().substring(0,1)) || line.substring(line.length()-1).equals("_")){
						result = "Error!";
					}else{
						for(int x = 0; x < line.length(); x++){
							if(line.substring(x, x+1).equals("_")){
								if(line.substring(x+1, x+2).equals(line.toUpperCase().substring(x+1, x+2))){
									result = "Error!";
									break;
								}else{
									result += line.toUpperCase().substring(x+1, x+2);
									x++;
								}
							}else{
								if(line.substring(x, x+1).equals(line.toUpperCase().substring(x, x+1))){
									result = "Error!";
									break;
								}else{
									result += line.substring(x, x+1);
								}
							}
						}
					}				
					//string.append(result + "\n");
					System.out.println(result);
				}
			}
		}catch(Exception e){}
		
		//System.out.println(string);
	}
}
