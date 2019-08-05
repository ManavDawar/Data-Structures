package genericTree;

import java.util.ArrayList;
import java.util.Collections;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
				-1 };
		
		GenericTree gt=new GenericTree(arr);
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
		gt.removeLeaves();
		gt.display();
		lexcico(1,1000);
	}
	 public static void lexcico(int start, int end) {
	        // Write your code here.
	       ArrayList<String> al =new ArrayList<>();
	        
	        for(int i=start;i<end;i++) {
	            al.add(i+"");
	        }
	        Collections.sort(al);
	        
	        for(String a:al) {
	            System.out.println(a);;
	        }
	  }

}
