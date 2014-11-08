import java.util.*;

class PONY2 {
	
	static long[] memo;
	
	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		int n = in.nextInt();
		while (n-->0) {
			String s = in.next();
			long k = in.nextInt();
			int length = s.length();
			memo = new long[length + 1];
			Arrays.fill(memo, 0);
			memo[0] = 1;
			if (length > 1) {
				int j = Integer.parseInt(s.substring(length-2));
				if (j == 10 || j == 20) memo[1] = 0;
				else memo[1] = 1;
			}
 		
			if (length == 1) {
				char c = (char)(Integer.parseInt(s) + 96);
				string.append(c);
			} else {
				long total = find(s);
				
				int remaining = length - 1;
				while (remaining >= 0) {
					if (k > memo[remaining]) {
					 	k -= memo[remaining];
					 	char c = (char)(Integer.parseInt(s.substring(0, 2)) + 96);
					 	string.append(c);
					 	remaining -= 2;
					 	s = s.substring(2);
					} else {
						char c = (char)(Integer.parseInt(s.substring(0, 1)) + 96);
						string.append(c);
						remaining--;
						s = s.substring(1);
					}
				}
			}
			
			string.append("\n");
		}
		System.out.print(string);
	}
	
	public static long find(String s) {		
		if (memo[s.length()] != 0) return memo[s.length()];
		int n = Integer.parseInt(s.substring(0, 2));
		if (n >= 10 && n <= 26) {
			if (n == 10 || n == 20) return memo[s.length()] = find(s.substring(2));
			else {
				if (s.length() > 2 && s.charAt(2) == '0') memo[s.length()] = find(s.substring(1));
				else memo[s.length()] = find(s.substring(1)) + find(s.substring(2));
				return memo[s.length()];
			}
		} else return memo[s.length()] = find(s.substring(1));
	}
}

