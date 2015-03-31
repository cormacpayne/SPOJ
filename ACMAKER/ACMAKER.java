import java.util.*;
import java.io.*;

class ACMAKER {

	public static int wlen, alen;
	public static String ab;
	public static String[] st;
	public static Integer[][][][] memo;

	public static int solve(int widx, int cidx, int aidx, boolean taken) {
		if (widx == wlen && aidx == alen) return 1;
		if (widx == wlen || aidx == alen) return 0;
		if (cidx == st[widx].length()) return 0;
		if (memo[widx][cidx][aidx][(taken ? 1 : 0)] != null) return memo[widx][cidx][aidx][(taken ? 1 : 0)];

		int sum = 0;
		if (st[widx].charAt(cidx) == ab.charAt(aidx)) sum += solve(widx, cidx+1, aidx+1, true) + solve(widx+1,0,aidx+1,false);
		sum += solve(widx, cidx+1,aidx, taken);

		return memo[widx][cidx][aidx][(taken ? 1 : 0)] = sum;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		int n = Integer.parseInt(in.readLine());

		while (n != 0) {
			String[] insig = new String[n];
			for (int i = 0; i < n; i++) insig[i] = in.readLine().toUpperCase();
			String s = in.readLine().toUpperCase();

			while (!s.equals("LAST CASE")) {
				ab = s.substring(0, s.indexOf(" "));
				s = s.substring(s.indexOf(" ") + 1);

				String[] temp = s.split(" ");
				for (int j = 0; j < temp.length; j++) {
					String w = temp[j];
					for (String ss : insig) {
						if (ss.equals(w)) temp[j] = "";
					}
				}

				s = "";

				int maxLen = -1;

				for (int j = 0; j < temp.length; j++) {
					if (!temp[j].equals("")) {
						s += temp[j] + " ";
						maxLen = Math.max(maxLen, temp[j].length());
					}
				}

				s = s.substring(0, s.length() - 1);
				st = s.split(" ");
				wlen = st.length;
				alen = ab.length();

				memo = new Integer[wlen + 1][maxLen + 1][alen + 1][2];

				int res = solve(0, 0, 0, false);

				if (res == 0) string.append(ab + " is not a valid abbreviation\n");
				else string.append(ab + " can be formed in " + res + " ways\n");

				s = in.readLine().toUpperCase();
			}


			n = Integer.parseInt(in.readLine());
		}
		System.out.print(string);
	}
}

