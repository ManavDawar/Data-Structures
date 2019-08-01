package dynamicProgramming;

public class GoldMine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr1 = { { 4, 5, 7, 3, 5 }, { 6, 7, 2, 0, 2 }, { 3, 0, 5, 4, 6 }, { 2, 4, 3, 0, 3 },
				{ 5, 7, 6, 4, 2 } };
		
		System.out.println(goldmineMcall(arr1));
		System.out.println(goldmine(arr1));
//		printpath(strg, 0, 0, "");

	}
	
	public static int goldmineMcall(int[][] mine) {
		int omax=Integer.MIN_VALUE;
		
		for(int r=0;r<mine.length;r++) {
			int res=goldMineM(mine,r,0,new int[mine.length][mine[0].length]);
			omax=Math.max(omax, res);
		}
		
		return omax;
	}
	
	public static int goldMineM(int[][] mine,int r,int c,int[][] strg) {
		if(c==mine[0].length-1) {
			return mine[r][c];
		}
		
		
		int gstod=0;
		int rm1tod=Integer.MIN_VALUE;
		int rtod=Integer.MIN_VALUE;
		int rp1tod=Integer.MIN_VALUE;
		if(strg[r][c]!=0) {
			return strg[r][c];
		}
		if(r>0) {
			rm1tod=goldMineM(mine, r-1, c+1,strg);
		}
		rtod=goldMineM(mine, r, c+1,strg);
		if(r<mine.length-1) {
			rp1tod=goldMineM(mine, r+1, c+1,strg);
		}
		gstod=mine[r][c]+Math.max(rm1tod, Math.max(rtod, rp1tod));
		strg[r][c]=gstod;
		
		return gstod;
		
	}

	static int[][] strg;

	public static int goldmine(int[][] arr) {
		strg = new int[arr.length][arr[0].length];
		int max = Integer.MIN_VALUE;

		for (int c = arr[0].length - 1; c >= 0; c--) {
			for (int r = arr.length - 1; r >= 0; r--) {
				if (c == arr[0].length - 1) {
					strg[r][c] = arr[r][c];
				} else if (r == 0) {
					strg[r][c] = Math.max(strg[r][c + 1], strg[r + 1][c + 1]) + arr[r][c];
				} else if (r == arr.length - 1) {
					strg[r][c] = Math.max(strg[r][c + 1], strg[r - 1][c + 1])+arr[r][c];
				} else {
					strg[r][c] = Math.max(strg[r][c + 1], Math.max(strg[r + 1][c + 1], strg[r - 1][c + 1])) + arr[r][c];
				}
				if (strg[r][c] > max) {
					max = strg[r][c];
				}
			}

		}

		return max;
	}
	
	public static void printpath(int[][] strg,int r,int c,String psf) {
		if (c == 0) {
			int omax = 0;
			for (int i = 0; i < strg[0].length; i++) {
				if (strg[i][0] > omax) {
					omax = strg[i][0];
					
					r = i;
				}
			}
		}
		
		if(c==strg[0].length-1) {
			System.out.println(psf);
		}else if(r==0) {
			int omax=Math.max(strg[r][c+1], strg[r+1][c+1]);
			if(strg[r][c+1]==omax) {
				printpath(strg, r, c+1, psf+"h");
			}
			if(strg[r+1][c+1]==omax) {
				printpath(strg, r+1, c+1, psf+"d");
			}
			
		}else if(r==strg.length-1) {
			
			int omax=Math.max(strg[r][c+1], strg[r-1][c+1]);
			if(strg[r][c+1]==omax) {
				printpath(strg, r, c+1, psf+"h");
			}
			if(strg[r-1][c+1]==omax) {
				printpath(strg, r-1, c+1, psf+"u");
			}
			
		}else {
			int omax=Math.max(strg[r][c+1],Math.max(strg[r-1][c+1], strg[r+1][c+1]));
			if(strg[r][c+1]==omax) {
				printpath(strg, r, c+1, psf+"h");
			}
			if(strg[r+1][c+1]==omax) {
				printpath(strg, r+1, c+1, psf+"d");
			}
			if(strg[r-1][c+1]==omax) {
				printpath(strg, r-1, c+1, psf+"u");
			}
		}
		
		
	}

}
