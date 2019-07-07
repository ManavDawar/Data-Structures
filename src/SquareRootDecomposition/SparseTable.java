package SquareRootDecomposition;

public class SparseTable {
	static int[][] sparse;
	static int[] logs;

	public static int getLog(int n) {
		int x = 0;
		while (1 << x <= n) {
			x++;
		}
		return x - 1;
	}

	public static void build(int[] arr) {
		logs = new int[arr.length + 1];
		for (int i = 0; i <= arr.length; i++) {
			logs[i] = getLog(i);
		}
		sparse = new int[arr.length][logs[arr.length] + 1];

		for (int j = 0; j < sparse[0].length; j++) {
			for (int i = 0; i < sparse.length; i++) {
				if (j == 0) {
					sparse[i][j] = arr[i];
				} else {
					if (i + (1 << (j - 1)) < arr.length) {
						sparse[i][j] = Math.min(sparse[i][j - 1], sparse[i + (1 << (j - 1))][j - 1]);
					}
				}
			}
		}

	}

	public static int query(int left, int right) {
		int n = right - left + 1;
		int log = logs[n];
		int segment = (1 << log);
		return Math.min(sparse[left][log], sparse[right - segment + 1][log]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 2, 4, -1, 6, 8, -4, 7, 12, 5, 4, -9, 3, 20, -16, 4, 11 };
		build(arr);
		System.out.println(query(10, 15));
		System.out.println(query(6, 12));
		System.out.println(query(2, 5));

	}

}
