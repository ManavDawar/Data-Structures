package binarysearchTree;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= {10,12,20,25,30,37,40,50,60,62,70,75,80,87,90};
		BST bt= new BST(a);
		bt.display();
//		System.out.println(bt.max());
//		System.out.println(bt.min());
//		System.out.println(bt.find(70));
//		bt.printinrange(60, 80);
		bt.replacewithsumoflargerNodes();
		System.out.println("---------");
		bt.display();
	}

}
