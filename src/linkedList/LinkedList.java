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

		node.next = null;
		if (size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
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
		if (idx < 0 || idx > size) {
			System.out.println("Index Out of Bounds");
		} else if (idx == 0) {
			addFirst(data);
		} else if (idx == size) {
			addLast(data);
		} else {
			Node idxm1node = getNodeAt(idx - 1);
			Node idxnode = idxm1node.next;

			Node node = new Node();
			node.data = data;

			node.next = idxnode;
			idxm1node.next = node;

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
		if (size == 0) {
			return -1;
		} else if (size == 1) {
			int temp = head.data;

			head = tail = null;
			size--;
			return temp;
		} else {
			Node node = head;

			head = node.next;
			size--;
			return node.data;
		}
	}

	public Node removeFirstNode() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Node node = head;
			head = tail = null;
			this.size--;
			return node;
		} else {
			Node node = head;

			head = head.next;
			this.size--;

			return node;
		}
	}

	public int removeLast() {
		if (size == 0) {
			return -1;
		} else if (size == 1) {
			Node node = head;

			head = tail = null;
			this.size--;
			return node.data;
		} else {

			Node node = tail;
			Node second = getNodeAt(size - 2);

			second.next = null;
			tail = second;
			this.size--;
			return node.data;
		}
	}

	public Node removeNodeLast() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Node node = tail;
			head = tail = null;
			this.size--;
			return node;
		} else {
			Node node = tail;

			Node second = getNodeAt(size - 2);
			second.next = null;
			tail = second;
			this.size--;
			return node;
		}
	}

	public int removeAt(int idx) {
		if (idx < 0 || idx >= size) {
			System.out.println("Out of Bounds");
			return -1;
		} else if (idx == 0) {
			return removeFirst();
		} else if (idx == size - 1) {
			return removeLast();
		} else {
			Node nm1 = getNodeAt(idx - 1);
			Node n = nm1.next;
			Node np = n.next;
			nm1.next = np;
			this.size--;
			return n.data;
		}
	}

	public void displayReverse() {
		displayreverseHelper(head);
		System.out.println(" . ");
	}

	private void displayreverseHelper(Node node) {

		if (node == null) {
			return;
		}
		displayreverseHelper(node.next);
		System.out.print(node.data + " -> ");
	}

	public void reverseDataIteratively() {
		int left = 0;
		int right = size - 1;

		while (left < right) {

			Node leftNode = getNodeAt(left);
			Node rightNode = getNodeAt(right);

			int temp = leftNode.data;
			leftNode.data = rightNode.data;
			rightNode.data = temp;
			left++;
			right--;
		}
	}

	public void reversePointerIterative() {

		Node prev = head;
		Node curr = head.next;

		while (curr != null) {

			Node next = curr.next;

			curr.next = prev;

			prev = curr;
			curr = next;
		}

		Node temp=tail;
		tail=head;
		head=temp;
		tail.next=null;
		
	}

	public void reversePointerRecursively() {
		reversePointerRecursivelyHelper(head);
		Node temp=head;
		head=tail;
		tail=temp;
		tail.next=null;
	}

	private void reversePointerRecursivelyHelper(Node node) {
		if(node==tail) {
			return;
		}
		reversePointerRecursivelyHelper(node.next);
		node.next.next=node;
	}
	
	private class Heapmover{
		Node node;
	}

	public void reverseDataRecursively() {
		Heapmover left =new Heapmover();
		left.node=head;
		reverseDataRecursivelyHelper(left,head,0);
		
	}

	private void reverseDataRecursivelyHelper(Heapmover left, Node right, int floor) {
		if(right==null) {
			return;
		}
		
		reverseDataRecursivelyHelper(left,right.next,floor+1);
		if(floor>=size/2) {
			int temp=right.data;
			right.data=left.node.data;
			left.node.data=temp;
			
			left.node=left.node.next;
		}
	}
	
	private Node leftPalindrome;
	
	public boolean isPalindrome() {
		leftPalindrome=head;
		return isPalindromeHelper(head);
	}

	private boolean isPalindromeHelper(Node right) {
		if(right==null) {
			return true;
		}
		
		boolean ispalin=isPalindromeHelper(right.next);
		if(ispalin==false) {
			return false;
		}else {
			if(leftPalindrome.data==right.data) {
				leftPalindrome=leftPalindrome.next;
				return true;
			}else {
				return false;
			}
			
		}
	}

	public void fold() {
		Heapmover left = new Heapmover();
		left.node=head;
		fold(left,head,0);
	}

	private void fold(Heapmover left, Node right, int floor) {
		if(right==null) {
			return;
		}
		fold(left, right.next, floor+1);
		if(floor>size/2) {
			Node originalNode=left.node.next;
			left.node.next=right;
			right.next=originalNode;
			
			left.node=originalNode;
		}else if(floor==size/2) {
			tail=right;
			tail.next=null;
		}
	}


	public int kfromLast(int k) {
		Node slow=head;
		Node fast=head;
		
		for(int i=0;i<k;i++) {
			fast=fast.next;
		}
		
		while(fast!=null) {
			slow=slow.next;
			fast=fast.next;
		}
		
		return slow.data;
	}
	
	public int kfromLast2(int k) {
		Node left=head;
		int counter=0;
		
		for(Node node=head;node!=null;node=node.next) {
			counter++;
			if(counter>k) {
				left=left.next;
			}
		}
		return left.data;
	}

	public int mid() {
		Node slow=head;
		Node fast=head;
		
		while(fast.next!=null&&fast.next.next!=null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		
		return slow.data;
	}

	public Node midNode() {
		Node slow=head;
		Node fast=head;
		
		while(fast.next!=null&&fast.next.next!=null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		
		return slow;
	}
	
	public static LinkedList mergeTwoSorted(LinkedList one,LinkedList two) {
		LinkedList res= new LinkedList();
		Node onode=one.head;
		Node tnode=two.head;
		
		while(onode!=null&&tnode!=null) {
			if(onode.data<tnode.data) {
				res.addLast(onode.data);
				onode=onode.next;
			}else {
				res.addLast(tnode.data);
				tnode=tnode.next;
			}
		}
		
		while(onode!=null) {
				res.addLast(onode.data);
				onode=onode.next;
		}		

		while(tnode!=null) {
				res.addLast(tnode.data);
				tnode=tnode.next;
		}		
		
		return res;
	}
	
	public static LinkedList mergeSort(LinkedList list) {
		if(list.size==1) {
			return list;
		}
		LinkedList res = new LinkedList();
		Node mid= list.midNode();
		
		LinkedList l1= new LinkedList();
		l1.head=list.head;
		l1.tail=mid;
		l1.size=(list.size+1)/2;
		
		LinkedList l2= new LinkedList();
		l2.head=mid.next;
		l2.tail=list.tail;
		l2.size=list.size/2;
		
		mid.next=null;
		
		l1=LinkedList.mergeSort(l1);
		l2=LinkedList.mergeSort(l2);
		
		res=LinkedList.mergeTwoSorted(l1,l2);
		return res;
	}
	
//	Complexity is o(N) as while loop runs n / k times and for loop runs k times 
	public void kReverse(int k) {
		LinkedList nl = new LinkedList();
		LinkedList temp = new LinkedList();
		
	
		while(!this.isEmpty()) {
			
			for(int i=0;i<k;i++) {
				int data =this.removeFirst();
				temp.addFirst(data);
			}
			
			if(nl.size==0) {
				nl=temp;
				temp=new LinkedList();
			}else {
				nl.tail.next=temp.head;
				nl.tail=temp.tail;
				nl.size+=temp.size;
				
				temp=new LinkedList();
			}
			
		}
		this.head=nl.head;
		this.tail=nl.tail;
		this.size=nl.size;
	
	}
	
	public void oddEven() {
		LinkedList ol=new LinkedList();
		LinkedList el=new LinkedList();
		
		while(this.size!=0) {
			int rem = this.removeFirst();
			
			if(rem%2==1) {
				ol.addLast(rem);
			}else {
				el.addLast(rem);
			}
		}
		
		if(ol.size==0) {
			this.head=el.head;
			this.tail=el.tail;
			this.size=el.size;
		}else if(el.size==0) {
			this.head=ol.head;
			this.tail=ol.tail;
			this.size=ol.size;
		}else {
			this.head=ol.head;
			this.tail=el.tail;
			this.size=ol.size+el.size;
			
			ol.tail.next=el.head;
		}
	}
}
