package stacks;

import java.util.HashMap;
import java.util.Stack;

public class Stackuse {
	
	public static String infixToPostfix(String exp) {
		
		String res="";
		Stack<Character> stack = new Stack<>();
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('^', 3);
		
		for(int i=0;i<exp.length();i++) {
			char ch = exp.charAt(i);
			
			if(ch>='a'&&ch<='z') {
				res+=ch;
			}else if(ch=='(') {
				stack.push(ch);
			}else if(ch==')') {

				while(stack.peek()!='(') {
					char rem = stack.pop();
					res+=rem;
				}
				stack.pop();
			}else {
				
				while(!(stack.size()==0||stack.peek()=='('||map.get(ch)>=map.get(stack.peek()))) {
					char rem = stack.pop();
					res+=rem;
				}
				stack.push(ch);
				
			}
			
		}
		
		while(stack.size()>0) {
			char rem = stack.pop();
			res+=rem;
		}
		
		return res;
	}

//	Ques : Evaluate a postfix Expression and return its result in Integer Value
	public static int PostEvaluate(String exp) {

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);

			if (ch >= '0' && ch <= '9') {
				stack.push(ch - '0');
			} else {

				int val1 = stack.pop();
				int val2 = stack.pop();

				if (ch == '+') {
					stack.push(val2 + val1);
				} else if (ch == '*') {
					stack.push(val2 * val1);
				} else if (ch == '-') {
					stack.push(val2 - val1);
				} else if (ch == '/') {
					stack.push(val2 / val1);
				} else if(ch =='^') {
					stack.push((int)Math.pow(val2, val1));
				} 
			}
		}
		return stack.peek();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		System.out.println(PostEvaluate("82-3/2131+*^+"));
		System.out.println(infixToPostfix("(a-b)/c+d^(e*(f+g))"));		
	}

}
