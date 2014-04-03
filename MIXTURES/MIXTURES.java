import java.util.*;
import java.io.*;

class MIXTURES {

	static long[][] dp;
	static int[] values;
	static int n;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();

		while (in.hasNext()) {
			n = in.nextInt();
			values = new int[n+1];
			dp = new long[n+1][n+1];

			for (long[] row : dp) {
				Arrays.fill(row, Long.MAX_VALUE);
			}

			for (int i = 1; i <= n; i++) {
				values[i] = in.nextInt();
			}

			string.append(solve(1, n) + "\n");
		}

		System.out.print(string);
	}

	public static long solve(int start, int end) {
		if (dp[start][end] != Long.MAX_VALUE) {
			return dp[start][end];
		}

		if (start == end) {
			return 0;
		}

		long val = dp[start][end];

		for (int k = start; k < end; k++) {
			val = Math.min(val, solve(start, k) + solve(k+1, end) + count(start, k) * count(k+1, end));
		}

		dp[start][end] = val;

		return val;
	}

	public static long count(int start, int end) {
		long sum = 0;
		
		for (int i = start; i <= end; i++) {
			sum += values[i];
			sum %= 100;
		}
		return sum;
	}
}
