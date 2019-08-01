package dynamicProgramming;

public class LongestBitonicSubseq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
		
		lbs(arr);
	}
	
	public static void lbs(int[] arr) {
		int omax=0;
		String opath="";
		
		int[]lis=new int[arr.length];
		String[] lipath=new String[arr.length];
		lis[0]=1;
		lipath[0]=arr[0]+"";
		for(int i=1;i<arr.length;i++) {
			for(int j=0;j<i;j++) {
				if(arr[i]>arr[j]) {
					if(lis[j]>lis[i]) {
						lis[i]=lis[j];
						lipath[i]=lipath[j];
					}
				}
			}
			lis[i]++;

			lipath[i] = lipath[i] + " " + arr[i];
		}
		
		int[] lds=new int[arr.length];
		String[] ldpath=new String[arr.length];
		lds[arr.length-1]=1;
		ldpath[arr.length-1]=arr[arr.length-1]+"";
		for(int i=arr.length-2;i>=0;i--) {
			for(int j=arr.length-1;j>=i;j--) {
				if(arr[i]>arr[j]) {
					if(lds[j]>lds[i]) {
						lds[i]=lds[j];
						ldpath[i]=ldpath[j];
					}
				}
			}
			lds[i]++;
			ldpath[i]=+arr[i]+" "+ldpath[i];
		}
		
		for(int i=0;i<arr.length;i++) {
			if(lis[i]+lds[i]>omax) {
				omax=lis[i]+lds[i];
				opath=lipath[i]+" "+ldpath[i];
			}
		}
		System.out.println(omax+"@"+opath);
		
	}

}
