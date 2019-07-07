package dynamicProgramming;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fibm(42, new int[43+1]));
		System.out.println(fibt(43));
		System.out.println(fibslider(43));
	}
	
	public static int fibm(int n,int[] qb) {
		if(n==0||n==1) {
			return 1;
		}
		
		if(qb[n]!=0) {
			return qb[n];
		}
		
		int fibm1=fibm(n-1, qb);
		int fibm2=fibm(n-2, qb);
		int fn=fibm1+fibm2;
		
		qb[n]=fn;
		
		return fn;
		
	}
	
	public static int fibt(int n) {
		int[] f=new int[n+1];
		f[0]=0;
		f[1]=1;
		
		for(int x=2;x<=n;x++) {
			f[x]=f[x-1]+f[x-2];
		}
		return f[n];
	}
	
	public static int fibslider(int n) {
		int[] slider=new int[2];
		slider[0]=0;
		slider[1]=1;
		
		for(int i=1;i<n;i++) {
			int sum=slider[0]+slider[1];
			slider[0]=slider[1];
			slider[1]=sum;
		}
		
		return slider[1];
	}

}
