package genericTree;

import java.util.*;


public class GenericTree {
	private class Node {
		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	Node root;
	int size;

	public GenericTree(int[] arr) {
		Stack<Node> st = new Stack<>();
		for (int i : arr) {
			if (i == -1) {
				st.pop();
			} else {
				Node node = new Node();
				this.size++;
				node.data = i;
				if (st.size() > 0) {
					st.peek().children.add(node);
				} else {
					root = node;
				}
				st.push(node);
			}
		}
	}

	public void display() {
		display(root);
	}

	private void display(Node node) {
		String str = node.data + "->";

		for (Node ch : node.children) {
			str += ch.data + ",";
		}

		str += ".";
		System.out.println(str);
		for (Node child : node.children) {
			display(child);
		}

	}

	public int size() {
//		return this.size; you can do both
		return size(root);
	}

	private int size(Node node) {
		int tsize = 0;

		for (Node child : node.children) {
			int size = size(child);
			tsize += size;
		}

		return tsize + 1;

	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int max() {
		return max(root);
	}

	private int max(Node node) {
		int max = Integer.MIN_VALUE;

		for (Node child : node.children) {
			int cmax = max(child);
			max = Math.max(cmax, max);
		}
		return Math.max(max, node.data);
	}

	public boolean find(int data) {
		return find(root, data);
	}

	public ArrayList<Integer> nodeToRootPath(int data) {
		return nodeToRootPath(root, data);
	}

	private ArrayList<Integer> nodeToRootPath(Node node, int data) {

		if (node.data == data) {
			ArrayList<Integer> rresult = new ArrayList<>();
			rresult.add(node.data);
			return rresult;

		}
		for (Node child : node.children) {
			ArrayList<Integer> result = nodeToRootPath(child, data);
			if (result.size() != 0) {
				result.add(node.data);
				return result;
			}
		}

		return new ArrayList<>();

	}
	
	private boolean find(Node node, int data) {
		if (node.data == data) {
			return true;
		}

		for (Node child : node.children) {
			boolean found = find(child, data);
			if (found == true) {
				return true;
			}
		}
		return false;
	}

	public int height() {
		return height(root);
	}

	private int height(Node node) {

		int height = -1;

		for (Node child : node.children) {
			int cheight = height(child);
			height = Math.max(cheight, height);
		}

		return height + 1;

	}

	public void levelorder() {
		levelorder(root);
	}

	private void levelorder(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		while (q.size() > 0) {
			Node rem = q.remove();

			System.out.print(rem.data + " ");

			for (Node child : rem.children) {
				q.add(child);
			}

		}

	}

	// Null Approcah
	public void levelorderLinewise() {
		levelorderlinewise(root);
	}

	private void levelorderlinewise(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		q.add(null);
		while (q.size() > 0) {
			Node rem = q.remove();
			if (rem == null) {
				System.out.println();
				if (q.size() > 0) {
					q.add(null);
				}
			} else {
				System.out.print(rem.data + " ");
				for (Node child : rem.children) {
					q.add(child);
				}
			}
		}
	}

	// Level Approach
	public class Pair {
		Node node;
		int level;

		public Pair(Node node, int level) {
			this.node = node;
			this.level = level;
		}
	}

	public void lolLevel() {
		lolLevel(root);
	}

	private void lolLevel(Node node) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(node, 0));
		int level = 0;
		while (q.size() > 0) {
			Pair rem = q.remove();
			if (level != rem.level) {
				level = rem.level;
				System.out.println();
			}
			System.out.print(rem.node.data + " ");

			for (Node child : rem.node.children) {
				q.add(new Pair(child, rem.level + 1));
			}

		}

	}

	public void lolZigZag() {
		lolZigZag(root);
	}

	private void lolZigZag(Node node) {

		Queue<Node> q = new LinkedList<>();
		LinkedList<Node> s = new LinkedList<>();
		q.add(node);
		boolean ltr = true;
		while (q.size() > 0) {
			Node rem = q.remove();
			System.out.print(rem.data + " ");

			if (ltr) {
				for (int i = 0; i < rem.children.size(); i++) {
					Node child = rem.children.get(i);
					s.push(child);
				}
			} else {
				for (int i = rem.children.size() - 1; i >= 0; i--) {
					Node child = rem.children.get(i);
					s.push(child);
				}
			}
			if (q.size() == 0) {
				System.out.println();
				ltr = !ltr;
				q = s;
				s = new LinkedList<>();
			}
		}
	}

	public void linewiseZigZag() {

		LinkedList<Node> stack1 = new LinkedList<>();
		LinkedList<Node> stack2 = new LinkedList<>();

		stack1.push(root);
		Boolean flag = false;
		while (stack1.size() > 0) {
			Node temp = stack1.pop();
			System.out.print(temp.data + " ");
			if (flag == false) {
				for (Node child : temp.children) {
					stack2.push(child);
				}
			} else {
				for (int i = temp.children.size() - 1; i >= 0; i--) {
					stack2.push(temp.children.get(i));
				}
			}

			if (stack1.size() == 0) {
				System.out.println();
				stack1 = stack2;
				flag = !flag;
				stack2 = new LinkedList<>();
			}
		}

	}

	// by queue and stack............................................

	public void linewiseZigZag2() {

		LinkedList<Node> clq = new LinkedList<>();
		LinkedList<Node> nls = new LinkedList<>();

		clq.addLast(root);
		int level = 1;
		while (clq.size() > 0) {
			Node temp = clq.removeFirst();
			System.out.print(temp.data + " ");
			if (level % 2 == 1) {
				for (int i = 0; i < temp.children.size(); i++) {
					nls.addFirst(temp.children.get(i));
					;
				}
			} else {
				for (int i = temp.children.size() - 1; i >= 0; i--) {
					nls.addFirst(temp.children.get(i));
				}
			}

			if (clq.size() == 0) {
				System.out.println();
				clq = nls;

				level++;
				nls = new LinkedList<>();
			}
		}

	}

	public void convertToMirror() {
		convertToMirror(root);
	}

	private void convertToMirror(Node node) {

		for (Node child : node.children) {
			convertToMirror(child);
		}
		Collections.reverse(node.children);
		// or we can do this
		// int left = 0;
		// int right = node.children.size() - 1;
		//
		// while (left < right) {
		//
		// Node leftnode = node.children.get(left);
		//
		// Node rightnode = node.children.get(right);
		//
		// node.children.set(left, rightnode);
		// node.children.set(right, leftnode);
		//
		// left++;
		// right--;
		//
		// }

	}

	public void removeLeaves() {
		removeLeaves(root);
	}

	private void removeLeaves(Node node) {

		for (int i = node.children.size() - 1; i >= 0; i--) {
			Node child = node.children.get(i);

			if (child.children.size() == 0) {
				node.children.remove(child);
			} else {
				removeLeaves(child);
			}
		}

	}

	public void linearise() {
		linearise(root);
	}

	private void linearise(Node node) {

		for (Node child : node.children) {
			linearise(child);
		}

		while (node.children.size() > 1) {
			Node sl = node.children.get(node.children.size() - 2);// second last
			Node l = node.children.remove(node.children.size() - 1);// last element

			Node sltail = gettail(sl);// second last ki tail
			sltail.children.add(l);
		}
	}

	private Node gettail(Node node) {
		Node tail = node;
		while (tail.children.size() == 1) {
			tail = tail.children.get(0);
		}

		return tail;
	}

	public void lineariseEff() {
		lineariseEff(root);
	}

	private Node lineariseEff(Node node) {
		if (node.children.size() == 0) {
			return node;
		}

		Node oltail = lineariseEff(node.children.get(node.children.size() - 1));

		while (node.children.size() > 1) {
			Node sl = node.children.get(node.children.size() - 2);
			Node l = node.children.get(node.children.size() - 1);

			Node slkitail = lineariseEff(sl);
			node.children.remove(l);
			slkitail.children.add(l);
		}
		return oltail;
	}

	public boolean IsIsomorphic(GenericTree other) {
		return IsIsomorphic(this.root, other.root);
	}

	private boolean IsIsomorphic(Node tnode, Node onode) {

		if (tnode.children.size() == onode.children.size()) {
			for (int i = 0; i < tnode.children.size(); i++) {
				Node tchild = tnode.children.get(i);
				Node ochild = onode.children.get(i);

				boolean isiso = IsIsomorphic(tchild, ochild);
				if (!isiso) {
					return false;
				}
			}

			return true;
		} else {
			return false;
		}

	}

	public boolean isMirror(GenericTree other) {
		return isMirror(this.root, other.root);
	}

	private boolean isMirror(Node tnode, Node onode) {
		if (tnode.children.size() != onode.children.size()) {
			return false;
		}
		int left = 0;
		int right = tnode.children.size() - 1;
		while (left < right) {
			Node tchild = tnode.children.get(left);
			Node ochild = onode.children.get(right);
			boolean ismirror = isMirror(tchild, ochild);
			if (!ismirror) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public boolean isSymmetric() {
		return isMirror(this.root, this.root);
	}

	public class HeapMover {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int size = 0;
		int height = 0;
		boolean find = false;
	}

	public void Multisolver(int data) {
		HeapMover hm = new HeapMover();
		Multisolver(root, hm, data, 0);
		System.out.println("Size = " + hm.size);
		System.out.println("Min = " + hm.min);
		System.out.println("Max = " + hm.max);
		System.out.println("Height = " + hm.height);
		System.out.println("Found = " + hm.find);
	}

	private void Multisolver(Node node, HeapMover hm, int data, int depth) {

		hm.min = Math.min(hm.min, node.data);
		hm.max = Math.max(hm.max, node.data);
		hm.size++;
		hm.height = Math.max(depth, hm.height);
		hm.find = hm.find || node.data == data;

		for (Node child : node.children) {
			Multisolver(child, hm, data, depth + 1);
		}

	}

	public class HealpMover2 {
		Node prev = null;
		Node curr = null;
		Node predecessor = null;
		Node succesor = null;
	}

	public void Multisolver2(int data) {
		HealpMover2 hm = new HealpMover2();
		Multisolver2(root, hm, data);

		System.out.println("Pre = " + (hm.predecessor == null ? "null" : hm.predecessor.data));
		System.out.println("Suc = " + (hm.succesor == null ? "null" : hm.succesor.data));

	}

	private void Multisolver2(Node node, HealpMover2 hm, int data) {
		hm.curr = node;

		if (hm.curr.data == data) {
			hm.predecessor = hm.prev;
		}

		if (hm.prev != null && hm.prev.data == data) {
			hm.succesor = hm.curr;
		}

		hm.prev = hm.curr;
		for (Node child : node.children) {
			Multisolver2(child, hm, data);
		}

	}

	private static Node ceil=null;
	
	public void justlarger(int data) {
		ceil=null;
		justlarger(root,data);
		System.out.println("Just Larger " +(ceil==null?"null":ceil.data));
	}

	private void justlarger(Node node, int data) {
		
		if(node.data>data) {
			if(ceil==null||ceil.data>node.data) {
				ceil=node;
			}
		}
		
		for(Node child:node.children) {
			justlarger(child, data);
		}
	}
	
	
	public int kthSmallest(int k) {
		
		int data=Integer.MIN_VALUE;
		for(int i=0;i<k;i++) {
			justlarger(root, data);
			data=ceil.data;
			ceil=null;
		}
		
		return data;
	}
}
