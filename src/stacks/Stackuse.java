package stacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Stackuse {
// Question 1: Convert Infix to Post-Fix expression
	public static String infixToPostfix(String exp) {

		String res = "";
		Stack<Character> stack = new Stack<>();
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('^', 3);

		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				res += ch;
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {

				while (stack.peek() != '(') {
					char rem = stack.pop();
					res += rem;
				}
				stack.pop();
			} else {

				while (!(stack.size() == 0 || stack.peek() == '(' || map.get(ch) >= map.get(stack.peek()))) {
					char rem = stack.pop();
					res += rem;
				}
				stack.push(ch);
			}
		}
		while (stack.size() > 0) {
			char rem = stack.pop();
			res += rem;
		}

		return res;
	}
	
	
	public static int infixEvaluate(String exp) {

		Stack<Integer> value = new Stack<>();
		Stack<Character> operator = new Stack<>();
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('^', 3);

		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (ch >= '0' && ch <= '9') {
				value.push(ch-'0');
			} else if (ch == '(') {
				operator.push(ch);
			} else if (ch == ')') {

				while (operator.peek() != '(') {
					char op = operator.pop();
					int val1=value.pop();
					int val2=value.pop();
					value.push(eval(val2, val1, op));
				}
				operator.pop();
			} else {

				while (operator.size() != 0 && operator.peek() != '(' && map.get(operator.peek()) >= map.get(ch)) {
					char op = operator.pop();
					int val1=value.pop();
					int val2=value.pop();
					value.push(eval(val2, val1, op));
				}
				operator.push(ch);
//				we pop everything till we acheive lower precedence that this and then we push the operator
			}
		}
		while (operator.size() > 0) {
			char op = operator.pop();
			int val1=value.pop();
			int val2=value.pop();
			value.push(eval(val2, val1, op));
		}

		return value.pop();
	}

//	Question 2 : Evaluate a postfix Expression and return its result in Integer Value and convert it into infix and prefix	
	public static int PostEvaluate(String exp) {
//		for postfix evaluation
		Stack<Integer> stack = new Stack<>();
//		to convert postfix to infix
		Stack<String> convertinfix = new Stack<>();
//		to convert postfix to prefix
		Stack<String> convertToprefix= new Stack<>();
		
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);

			if (ch >= '0' && ch <= '9') {
				stack.push(ch - '0');
				convertinfix.push(ch+"");
				convertToprefix.push(ch+"");
			} else {

				int val1 = stack.pop();
				int val2 = stack.pop();
				String ival1=convertinfix.pop();
				String ival2=convertinfix.pop();
				String pval1=convertToprefix.pop();
				String pval2=convertToprefix.pop();
				
				stack.push(eval(val2, val1, ch));
				convertinfix.push("("+ival2+ch+ival1+")");
				convertToprefix.push(ch+pval2+pval1);
				
			}
		}
		System.out.println(convertinfix.pop() );
		System.out.println(convertToprefix.pop());
		
		return stack.peek();
	}
	
	static int eval(int val1,int val2,char op) {
		
		switch (op) {
		case '*':
			return val1*val2;
		case '+':
			return val1+val2;
		case '-':
			return val1-val2;
		case '/':
			return val1/val2;
		case '^':
			return (int)Math.pow(val1, val2);
				
		default:
			return -1;
		}
	}
	
//	Question:Prefix all functions i.e evaluate convert to infix and postfix

	public static int preall(String exp) {
		Stack<Integer> stack = new Stack<>();
		Stack<String> infix = new Stack<>();
		Stack<String> postfix= new Stack<>();
		
		for (int i = exp.length()-1; i >=0 ; i--) {
			char ch = exp.charAt(i);

			if (ch >= '0' && ch <= '9') {
				stack.push(ch - '0');
				infix.push(ch+"");
				postfix.push(ch+"");
			} else {
				int val1 = stack.pop();
				int val2 = stack.pop();
				String ival1=infix.pop();
				String ival2=infix.pop();
				String pval1=postfix.pop();
				String pval2=postfix.pop();
				
				stack.push(eval(val1, val2, ch));
//				first value poped should be operated with second value
				infix.push("("+ival1+ch+ival2+")");
				postfix.push(pval1+pval2+ch);
				
			}
		}
		System.out.println(infix.pop() );
		System.out.println(postfix.pop());
		return stack.peek();
	}	
	
	
	static ArrayList<Integer> slidingwindowmax(int[] arr,int k) {

		Integer[] nge=new Integer[arr.length];
		ArrayList<Integer> swmax = new ArrayList<>();
		
		for(int i=0;i<arr.length;i++) {
			Arrays.fill(nge, arr.length);
		}
		Stack<Integer> st=new Stack<>();
		st.push(arr.length-1);
		
		for(int i = arr.length-2;i>=0;i--) {
			
			while (st.size() > 0 && arr[i] > arr[st.peek()]) {
				st.pop();
			}
			nge[i] = st.size() == 0 ? arr.length : st.peek();
			st.push(i);
		}
		
		int j=0;
		for(int i=0;i<=arr.length-k;i++) {
			
			if(j<i) {
				j=i;
			}
			while(nge[j]<i+k) {
				
				j=nge[j];
			}
			swmax.add(arr[j]);
		}
		
		return swmax;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println(PostEvaluate("82-3/2131+*^+"));
//		System.out.println(infixEvaluate("8-2/3+2^1*(1+3)"));
		System.out.println(infixToPostfix("(a-b)/c+d^(e*(f+g))"));		
//		System.out.println(preall("+/-823^3*1+24"));
		
		
		int[] arr={3,5,1,2,4,2,7,4,2,1,3,5,5,8,1,3,2};
		System.out.println(slidingwindowmax(arr, 5));
	}

}
