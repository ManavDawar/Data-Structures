package dynamicProgramming;

public class MinCostPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = { { 2, 3, 6, 8, 1 }, { 7, 9, 4, 0, 4 }, { 4, 5, 9, 3, 7 }, { 5, 1, 0, 8, 4 }, { 1, 3, 8, 7, 6 },
				{ 8, 7, 0, 5, 1 }, { 0, 9, 6, 3, 2 }

		};

		System.out.println(mincostpath(0, 0, arr.length - 1, arr[0].length - 1, arr));
		System.out.println(
				mincostpathM(0, 0, arr.length - 1, arr[0].length - 1, arr, new int[arr.length + 1][arr[0].length + 1]));
		System.out.println(mincostpathT(arr));

		minpath(arr, 0, 0, "");
	}

	static int[][] strg;

	private static int mincostpath(int sr, int sc, int dr, int dc, int[][] arr) {
		// TODO Auto-generated method stub
		if (sr == dr && sc == dc) {
			return arr[sr][sc];
		}
		int cihtod = Integer.MAX_VALUE;
		int civtod = Integer.MAX_VALUE;

		int cstod = 0;
		if (sc < dc) {
			cihtod = mincostpath(sr, sc + 1, dr, dc, arr);
		}
		if (sr < dr) {
			civtod = mincostpath(sr + 1, sc, dr, dc, arr);
		}
		cstod = arr[sr][sc] + Math.min(cihtod, civtod);
		return cstod;
	}

	private static int mincostpathM(int sr, int sc, int dr, int dc, int[][] arr, int[][] qb) {
		// TODO Auto-generated method stub
		if (sr == dr && sc == dc) {
			return arr[sr][sc];
		}
		if (sr > dr || sc > dc) {
			return Integer.MAX_VALUE;
		}
		if (qb[sr][sc] != 0) {
			return qb[sr][sc];
		}
		int cstod = 0;

		int cihtod = mincostpathM(sr, sc + 1, dr, dc, arr, qb);
		int civtod = mincostpathM(sr + 1, sc, dr, dc, arr, qb);

		cstod = arr[sr][sc] + Math.min(cihtod, civtod);
		qb[sr][sc] = cstod;
		return cstod;
	}

	public static void minpath(int[][] strg, int r, int c, String psf) {
		if (r == strg.length - 1 && c == strg[0].length - 1) {
			System.out.println(psf);
		}

		else if (r == strg.length - 1) {
			minpath(strg, r, c + 1, psf + "h");
		} else if (c == strg[0].length - 1) {
			minpath(strg, r + 1, c, psf + "v");
		} else {
			if (strg[r + 1][c] > strg[r][c + 1]) {
				minpath(strg, r, c + 1, psf + "h");
			} else if (strg[r + 1][c] < strg[r][c + 1]) {
				minpath(strg, r + 1, c, psf + "v");
			} else {
				minpath(strg, r, c + 1, psf + "h");
				minpath(strg, r + 1, c, psf + "v");
			}
		}

	}

//	
	public static int mincostpathT(int[][] arr) {
		strg = new int[arr.length][arr[0].length];

		for (int i = arr.length-1; i >= 0; i--) {
			for (int j = arr[0].length-1; j >= 0; j--) {
				if (i == arr.length-1 && j == arr[0].length-1) {
					strg[i][j] = arr[i][j];
				} else if (i == arr.length-1) {
					strg[i][j] = strg[i][j + 1] + arr[i][j];
				} else if (j == arr[0].length-1) {
					strg[i][j] = strg[i + 1][j] + arr[i][j];
				} else {
					strg[i][j] = Math.min(strg[i + 1][j], strg[i][j + 1]) + arr[i][j];
				}

			}
		}

		return strg[0][0];

	}

}
