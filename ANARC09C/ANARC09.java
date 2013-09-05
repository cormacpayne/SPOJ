import java.util.*;
class ANARC09C {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int A = Integer.parseInt(s.next());
		int B = Integer.parseInt(s.next());
		StringBuilder string = new StringBuilder();
		
		int testCases=1;
		boolean [] sieve ;
		int MAX = 1000000;
		sieve = new boolean [MAX];
		sieve [0] = sieve [1] = true ; 
		
		for (int i = 2; i < MAX ; ++i){
			for (int j = i * 2; j < MAX ; j += i){
				sieve [j] = true ; 
				}
		}
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 0; i < sieve.length; i++){
			if(sieve[i]==false){
				primes.add(i);
			}
		}
		while(A != 0 && B != 0){
			ArrayList<Integer> listA = new ArrayList<Integer>();
			ArrayList<Integer> listB = new ArrayList<Integer>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			int tempA = A;
			int tempB = B;
			int maxCountA=0;
			int maxCountB=0;
			for(int x = 0; x < primes.size(); x++){
				int pow = 0;
				if(tempA%primes.get(x) == 0){
					list.add(x);
					maxCountA=x;
				}
				while(tempA%primes.get(x) == 0){
					tempA /= primes.get(x);
					pow++;
				}
				listA.add(pow);
			}
			
			for(int x = 0; x < primes.size(); x++){
				int pow = 0;
				if(tempB%primes.get(x) == 0){
					list.add(x);
					maxCountB=x;
				}
				while(tempB%primes.get(x) == 0){
					tempB /= primes.get(x);
					pow++;
				}
				listB.add(pow);
			}
						
			HashSet<Integer> set = new HashSet<Integer>(list);
			int maxCount=0;
			
			if(maxCountA>maxCountB){
				maxCount=maxCountA;
			}else{
				maxCount=maxCountB;
			}
			
			int total = 0;
			for(int i=0; i<=maxCount; i++){
				total += Math.abs(listA.get(i) - listB.get(i));
			}
			string.append(testCases + ". " + set.size() + ":" + total + "\n");
			A= Integer.parseInt(s.next());
			B= Integer.parseInt(s.next());
			testCases++;	
		}
		System.out.println(string);
	}	
}
