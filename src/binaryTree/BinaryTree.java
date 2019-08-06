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

				if (curr.left == prev) {
					printkdown(curr.right, k - i - 1);
				} else {
					printkdown(curr.left, k - i - 1);
				}
			}

		}

	}

}
