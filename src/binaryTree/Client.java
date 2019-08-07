package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };
		int[] arr2= {0,1,3,-1,2,-1,-1,-1};
		BinaryTree bt2=new BinaryTree(arr2);
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
//		bt2.printkfar(1, 2);
		int[] pre = { 50, 25, 12, 37, 75, 62, 87 };
		int[] in = { 12, 25, 37, 50, 62, 75, 87 };
		int[] pos = { 12, 37, 25, 62, 87, 75, 50 };
//		bt.nodetoLeafPathSum(100);
		
//		BinaryTree bt3=new BinaryTree(pre, in);
//		bt3.display();
		
		BinaryTree bt4=new BinaryTree(pos, in, false);
//		bt4.display();
//		System.out.println(bt4.diameter());
//		System.out.println(bt4.diameter2());
//		System.out.println(bt4.diameter3());
//		bt.display();
		
//		System.out.println(bt.isBalanced());
//		System.out.println(bt.isbal2());
		
//		System.out.println(bt4.isBst());
//		System.out.println(bt4.isBst2());
//		System.out.println(Integer.MAX_VALUE);
//		bt4.largestbst();
		
		bt4.display();
		bt4.transform();
		System.out.println("-----------------------------------");
		bt4.display();

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
