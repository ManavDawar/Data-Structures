package dynamicProgramming;

public class RodCutting {

	public static void main(String[] args) {
		int[] arr = { 0, 3, 5, 6, 15, 10, 25, 12, 24 };
		rodCutT(arr);
		System.out.println(rodCutM(arr, 8, new int[arr.length]));

	}
	
	public static int rodCutM(int[] arr,int rl,int[] qb) {
		if(rl==0) {
			return 0;
		}
		if(qb[rl]!=0) {
			return qb[rl];
		}
		int msp=arr[rl];
		int left=1;
		int right=rl-1;
		while(left<=right) {
			int mspl=rodCutM(arr, left, qb);
			int mspr=rodCutM(arr, right, qb);
			if(msp<mspl+mspr) {
				msp=mspl+mspr;
			}
			left++;
			right--;
		}
		qb[rl]=msp;
		return msp;
		
		
	}

	public static void rodCutT(int[] arr) {
		int[] strg = new int[arr.length];
		String[] path = new String[arr.length];

		strg[0] = 0;
		strg[1] = arr[1];

		path[0] = "";
		path[1] = "1";

		for (int i = 2; i < arr.length; i++) {
			strg[i] = arr[i];
			path[i] = i + "";
			int left = 1;
			int right = i - 1;
			while (left <= right) {
				if (strg[i] < strg[left] + strg[right]) {
					strg[i] = strg[left] + strg[right];
					path[i] = path[left] + path[right];
				}
				left++;
				right--;
			}
		}

		System.out.println(strg[strg.length - 1] + "@" + path[path.length - 1]);
	}

}
