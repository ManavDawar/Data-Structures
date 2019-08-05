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
			System.out.print(rem.data+" ");

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
		
		for(Node child:node.children) {
			convertToMirror(child);
		}
		Collections.reverse(node.children);		
		
	}

	public void removeLeaves() {
		removeLeaves(root);
	}
	
	private void removeLeaves(Node node) {
		
		for(int i=node.children.size()-1;i>=0;i--) {
			Node child=node.children.get(i);
			
			if(child.children.size()==0) {
				node.children.remove(child);
			}else {
				removeLeaves(child);
			}
		}
		
	}
	
}
