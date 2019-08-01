package dynamicProgramming;

public class MatrixChainMultiplication {
	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50, 60 };
		System.out.println(mcm(arr, 0, arr.length - 1, new int[arr.length][arr.length]));
		System.out.println(mcmT(arr));
	}

	public static int mcm(int[] arr, int si, int ei, int[][] qb) {
		if (ei - si == 1) {
			return 0;
		}
		if (qb[si][ei] != 0) {
			return qb[si][ei];
		}
		int min = Integer.MAX_VALUE;
		for (int cbp = si + 1; cbp <= ei - 1; cbp++) {// cbp-cut break point
			int part1 = mcm(arr, si, cbp, qb);
			int part2 = mcm(arr, cbp, ei, qb);
			int pc = arr[si] * arr[ei] * arr[cbp];// khudki cost

			min = Math.min(part1 + part2 + pc, min);
		}

		qb[si][ei] = min;
		return min;
	}

	public static int mcmT(int[] dims) {

		int[][] mpc = new int[dims.length][dims.length];

		for (int gap = 1; gap < dims.length; gap++) {
			int i = 0, j = i + gap;
			while (j < dims.length) {
				if (gap == 1) {
					mpc[i][j] = 0;
				} else {
					int minval = Integer.MAX_VALUE;

					for (int cp = i + 1; cp <= j - 1; cp++) {
						int fp = mpc[i][cp];
						int sp = mpc[cp][j];
						int total = dims[i] * dims[j] * dims[cp]+fp+sp;
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

		return mpc[0][dims.length - 1];

	}
}
