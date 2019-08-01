package dynamicProgramming;

public class MaxSquare1s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] arr = { { 1, 0, 1, 0, 1, 1, 1 }, { 1, 0, 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0, 1, 0 },
				{ 0, 1, 1, 1, 1, 0, 0 }, { 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0 }, { 1, 0, 0, 1, 1, 0, 0 },
				{ 1, 0, 1, 1, 1, 0, 1 } };
		largestonesquarematrix1T(arr);
		System.out.println(matrixM(arr, 0, 0, arr.length - 1, arr[0].length - 1));
		System.out.println(max);
	}

	static int max = 0;

	public static int matrixM(int[][] arr, int sr, int sc, int dr, int dc) {

		if (sr == dr || sc == dc) {
			return arr[sr][sc];
		}
		if (arr[sr][sc] == 0) {
			return 0;
		} else {
			int h = matrixM(arr, sr, sc + 1, dr, dc);
			int v = matrixM(arr, sr + 1, sc, dr, dc);
			int d = matrixM(arr, sr + 1, sc + 1, dr, dc);
			int res = Math.min(v, Math.min(h, d)) + 1;
			if (res > max) {
				max = res;
			}
			return res;

		}

	}

	public static void largestonesquarematrix1T(int[][] arr) {

		int[][] strg = new int[arr.length][arr[0].length];

		int max = Integer.MIN_VALUE;

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = arr[0].length - 1; j >= 0; j--) {

				if (i == arr.length - 1 && j == arr[0].length - 1) {
					strg[i][j] = arr[i][j];
				} else if (i == arr.length - 1) {
					strg[i][j] = arr[i][j];
				} else if (j == arr[0].length - 1) {
					strg[i][j] = arr[i][j];
				} else {
					if (arr[i][j] == 1) {
						strg[i][j] = Math.min(strg[i][j + 1], Math.min(strg[i + 1][j + 1], strg[i + 1][j])) + 1;
					}

					max = Math.max(max, strg[i][j]);
				}
			}
		}

		System.out.println(max);

	}
}
