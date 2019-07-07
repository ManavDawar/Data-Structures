package dynamicProgramming;

public class CountBoardPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(countboardpath(0, 30));
//
		System.out.println(countboardpathM(0, 30, new int[30 + 1]));
//
		System.out.println(countboardpathT(0, 30));

//		System.out.println(gamepadT6(0, 10));
	}

	private static int countboardpath(int src, int des) {
		// TODO Auto-generated method stub
		if (src > des) {
			return 0;
		}

		if (src == des) {
			return 1;
		}
		int cstod = 0;

		for (int dice = 1; dice <= 6; dice++) {
			int intermediate = src + dice;

			int citod = countboardpath(intermediate, des);
			cstod += citod;

		}

		return cstod;
	}

	public static int countboardpathM(int src, int des, int qb[]) {

		if (src > des) {
			return 0;
		}

		if (src == des) {
			return 1;
		}
		int cstod = 0;
		if (qb[src] != 0) {
			return qb[src];
		}
		for (int dice = 1; dice <= 6; dice++) {
			int intermediate = src + dice;

			int citod = countboardpathM(intermediate, des, qb);

			cstod += citod;
		}

		qb[src] = cstod;
		return cstod;
	}

	public static int countboardpathT(int src, int des) {
		int[] f=new int[des+1];
		f[des]=1;
		
		for(int i=f.length-1;i>=0;i--) {
			for(int dice=1;dice<=6&&i+dice<f.length;dice++) {
				f[i]+=f[i+dice];
			}
		}
		
		return f[0];
	}

	public static int gamepadT6(int src, int des) {

		int[] s = new int[6];

		s[0] = 1;

		for (int slides = 1; slides <= des; slides++) {
			int nv = s[0] + s[1] + s[2] + s[3] + s[4] + s[5];

			s[5] = s[4];
			s[4] = s[3];
			s[3] = s[2];
			s[2] = s[1];
			s[1] = s[0];
			s[0] = nv;

		}

		return s[0];
	}
}
