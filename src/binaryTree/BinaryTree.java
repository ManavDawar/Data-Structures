package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	private class Node {
		int data;
		Node left;
		Node right;
	}

	private Node root;
	private int size;

	public BinaryTree(int[] arr) {
		Stack<Node> st = new Stack<>();

		for (int val : arr) {
			if (val == -1) {
				st.pop();
			} else {
				Node node = new Node();
				this.size++;
				node.data = val;
				if (st.size() == 0) {
					root = node;
				} else {
					if (st.peek().left == null) {
						st.peek().left = node;
					} else {
						st.peek().right = node;
					}
				}
				st.push(node);
			}
		}
	}

	public void display() {
		display(root);
	}

	private void display(Node node) {
		if (node == null) {
			return;
		}
		String str = "";

		str += node.left != null ? node.left.data + "" : ".";
		str += "<-" + node.data + "->";
		str += node.right != null ? node.right.data + "" : ".";

		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		}

		int ls = size(node.left);
		int rs = size(node.right);

		return ls + rs + 1;
	}

	public int max() {
		return max(root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int lm = max(node.left);
		int rm = max(node.right);

		return Math.max(lm, Math.max(rm, node.data));
	}

	public int min() {
		return min(root);
	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}

		int lm = min(node.left);
		int rm = min(node.right);

		return Math.min(lm, Math.min(rm, node.data));
	}

	public int height() {
		return height(root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}

		int lh = height(node.left);
		int rh = height(node.right);

		return Math.max(lh, rh) + 1;
	}

	public boolean find(int data) {
		return find(root, data);
	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}
		if (node.data == data) {
			return true;
		}
		boolean found = find(node.left, data);
		if (found == true) {
			return true;
		}
		boolean found2 = find(node.right, data);
		if (found2 == true) {
			return true;
		}
		return false;
	}

	public void target(int low, int high) {

		target(root, low, high, 0, "");

	}

	private void target(Node node, int low, int high, int ssf, String psf) {

		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			ssf += node.data;
			psf += node.data + " ";
			if (ssf < high && ssf > low) {
				System.out.println(psf);
			}
			return;
		}

		ssf += node.data;
		psf += node.data + " ";

		target(node.left, low, high, ssf, psf);
		target(node.right, low, high, ssf, psf);
	}

	public void levelorder() {
		Queue<Node> q = new LinkedList<>();

		q.add(root);
		while (q.size() > 0) {
			Node rem = q.remove();
			System.out.print(rem.data + " ");

			if (rem.left != null) {
				q.add(rem.left);
			}

			if (rem.right != null) {
				q.add(rem.right);
			}

		}

	}

	public class Pair {
		Node node;
		int wc = 0;

		public Pair(Node node, int wc) {
			this.node = node;
			this.wc = wc;
		}
	}

	public void preoiterative() {
		Stack<Pair> st = new Stack<>();
		st.push(new Pair(root, 0));

		while (st.size() > 0) {
			Pair rem = st.peek();
			if (rem.wc == 0) {
				System.out.print(rem.node.data + " ");
			} else if (rem.wc == 1) {
				if (rem.node.left != null) {
					st.push(new Pair(rem.node.left, 0));
				}
			} else if (rem.wc == 2) {
				if (rem.node.right != null) {
					st.push(new Pair(rem.node.right, 0));
				}
			} else {
				st.pop();
			}
			rem.wc++;
		}
	}

	public void postoiterative() {
		Stack<Pair> st = new Stack<>();
		st.push(new Pair(root, 0));

		while (st.size() > 0) {
			Pair rem = st.peek();
			if (rem.wc == 0) {
				if (rem.node.left != null) {
					st.push(new Pair(rem.node.left, 0));
				}
			} else if (rem.wc == 1) {
				if (rem.node.right != null) {
					st.push(new Pair(rem.node.right, 0));
				}
			} else if (rem.wc == 2) {
				System.out.print(rem.node.data + " ");
			} else {
				st.pop();
			}
			rem.wc++;
		}
	}

	public void inoiterative() {
		Stack<Pair> st = new Stack<>();
		st.push(new Pair(root, 0));

		while (st.size() > 0) {
			Pair rem = st.peek();
			if (rem.wc == 0) {
				if (rem.node.left != null) {
					st.push(new Pair(rem.node.left, 0));
				}
			} else if (rem.wc == 1) {
				System.out.print(rem.node.data + " ");
			} else if (rem.wc == 2) {
				if (rem.node.right != null) {
					st.push(new Pair(rem.node.right, 0));
				}
			} else {
				st.pop();
			}
			rem.wc++;
		}
	}

	public void singlechild() {
		singlechild(root, root.left);
		singlechild(root, root.right);
	}

	private void singlechild(Node parent, Node child) {
		if (child == null) {
			return;
		}
		if ((parent.left == child && parent.right == null) || (parent.right == child && parent.left == null)) {
			System.out.print(child.data + " ");
		}
		singlechild(child, child.left);
		singlechild(child, child.right);

	}

	public void removeleaves() {
		removeleaves(root, root.left);
		removeleaves(root, root.right);
	}

	private void removeleaves(Node parent, Node child) {
		if (child == null) {
			return;
		}
		if (child.left == null && child.right == null) {
			if (parent.left == child) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}

		removeleaves(child, child.left);
		removeleaves(child, child.right);

	}

	public ArrayList<Integer> nodetorootpath(int data) {
		return nodetorootpath(root, data);
	}

	private ArrayList<Integer> nodetorootpath(Node node, int data) {
		if (node == null) {
			return new ArrayList<>();
		}
		if (node.data == data) {
			ArrayList<Integer> bresult = new ArrayList<>();
			bresult.add(node.data);
			return bresult;
		}

		ArrayList<Integer> lres = nodetorootpath(node.left, data);
		if (lres.size() > 0) {
			lres.add(node.data);
			return lres;
		}
		ArrayList<Integer> rres = nodetorootpath(node.right, data);
		if (rres.size() > 0) {
			rres.add(node.data);
			return rres;
		}
		return new ArrayList<>();
	}

	private ArrayList<Node> nodetorootpathNodes(Node node, int data) {
		if (node == null) {
			return new ArrayList<>();
		}
		if (node.data == data) {
			ArrayList<Node> bresult = new ArrayList<>();
			bresult.add(node);
			return bresult;
		}

		ArrayList<Node> lres = nodetorootpathNodes(node.left, data);
		if (lres.size() > 0) {
			lres.add(node);
			return lres;
		}
		ArrayList<Node> rres = nodetorootpathNodes(node.right, data);
		if (rres.size() > 0) {
			rres.add(node);
			return rres;
		}
		return new ArrayList<>();
	}

	private void printkdown(Node node, int k) {
		if (k < 0 || node == null) {
			return;
		}
		if (k == 0) {
			System.out.println(node.data);
		}

		printkdown(node.left, k - 1);
		printkdown(node.right, k - 1);
	}

	public void printkfar(int k, int data) {

		ArrayList<Node> path = nodetorootpathNodes(root, data);

		for (int i = 0; i < path.size() && i <= k; i++) {
			if (i == 0) {
				Node node = path.get(i);
				printkdown(node, k);
			} else {
				Node curr = path.get(i);
				Node prev = path.get(i - 1);
				int z = k;
				if (z - 1 == 0) {
					System.out.println(curr.data);
					continue;
				}

				if (curr.left == prev) {
					printkdown(curr.right, k - i - 1);
				} else {
					printkdown(curr.left, k - i - 1);
				}
			}

		}

	}

	public void nodetoLeafPathSum(int tar) {
		nodetoLeafPathSum(root, 0, "", tar);
	}

	private void nodetoLeafPathSum(Node node, int ssf, String psf, int tar) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			ssf += node.data;
			if (ssf < tar) {
				System.out.println(psf + node.data + " " + "@" + ssf);
			}
			return;
		}

		nodetoLeafPathSum(node.left, ssf + node.data, psf + node.data + " ", tar);
		nodetoLeafPathSum(node.right, ssf + node.data, psf + node.data + " ", tar);
	}

	public BinaryTree(int[] pre, int[] in) {
		this.root = construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
		this.size = pre.length;
	}

	private Node construct(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
		if (psi > pei || isi > iei) {
			return null;
		}

		Node node = new Node();
		node.data = pre[psi];

		int idx = -1;
		for (int i = isi; i <= iei; i++) {
			if (pre[psi] == in[i]) {
				idx = i;
				break;
			}
		}
		int lhe = idx - isi;
		node.left = construct(pre, psi + 1, psi + lhe, in, isi, idx - 1);
		node.right = construct(pre, psi + lhe + 1, pei, in, idx + 1, iei);

		return node;
	}

	public BinaryTree(int[] post, int[] in, boolean flag) {
		root = construct2(post, 0, post.length - 1, in, 0, in.length - 1);
	}

	private Node construct2(int[] post, int psi, int pei, int[] in, int isi, int iei) {
		if (psi > pei || isi > iei) {
			return null;
		}
		Node node = new Node();
		node.data = post[pei];
		size++;
		int idx = -1;

		for (int i = isi; i <= iei; i++) {
			if (post[pei] == in[i]) {
				idx = i;
				break;
			}
		}
		int lelements = idx - isi;

		node.left = construct2(post, psi, psi + lelements - 1, in, isi, idx - 1);
		node.right = construct2(post, psi + lelements, pei - 1, in, idx + 1, iei);

		return node;

	}

	public int diameter() {
		return diameter(root);
	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}

		int ld = diameter(node.left);// distance of farthest nodes in left subtree
		int rd = diameter(node.right);// distance of farthest nodes in left subtree

		int lh = height(node.left);// height of depest node in left subtree
		int rh = height(node.right);// height of depest node in left subtree

		int factor = lh + rh + 2;

		return Math.max(factor, Math.max(ld, rd));
	}

	public class Dipair {
		int height;
		int diameter;
	}

	public int diameter2() {
		return diameter2(root).diameter;
	}

	private Dipair diameter2(Node node) {
		if (node == null) {
			Dipair bpair = new Dipair();
			bpair.height = -1;
			bpair.diameter = 0;
			return bpair;
		}

		Dipair left = diameter2(node.left);
		Dipair right = diameter2(node.right);

		Dipair mpair = new Dipair();
		mpair.height = Math.max(left.height, right.height) + 1;
		mpair.diameter = Math.max(left.height + right.height + 2, Math.max(left.diameter, right.diameter));
		return mpair;
	}

	int ans;

	public int diameter3() {
		return diameterOfBinaryTree(root);
	}

	public int diameterOfBinaryTree(Node root) {
		ans = 1;
		depth2(root);
		return ans - 1;
	}

	public int depth2(Node node) {

		if (node == null)
			return 0;
		int L = depth2(node.left);
		int R = depth2(node.right);
		ans = Math.max(ans, L + R + 1);
		return Math.max(L, R) + 1;
	}

	public boolean isBalanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
		}

		boolean lp = isBalanced(node.left);
		boolean rp = isBalanced(node.right);
		int lh = height(node.left);
		int rh = height(node.right);
		return lp && rp && Math.abs(rh - lh) <= 1;
	}

	public class BalPair {
		boolean isbalanced;
		int height;
	}

	public boolean isbal2() {
		return isbal2(root).isbalanced;
	}

	private BalPair isbal2(Node node) {
		if (node == null) {
			BalPair bp = new BalPair();
			bp.isbalanced = true;
			bp.height = -1;
			return bp;
		}

		BalPair lp = isbal2(node.left);
		BalPair rp = isbal2(node.right);

		BalPair mp = new BalPair();
		mp.isbalanced = lp.isbalanced && rp.isbalanced && Math.abs(lp.height - rp.height) <= 1;
		mp.height = Math.max(lp.height, rp.height) + 1;
		return mp;
	}

	public boolean isBst() {
		return isBst(root);
	}

	private boolean isBst(Node node) {
		if (node == null) {
			return true;
		}
		int ldata = max(node.left);
		int rdata = min(node.right);

		if (node.data < ldata || node.data > rdata) {
			return false;
		}

		boolean left = isBst(node.left);
		if (!left) {
			return false;
		}
		boolean right = isBst(node.right);
		if (!right) {
			return false;
		}
		
		return true;
	}
	
	
	public class BstPair{
		boolean isbst;
		int max;
		int min;
	}
	
	public boolean isBst2() {
		return isBst2(root).isbst;
	}
	
	private BstPair isBst2(Node node) {
		if(node==null) {
			BstPair bp=new BstPair();
			bp.isbst=true;
			bp.max=Integer.MIN_VALUE;
			bp.min=Integer.MAX_VALUE;
			return bp;
		}
		
		
		BstPair l=isBst2(node.left);
		BstPair r=isBst2(node.right);
		
		BstPair mpair=new BstPair();
		mpair.isbst=l.isbst&&r.isbst&&node.data>=l.max&&node.data<=r.min;
		mpair.max=Math.max(node.data, Math.max(l.max, r.max));
		mpair.min=Math.min(node.data, Math.min(l.min, r.min));
		return mpair;
	}
	
	class Solution {
		  public boolean helper(Node node, Integer lower, Integer upper) {
		    if (node == null) return true;

		    int val = node.data;
		    if (lower != null && val <= lower) return false;
		    if (upper != null && val >= upper) return false;

		    if (! helper(node.right, val, upper)) return false;
		    if (! helper(node.left, lower, val)) return false;
		    return true;
		  }

		  public boolean isValidBST(Node root) {
		    return helper(root, null, null);
		  }
		}
	
	
	public class LargestBst{
		boolean isbst;
		int max;
		int min;
		Node lroot;
		int lsize;
	}
	
	public void largestbst() {
		LargestBst lp=largestbst(root);
		System.out.println(lp.lroot.data+"@"+lp.lsize);
	}
	
	private LargestBst largestbst(Node node) {
		if(node==null) {
			LargestBst bp=new LargestBst();
			bp.isbst=true;
			bp.max=Integer.MIN_VALUE;
			bp.min=Integer.MAX_VALUE;
			bp.lroot = null;
			bp.lsize = 0;
			return bp;
		}
		
		
		LargestBst l=largestbst(node.left);
		LargestBst r=largestbst(node.right);
		
		LargestBst mpair=new LargestBst();
		mpair.isbst=l.isbst&&r.isbst&&node.data>=l.max&&node.data<=r.min;
		mpair.max=Math.max(node.data, Math.max(l.max, r.max));
		mpair.min=Math.min(node.data, Math.min(l.min, r.min));
		
		if(mpair.isbst) {
			mpair.lroot=node;
			mpair.lsize=l.lsize+r.lsize+1;
		}else {
			if(l.lsize>r.lsize) {
				mpair.lroot=l.lroot;
				mpair.lsize=l.lsize;
			}else {
				mpair.lroot=r.lroot;
				mpair.lsize=r.lsize;
			}
		}
		return mpair;
		
		
	}
	
	
//	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
//		List<Integer> list = new ArrayList<Integer>();
//
//		traverse(root, list, target, k);
//		return list;
//	}
//
//	public int traverse(TreeNode root, List<Integer> list, TreeNode target, int k) {
//		if (root == null)
//			return -1;
//		if (root.val == target.val) {
//			printKDown(root, null, k, list);
//			return 0;
//		}
//		int left = traverse(root.left, list, target, k);
//
//		if (left != -1) {
//			printKDown(root, root.left, k - left - 1, list);
//			return left + 1;
//		}
//		int right = traverse(root.right, list, target, k);
//
//		if (right != -1) {
//			printKDown(root, root.right, k - right - 1, list);
//			return right + 1;
//		}
//
//		return -1;
//	}
//
//	public void printKDown(TreeNode node, TreeNode blocker, int k, List<Integer> list) {
//		if (node == null || k < 0)
//			return;
//		if (node == blocker)
//			return;
//		if (k == 0) {
//			list.add(node.val);
//			return;
//		}
//
//		printKDown(node.left, blocker, k - 1, list);
//		printKDown(node.right, blocker, k - 1, list);
//	}

}
