package dynamicProgramming;

import java.util.ArrayList;

public class CoinChangeCombinations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]  denoms= {2,3,5,6};
		cccT(denoms,10);
		System.out.println(cccM(denoms, 10, 0, new ArrayList[10+1][denoms.length]));
	}

	private static ArrayList<String> cccM(int[] denoms,int amt,int lastpaydoneindex,ArrayList[][] qb){
		if(amt==0) {
			ArrayList<String> bresult=new ArrayList<>();
			bresult.add("");
			return bresult;
		}
		if(amt<0) {
			ArrayList<String> bresult=new ArrayList<>();
			return bresult;
		}
		if(qb[amt][lastpaydoneindex]!=null) {
			return qb[amt][lastpaydoneindex];
		}
		ArrayList<String> mres=new ArrayList<>();
		
		for(int i=lastpaydoneindex;i<denoms.length;i++) {
			ArrayList<String> recres=cccM(denoms, amt-denoms[i],i, qb);
			for(String str:recres) {
				String m=denoms[i]+str;
				mres.add(m);
			}
		}
		qb[amt][lastpaydoneindex]=mres;
		return mres;
		
	}
	private static void cccT(int[] denoms, int amt) {		
		int[] strg=new int[amt+1];
		ArrayList<String>[] res=new ArrayList[amt+1];
		
		strg[0]=1;
		
		for(int i=0;i<res.length;i++) {
			res[i]=new ArrayList<>();
		}

		res[0].add("");
		
		for(int i=0;i<denoms.length;i++) {
			for(int j=denoms[i];j<strg.length;j++) {
				strg[j]+=strg[j-denoms[i]];
				
				for(String rstr:res[j-denoms[i]]) {
					String mystr=rstr+denoms[i];
					res[j].add(mystr);
				}
			}
		}
		
		System.out.println(strg[strg.length-1]);
		System.out.println(res[res.length-1]);
	}

}
