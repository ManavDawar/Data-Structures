package dynamicProgramming;

import java.util.Arrays;

public class CountMazePath {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(countMazePathM(0, 0, 2, 2, new int[3][3]));
		System.out.println(countMazePathT(2, 2));
		System.out.println(countS(2, 2));

	}

	private static int countMazePathT(int dr, int dc) {
		// TODO Auto-generated method stub
		int[][] qb = new int[dr + 1][dc + 1];

		for (int row = dr; row >= 0; row--) {
			for (int col = dc; col >= 0; col--) {
				if (row == dr && col == dc) {
					qb[row][col] = 1;
				} else if (row == dr) {
					qb[row][col] = 1;
				} else if (col == dc) {
					qb[row][col] = 1;
				} else {
					qb[row][col] = qb[row + 1][col] + qb[row][col + 1];
				}
			}
		}

		return qb[0][0];
	}

	private static int countMazePathM(int sr, int sc, int dr, int dc, int[][] qb) {
		// TODO Auto-generated method stub
		if (sr == dr && sc == dc) {
			return 1;
		}
		if (sr > dr || sc > dc) {
			return 0;
		}

		int count = 0;
		if (qb[sr][sc] != 0) {
			return qb[sr][sc];
		}
		int cihtod = countMazePathM(sr, sc + 1, dr, dc, qb);
		int civtod = countMazePathM(sr + 1, sc, dr, dc, qb);
		count = cihtod + civtod;
		return count;
	}

	public static int countS(int dr,int dc) {
		int [] qb = new int[dc+1];
		
		Arrays.fill(qb, 1);
		
		for(int nors=1;nors<=dr;nors++) {
			for(int nocs=qb.length-2;nocs>=0;nocs--) {
				qb[nocs]=qb[nocs]+qb[nocs+1];
			}
		}
		
		return qb[0];
	}
	
}
