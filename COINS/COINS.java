import java.util.*;

class COINS {

	public static long[] memo;

	public static long solve(int n) {
		if (n < 0) return 0;
		if (n <= 1000000 && (memo[n] != 0 || n == 0)) return memo[n];
		if (n <= 1000000) {
			return memo[n] = (long)Math.max(n, solve(n / 2) + solve(n / 3) + solve(n / 4));
		} else {
			return (long)Math.max(n, solve(n / 2) + solve(n / 3) + solve(n / 4));
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();
		memo = new long[1000001];
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 3;
		while (in.hasNext()) {
			int n = in.nextInt();
			string.append(solve(n) + "\n");
		}
		System.out.print(string);
	}
}
