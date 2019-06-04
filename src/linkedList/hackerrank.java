package linkedList;

import java.util.Scanner;

public class hackerrank {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();

		while (t-- > 0) {
			int n = scan.nextInt();
			int[] arr = new int[n];
			for (int i = 0; i < n; ++i) {
				arr[i] = scan.nextInt();	
			}
		}

		scan.close();
	}
	
	
	

}
