package dynamicProgramming;

public class EggDrop {

	public static void main(String[] args) {
		System.out.println(eggdrop(2,64,new int[3][65]));
		System.out.println(eggdropT(12, 8191));
	}

	private static int eggdrop(int eggs, int floor, int[][] qb) {
		if(eggs==1) {
			return floor;
		}
		if(floor==1) {
			return 1;
		}
		if(floor==0) {
			return 0;
		}
		if(qb[eggs][floor]!=0) {
			return qb[eggs][floor];
		}
		int minval=Integer.MAX_VALUE;
		for(int f=1;f<=floor;f++) {
			int eggbreak=eggdrop(eggs-1, floor-f, qb);
			int eggnotbreak=eggdrop(eggs, f-1, qb);
			
			minval=Math.min(minval, Math.max(eggbreak, eggnotbreak));
		}
		qb[eggs][floor]=minval+1;
		return minval+1;
	}
	
	public static int eggdropT(int eggs,int floors) {
		int[][] arr=new int[eggs+1][floors+1];
		
		for(int e=1;e<=eggs;e++) {
			for(int f=1;f<=floors;f++) {
				
				if(f==1) {
					arr[e][f]=1;
				}else if(e==1) {
					arr[e][f]=f;
				}else {
					int minval=Integer.MAX_VALUE;
					for(int k=1;k<=f;k++) {
						int eggbreak=arr[e-1][k-1];
						int eggnotbreak=arr[e][f-k];
						
						minval=Math.min(minval, Math.max(eggbreak, eggnotbreak));
					}
					arr[e][f]=minval+1;
				}
				
			}
		}
		
		return arr[eggs][floors];
	}
	
}
