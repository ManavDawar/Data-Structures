package dynamicProgramming;

public class MinPalindromicCuts {

	public static void main(String[] args) {
		String p = "abccbc";
		System.out.println(mpcM(p, 0, p.length() - 1, new int[p.length()][p.length()]));

		System.out.println(mpcT("abccbc"));
	}

	private static int mpcM(String p, int si, int ei, int[][] qb) {
		if (ispalindrome(p, si, ei)) {
			return 0;
		}
		if (qb[si][ei] != 0)
			return qb[si][ei];

		int mpc = Integer.MAX_VALUE;
		for (int cp = si; cp < ei; cp++) {
//			cp = cut point
			int mpc1 = mpcM(p, si, cp, qb);
			int mpc2 = mpcM(p, cp + 1, ei, qb);
			int total = mpc1 + mpc2 + 1;
			if (total < mpc) {
				mpc = total;
			}
		}

		qb[si][ei] = mpc;

		return mpc;
	}

	private static boolean ispalindrome(String p, int si, int ei) {
		int left = si;
		int right = ei;
		while (left < right) {
			if (p.charAt(left) != p.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}

	public static int mpcT(String s) {
		boolean[][] ispalin = new boolean[s.length()][s.length()];

		for (int gap = 0; gap < s.length(); gap++) {
			int i = 0, j = i + gap;
			while (j < s.length()) {
				if (gap == 0) {
					ispalin[i][j] = true;
				} else if (gap == 1) {
					ispalin[i][j] = s.charAt(i) == s.charAt(j);
				} else {
					ispalin[i][j] = s.charAt(i) == s.charAt(j) && ispalin[i + 1][j - 1];
				}

				i++;
				j++;
			}
		}

		int[][] mpc = new int[s.length()][s.length()];

		for (int gap = 0; gap < s.length(); gap++) {
			int i = 0, j = i + gap;
			while (j < s.length()) {
				if (gap == 0) {
					mpc[i][j] = 0;
				} else if (gap == 1) {
					mpc[i][j] = ispalin[i][j] == true ? 0 : 1;
				} else {
					if (ispalin[i][j]) {
						mpc[i][j] = 0;
						i++;
						j++;
						continue;
					}
					int minval = Integer.MAX_VALUE;

					for (int cp = i; cp < j; cp++) {
						int fp = mpc[i][cp];
						int sp = mpc[cp+1][j];
						int total = fp + sp + 1;

						if (total < minval) {
							minval = total;
						}
					}

					mpc[i][j] = minval;

				}

				i++;
				j++;
			}
		}

		return mpc[0][s.length() - 1];

	}

}
