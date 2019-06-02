package linkedList;

public class LinkedList {

	private class Node {
		int data;
		Node next;
	}

	private Node head;
	private Node tail;
	private int size;

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void addLast(int data) {
		// create
		Node node = new Node();

		// set property
		node.data = data;
		node.next = null;

		if (size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}

		this.size++;
	}
	
	public void addNodeLast(Node node) {
		if(size==0) {
			head=tail=node;
		}else {
			tail.next=node;
			node.next=null;
			tail=node;
		}
		this.size++;
	}

	public void addFirst(int data) {
		Node node = new Node();

		node.data = data;

		if (size == 0) {
			head = tail = node;
		} else {
			node.next = head;
			head = node;
		}
		this.size++;
	}

	public void addFirstNode(Node node) {
		if (size == 0) {
			head = tail = node;
		} else {
			node.next = head;
			node = head;
		}
		size++;
	}

	public void addAt(int idx, int data) {
		if(idx<0||idx>size) {
			System.out.println("Index Out of Bounds");
		}else if(idx==0) {
			addFirst(data);
		}else if(idx==size) {
			addLast(data);
		}else {
			Node idxm1node=getNodeAt(idx-1);
			Node idxnode=idxm1node.next;
			
			Node node=new Node();
			node.data=data;
			
			node.next=idxnode;
			idxm1node.next=node;
			
			this.size++;
		}
	}

	public void display() {

		for (Node node = head; node != null; node = node.next) {
			System.out.print(node.data + " -> ");
		}
		System.out.println(".");
	}

	public void display1() {
		Node temp = this.head;
		while (temp != null) {
			System.out.print(temp.data + " -> ");
			temp = temp.next;
		}
		System.out.println(".");
	}

	public int getFirst() {
		if (size == 0) {
			return -1;
		} else {
			return head.data;
		}
	}

	public int getLast() {
		if (size == 0) {
			return -1;
		} else {
			return tail.data;
		}
	}

	public int getAt(int idx) {
		if (size == 0) {
			return -1;
		} else if (idx < 0 || idx >= size) {
			System.out.println("Out of Bounds");
			return -1;
		}

		Node node = new Node();
		node = head;

		for (int i = 0; i < idx; i++) {
			node = node.next;
		}
		return node.data;

	}

	public Node getNodeAt(int idx) {

		if (size == 0) {
			return null;
		} else if (idx < 0 || idx >= size) {
			System.out.println("Out of Bounds");
			return null;
		}

		Node node = new Node();
		node = head;

		for (int i = 0; i < idx; i++) {
			node = node.next;
		}
		return node;
	}

	public int removeFirst() {
		if(size==0) {
			return -1;
		}else if(size==1) {
			int temp=head.data;
			
			head=tail=null;
			size--;
			return temp;
		}else {
			Node node =head;
			
			head=node.next;
			size--;
			return node.data;
		}
	}
	
	public Node removeFirstNode() {
		if(size==0) {
			return null;
		}else if(size==1) {
			Node node=head;
			head=tail=null;
			this.size--;
			return node;
		}else {
			Node node= head;
			
			head=head.next;
			this.size--;
			
			return node;
		}
	}
	
	public int removeLast() {
		if(size==0) {
			return -1;
		}else if(size==1){
			Node node=head;
			
			head=tail=null;
			this.size--;
			return node.data;
		}else {
		
			Node node=tail;
			Node second=getNodeAt(size-2);
			
			second.next=null;
			tail=second;
			this.size--;
			return node.data;
		}
	}
	
	public Node removeNodeLast() {
		if(size==0) {
			return null;
		}else if(size==1) {
			Node node=tail;
			head=tail=null;
			this.size--;
			return node;
		}else {
			Node node =tail;
			
			Node second =getNodeAt(size-2);
			second.next=null;
			tail=second;
			this.size--;
			return node;
		}
	}
	
	public int removeAt(int idx) {
		if(idx<0||idx>=size) {
			System.out.println("Out of Bounds");
			return -1;
		}else if(idx==0) {
			return removeFirst();
		}else if(idx==size-1) {
			return removeLast();
		}else {
			Node nm1=getNodeAt(idx-1);
			System.out.println(nm1.data);
			Node n =nm1.next;
			System.out.println(n.data);
			Node np=n.next;
			System.out.println(np.data);
			
			nm1.next=np;
			this.size--;
			return n.data;
		}
	}
	
	
	
}
