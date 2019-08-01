package dynamicProgramming;

public class ClimbStairs {

	public static void main(String[] args) {
		// TODO Auto-gen0erated method stub
		System.out.println(climbStairsM(7, new int[7 + 1]));
		System.out.println(climbStairsT(7));
	
	}

	public static int climbStairsM(int n, int[] qb) {
		
		if (n == 0) {
			return 1;
		}
		int count = 0;
		if (qb[n] != 0) {
			return qb[n];
		}
		
		for (int i = 1; i <= 3 && n - i >= 0; i++) {
			count += climbStairsM(n - i, qb);
		}
		
		qb[n] = count;
		
		return count;
	}

	public static int climbStairsT(int n) {
		int[] qb = new int[n + 1];

		qb[0] = 1;

		for (int i = 1; i <= n; i++) {
			if (i >= 1) {
				qb[i] += qb[i - 1];
			}
			if (i >= 2) {
				qb[i] += qb[i - 2];
			}
			if (i >= 3) {
				qb[i] += qb[i - 3];
			}
		}

		return qb[n];
	}

}
