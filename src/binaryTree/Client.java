package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };

		BinaryTree bt = new BinaryTree(arr);
//		bt.display();

//		System.out.println(bt.size());
//		System.out.println(bt.max());
//		System.out.println(bt.min());
//		System.out.println(bt.height());
//		System.out.println(bt.find(70));
//		System.out.println(bt.find(66));
//		bt.levelorder();
//		printBinary(20);
//		bt.preoiterative();
//		System.out.println();
//		bt.postoiterative();
//		System.out.println();
//		bt.inoiterative();
//		bt.singlechild();
//		bt.removeleaves();

//		System.out.println(bt.nodetorootpath(60));
		bt.printkfar(3, 75);
//		bt.display();

	}

	private static class pair {
		int data;
		String bin;

		public pair(int data, String bin) {
			this.data = data;
			this.bin = bin;
		}
	}

	public static void printBinary(int n) {
		Queue<pair> q = new LinkedList<>();
		q.add(new pair(1, "1"));

		while (q.size() > 0) {
			pair rem = q.remove();
			if (rem.data == n) {
				break;
			}
			System.out.println(rem.data + "->" + rem.bin);

			q.add(new pair(2 * rem.data, rem.bin + "0"));
			q.add(new pair(2 * rem.data + 1, rem.bin + "1"));

		}
	}

}
