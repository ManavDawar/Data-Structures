package linkedList;

import java.awt.DisplayMode;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		LinkedList list1 =new LinkedList();
		list.addLast(1);
		list.addLast(20);
		list.addLast(100);

		list.addLast(15);
		list.addLast(5);
		list.addLast(45);
		list.addLast(60);
		list.display();
		list.oddEven();
		list.display();
		
	}

}
