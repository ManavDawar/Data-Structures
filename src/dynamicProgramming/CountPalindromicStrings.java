package dynamicProgramming;

public class CountPalindromicStrings {
	public static void main(String[] args) {
		System.out.println(countpaliStr("aaa"));
	}

	public static int countpaliStr(String str) {
		boolean[][] strg = new boolean[str.length()][str.length()];
		int counter = 0;
		for (int diag = 0; diag < strg.length; diag++) {
			int si = 0;
			int ei = diag;

			while (ei < str.length()) {
				if (diag == 0) {
					strg[si][ei] = true;
				} else if (diag == 1) {
					if (str.charAt(si) == str.charAt(ei)) {
						strg[si][ei] = true;
					}
				} else {
					if (str.charAt(si) == str.charAt(ei) && strg[si + 1][ei - 1] == true) {
						strg[si][ei] = true;
					}
				}
				if (strg[si][ei] == true) {
					counter++;
				}

				ei++;
				si++;
			}
		}
		
		return counter;

	}

}
