package binarysearchTree;

public class BST {

	private class Node {
		int data;
		Node left;
		Node right;
	}

	Node root;
	int size;

	public BST(int[] arr) {
		root = construct(arr, 0, arr.length - 1);
	}

	private Node construct(int[] arr, int lo, int hi) {
		if (lo > hi) {
			return null;
		}
		int mid = (lo + hi) / 2;
		Node node = new Node();
		node.data = arr[mid];
		size++;

		node.left = construct(arr, lo, mid - 1);
		node.right = construct(arr, mid + 1, hi);
		return node;
	}

	public void display() {
		display(root);
	}

	private void display(Node node) {
		if (node == null) {
			return;
		}
		String s = "";
		s += node.left != null ? node.left.data + "" : ".";
		s += "<-" + node.data + "->";
		s += node.right != null ? node.right.data + "" : ".";
		System.out.println(s);

		display(node.left);
		display(node.right);
	}

	public int max() {
		return max(root);
	}

	private int max(Node node) {
		if (node.right == null) {
			return node.data;
		}
		return max(node.right);

	}

	public Node max1() {
		return max1(root);
	}

	private Node max1(Node node) {
		if (node.right == null) {
			return node;
		}

		return max1(node.right);
	}

	public int min() {
		return min(root);
	}

	private int min(Node node) {
		if (node.left == null) {
			return node.data;
		}
		return min(node.left);
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

		if (node.data > data) {
			boolean left = find(node.left, data);
			if (left) {
				return true;
			}
		} else {
			boolean right = find(node.right, data);
			if (right) {
				return true;
			}
		}

		return false;
	}

	public void printinrange(int lo, int hi) {
		printinrange(root, lo, hi);
	}
	
	private void printinrange(Node node,int lo,int hi) {
		if(node==null) {
			return;
		}
		if(node.data>=lo&&node.data<=hi) {
			printinrange(node.left,lo,hi);
			System.out.println(node.data);
			printinrange(node.right, lo, hi);
		}else if(node.data>hi) {
			printinrange(node.left, lo, hi);
		}else {
			printinrange(node.right, lo, hi);
		}
		
	}
	private int sum;
	public void replacewithsumoflargerNodes() {
		sum=0;
		replacewithsumoflargerNodes(root);
	}
	
	private void replacewithsumoflargerNodes(Node node) {
		if(node==null) {
			return;
		}
		
		replacewithsumoflargerNodes(node.right);
		sum+=node.data;
		node.data=sum;
		replacewithsumoflargerNodes(node.left);
		
	}
	
	
	

}
