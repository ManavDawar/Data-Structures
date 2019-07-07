package SquareRootDecomposition;

public class SegmentTree {

	public interface IOperator {

		public int oepration(int lhs, int rhs);

		public int identity();
	}

	class SumOperator implements IOperator {

		@Override
		public int oepration(int lhs, int rhs) {
			return lhs + rhs;
		}

		@Override
		public int identity() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	class MaxOperator implements IOperator {

		@Override
		public int oepration(int lhs, int rhs) {
			return Math.max(lhs, rhs);
		}

		@Override
		public int identity() {
			// TODO Auto-generated method stub
			return Integer.MIN_VALUE;
		}

	}

	public static class STree {
		int[] oa;
		int oas;
		int[] sa;
		int sas;

		public int getlog(int n) {
			int x = 0;
			while ((1 << x) < n) {
				x++;
			}

			return x;
		}

		public STree(int[] oa, int oas) {
			this.oa = oa;
			this.oas = oas;
			int h = getlog(oas) + 1;
			this.sas = (1 << h) - 1;
			this.sa = new int[sas];
			build(0, oas - 1, 0);
		}

		public void build(int ss, int se, int si) {
			if (ss == se) {
				sa[si] = oa[ss];
				return;
			}

			int mid = (ss + se) / 2;

			build(ss, mid, 2 * si + 1);
			build(mid + 1, se, 2 * si + 2);

			sa[si] = sa[2 * si + 1] + sa[2 * si + 2];
		}

		public void update(int idx, int delta, int ss, int se, int si) {
			if (ss == se && ss == idx) {
				sa[si] += delta;
				return;
			}

			int mid = (ss + se) / 2;
			if (idx <= mid) {
				update(idx, delta, ss, mid, 2 * si + 1);
			} else {
				update(idx, delta, mid + 1, se, 2 * si + 2);
			}

			sa[si] = sa[2 * si + 1] + sa[2 * si + 2];

		}

		public int query(int qs,int qe,int ss,int se,int si) {
			
			if(ss>=qs&&se<=qe) {
				return sa[si];
			}
			else if(se<qs||ss>qe) {
				return 0;
			}else {
				int mid=(ss+se)/2;
				int lc=query(qs, qe, ss, mid, 2*si+1);
				int rc=query(qs, qe, mid+1, se, 2*si+2);
				return lc+rc;
			}
			
	

			
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			int[] arr= {2,5,-3,6,7,14,1,0};
			STree st= new STree(arr, arr.length);
		
			System.out.println(st.query(2, 5, 0, arr.length-1, 0));
			System.out.println(st.query(5, 7, 0, arr.length-1, 0));
			st.update(4, 17, 0, arr.length, 0);
			System.out.println(st.query(2, 5, 0, arr.length-1, 0));
			System.out.println(st.query(5, 7, 0, arr.length-1, 0));
	  
			
		
		}
	}

}
