package SquareRootDecomposition;

import java.util.Arrays;

public class SquareRootDecomposition {
	static int[] oa;
	static int[] sa;
	static int sob;
	static int nob;

	public static void build(int[] arr) {
		oa = arr;
		sob = (int) Math.sqrt(arr.length);
		nob = sob;
		sa = new int[nob];
		for (int i = 0; i < nob * sob; i++) {
			int bi = i / sob;
			sa[bi] += arr[i];
		}
	}

	public static int query(int left, int right) {

		int sum = 0;
		int lbi = left / sob;
		int rbi = right / sob;

		if (lbi == rbi) {
			for (int i = left; i <= right; i++) {
				sum += oa[i];
			}
			return sum;
		}

		for (int i = left; i / sob == lbi; i++) {
			sum += oa[i];
		}
		for (int i = lbi + 1; i <= rbi - 1; i++) {
			sum += sa[i];
		}
		for (int i = right; i / sob == rbi; i--) {
			sum += oa[i];
		}
		return sum;

	}

	public static void update(int idx, int val) {
		int oval = oa[idx];

		int bi = idx / sob;
		sa[bi] += (val - oval);
		oa[idx] = val;
	}

	public static void main(String[] args) {

		int arr[] = { 88, 70, 30, 56, 29, 2, 71, 50, 92, 53, 75, 79, 73, 49, 17, 65, 29, 14, 92, 79, 16, 91, 46, 69, 54,
				69, 11, 24, 98, 89, 30, 48, 31, 85, 51, 16, 34, 81, 36, 69, 90, 12, 25, 36, 44, 93, 3, 95, 60, 1000,
				2000, 3000, 4000 };
		build(arr);
		System.out.println(Arrays.toString(arr));

		System.out.println(query(45, 51));
		System.out.println(query(11, 36));
		System.out.println(query(1, 20));
		System.out.println(query(25, 27));
		update(15, 100);
		System.out.println(query(15, 46));
		System.out.println(query(11, 36));
		System.out.println(query(1, 20));
		System.out.println(query(25, 27));

	}
}
