package dynamicProgramming;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
		int[] arr2= {1,2,4,3,5,4,7,2};
		System.out.println(findNumberOfLIS(arr2));
//		lis(arr);
//		System.out.println(lisM(arr));
	}

	 public static int findNumberOfLIS(int[] arr) {
	        if(arr.length==0||arr.length==1){
	            return arr.length;
	        }
	        
	        int[] lis = new int[arr.length];

			int omax = 0;
	        int counter=0;

			lis[0] = 1;

			for (int i = 1; i < arr.length; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[i] > arr[j]) {
						if (lis[j] > lis[i]) {
							lis[i] = lis[j];
						}
					}
				}

				lis[i] = lis[i] + 1;
				if (lis[i] > omax) {
					counter=1;
					omax=lis[i];
				}
	            if(lis[i]==omax){
	                counter++;
	            }
			}
	        return counter;
	    }
	public static int lisM(int[] arr) {
		int omax = 0;
		for (int i = 1; i < arr.length; i++) {
			int max = lisendatpoint(arr, i, new int[arr.length]);
			if (max > omax) {
				omax = max;
			}
		}
		return omax;

	}

	public static int lisendatpoint(int[] arr, int point, int[] qb) {
		if (point == 0) {
			return 1;
		}
		if (qb[point] != 0) {
			return qb[point];
		}

		int lisendatpoint = 0;

		for (int i = 0; i < point; i++) {
			if (arr[i] < arr[point]) {
				int lisendati = lisendatpoint(arr, i, qb);
				if (lisendati > lisendatpoint) {
					lisendatpoint = lisendati;
				}
			}
		}
		lisendatpoint += 1;
		qb[point] = lisendatpoint;
		return lisendatpoint;

	}

	public static void lis(int[] arr) {
		int[] lis = new int[arr.length];
		String[] lpath = new String[arr.length];

		int omax = 0;
		String opath = "";

		lis[0] = 1;
		lpath[0] = arr[0] + "";

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (lis[j] > lis[i]) {
						lis[i] = lis[j];
						lpath[i] = lpath[j];
					}
				}
			}

			lis[i] = lis[i] + 1;
			lpath[i] = lpath[i] + " " + arr[i];
			if (lis[i] > omax) {
				omax = lis[i];
				opath = lpath[i];
			}
		}

		System.out.println(omax + "@" + opath);
	}

}
