package genericTree;

import java.util.ArrayList;
import java.util.Collections;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
				-1 };
		int[] arr2 = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
				-1 };
		GenericTree gt=new GenericTree(arr);
		GenericTree gt2=new GenericTree(arr2);
//		gt.display();
//		System.out.println(gt.size());
//		System.out.println(gt.max());
//		System.out.println(gt.find(11));
//		System.out.println(gt.height());
//		gt.levelorder();
//		gt.levelorderLinewise();
//		gt.lolLevel();
//		gt.lolZigZag();
//		gt.convertToMirror();
//		gt.display();
//		gt.removeLeaves();
//		gt.linearise();
//		gt.lineariseEff();
		
//		System.out.println(gt.IsIsomorphic(gt2));
//		System.out.println(gt.isSymmetric());
//		gt.Multisolver(110);
		
//		gt.Multisolver2(10);
//		gt.justlarger(0);
		System.out.println(gt.kthSmallest(3));
//		gt.display();

	}
	 

}
