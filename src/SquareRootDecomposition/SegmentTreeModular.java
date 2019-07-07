package SquareRootDecomposition;

import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.List;

public class SegmentTreeModular {

	public static interface IOperator {

		public int oepration(int lhs, int rhs);

		public int identity();
	}

	static class SumOperator implements IOperator {

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

	static class GCDOperator implements IOperator {

		@Override
		public int oepration(int lhs, int rhs) {
			if (lhs == 0) {
				return rhs;
			}

			return oepration(rhs % lhs, lhs);

		}

		@Override
		public int identity() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	static class MaxOperator implements IOperator {

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

	static class MinOperator implements IOperator {

		@Override
		public int oepration(int lhs, int rhs) {
			return Math.min(lhs, rhs);
		}

		@Override
		public int identity() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

	}

	public static class STree {
		int[] oa;
		int oas;
		int[] sa;
		int sas;
		IOperator ioOperator;

		public int getlog(int n) {
			int x = 0;
			while ((1 << x) < n) {
				x++;
			}

			return x;
		}

		public STree(int[] oa, int oas, IOperator ioOperator) {
			this.ioOperator = ioOperator;
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

			sa[si] = ioOperator.oepration(sa[2 * si + 1], sa[2 * si + 2]);
		}

		public void update(int idx, int nv, int ss, int se, int si) {
			if (ss == se && ss == idx) {
				sa[si] = nv;
				return;
			}

			int mid = (ss + se) / 2;
			if (idx <= mid) {
				update(idx, nv, ss, mid, 2 * si + 1);
			} else {
				update(idx, nv, mid + 1, se, 2 * si + 2);
			}

			sa[si] = ioOperator.oepration(sa[2 * si + 1], sa[2 * si + 2]);
		}

		public int query(int qs, int qe, int ss, int se, int si) {

			if (ss >= qs && se <= qe) {
				return sa[si];
			} else if (se < qs || ss > qe) {
				return ioOperator.identity();
			} else {
				int mid = (ss + se) / 2;
				int lc = query(qs, qe, ss, mid, 2 * si + 1);
				int rc = query(qs, qe, mid + 1, se, 2 * si + 2);
				return ioOperator.oepration(lc, rc);
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 2, 5, 3, 6, 42, 14, 98, 9 };
//		STree st= new STree(arr, arr.length,new SumOperator());
//	
//		System.out.println(st.query(2, 5, 0, arr.length-1, 0));
//		System.out.println(st.query(5, 7, 0, arr.length-1, 0));
//		st.update(4, 10, 0, arr.length, 0);
//		System.out.println(st.query(2, 5, 0, arr.length-1, 0));
//		System.out.println(st.query(5, 7, 0, arr.length-1, 0));
//  
		STree st = new STree(arr, arr.length, new GCDOperator());

		System.out.println(st.query(2, 5, 0, arr.length - 1, 0));
		System.out.println(st.query(5, 7, 0, arr.length - 1, 0));
		st.update(4, 20, 0, arr.length, 0);
		System.out.println(st.query(2, 5, 0, arr.length - 1, 0));
		System.out.println(st.query(5, 6, 0, arr.length - 1, 0));
		List<Integer> list=new ArrayList<>();

	}

}
