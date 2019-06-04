package linkedList;

import java.util.ArrayList;
import java.util.Scanner;

public class matrix {

	public static Scanner scn = new Scanner(System.in);

	static String[] codes = { ".", "abc", "def", "gh", "jkl", "mno", "pqrs", "tuv", "wx", "yz" };
	
	public static int keyPad(String str, String[] codes, String ans) {
		int count=0;
		
		if(str.length()==0) {
			
			return 1;
		}
		char ch =str.charAt(0);
		String ros = str.substring(1);

		int lcount=keyPad(ros, codes, ans);
		
		String code=codes[ch-'0'];
		
		for (int j=0;j<lcount;j++) {
			for (int i = 0; i < code.length(); i++) {
				count++;
//				mresult.add(code.charAt(i) + recs);
			}

		}

		
		return count;
	}

	// Driver program
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s = scn.nextLine();
//		System.out.println(keyPad(s, codes, ""));
		String str ="19";
		System.out.println(keypad1(str));
	}



	public static ArrayList<String> keypad1(String str) {

		if (str.length() == 0) {
			ArrayList<String> bresult = new ArrayList<>();
			bresult.add("");
			return bresult;
		}
		char ch = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> rresult = keypad1(ros);
		ArrayList<String> mresult = new ArrayList<>();

		String code = codes[ch - '0'];

		for (String recs : rresult) {

			for (int i = 0; i < code.length(); i++) {
				mresult.add(code.charAt(i) + recs);
			}

		}
		return mresult;
	}

//	public static void exitPoint(int[][] arr) {
//
//		// Write your code only here.
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				if (arr[i][j] == 1) {
//
//				}
//			}
//		}
//
//	}
}
