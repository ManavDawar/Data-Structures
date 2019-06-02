package linkedList;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		
		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		list.addLast(40);
		list.addFirst(5);
		list.display();
		
		System.out.println(list.removeAt(2));
		
		list.display();

	}

}
