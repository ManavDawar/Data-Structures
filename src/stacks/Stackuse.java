package stacks;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println(PostEvaluate("82-3/2131+*^+"));
//		System.out.println(infixToPostfix("(a-b)/c+d^(e*(f+g))"));
		
		
		System.out.println(preall("+/-823^3*1+24"));
		
	}

}
