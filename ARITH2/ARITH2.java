import java.util.*;
import java.io.*;
class ARITH2{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StringBuilder string = new StringBuilder();
        int num = Integer.parseInt(in.nextLine());
        for(int x = 0; x < num; x++){
            String blank = in.nextLine();
            String line = in.nextLine();
            ArrayList<String> list = new ArrayList<String>();
            String newLine = line.replaceAll("\\s","");
            String number = "";
            for(int y = 0; y < newLine.length()-1; y++){
                String i = newLine.substring(y, y+1);
                if(i.equals("+")){
                    list.add(number);
                    number = "";
                    list.add(i);
                }else if(i.equals("-")){
                    list.add(number);
                    number = "";
                    list.add(i);
                }else if(i.equals("*")){
                    list.add(number);
                    number = "";
                    list.add(i);
                }else if(i.equals("/")){
                    list.add(number);
                    number = "";
                    list.add(i);
                }else{
                    number += i;
                }  
             }    
            list.add(number);            
            long result = Long.valueOf(list.get(0));
            for(int z = 1; z <= (list.size()-1)/2; z++){
                if(list.get(2 * z - 1).equals("+")){
                    result += Long.valueOf(list.get(2 * z));
                }else if(list.get(2 * z - 1).equals("-")){
                    result -= Long.valueOf(list.get(2 * z));
                }else if(list.get(2 * z - 1).equals("*")){
                    result *= Long.valueOf(list.get(2 * z));
                }else{
                    result /= Long.valueOf(list.get(2 * z));
                }
            }
            string.append(result + "\n");
        }
        System.out.print(string);
    }
}   
