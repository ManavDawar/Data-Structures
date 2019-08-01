package dynamicProgramming;

import java.util.ArrayList;

public class CoinChangePermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] denoms = { 2, 3, 5, 6 };
		ccpT(denoms, 10);
		System.out.println(ccpM(denoms, 10, new ArrayList[11]));
	}
	
	private static ArrayList<String> ccpM(int[] denoms,int amt,ArrayList[] qb){
		if(amt==0) {
			ArrayList<String> bresult=new ArrayList<>();
			bresult.add("");
			return bresult;
		}
		if(amt<0) {
			ArrayList<String> bresult=new ArrayList<>();
			return bresult;
		}
		if(qb[amt]!=null) {
			return qb[amt];
		}
		ArrayList<String> mres=new ArrayList<>();
		
		for(int i=0;i<denoms.length;i++) {
			ArrayList<String> recres=ccpM(denoms, amt-denoms[i], qb);
			
			for(String str:recres) {
				String m=str+denoms[i];
				mres.add(m);
			}
		}
		qb[amt]=mres;
		
		return mres;
		
	}

	private static void ccpT(int[] denoms, int amt) {
		int[] strg = new int[amt + 1];
		ArrayList<String>[] res = new ArrayList[amt + 1];

		strg[0] = 1;

		for (int i = 0; i < res.length; i++) {
			res[i] = new ArrayList<>();
		}

		res[0].add("");
		for (int j =1; j < strg.length; j++) {
			for (int i = 0; i < denoms.length; i++) {
				if (j >= denoms[i]) {
					strg[j] += strg[j - denoms[i]];

					for (String rstr : res[j - denoms[i]]) {
						String mystr = rstr + denoms[i];
						res[j].add(mystr);
					}
				}
			}
		}

		System.out.println(strg[strg.length - 1]);
		System.out.println(res[res.length - 1]);
	}

}
