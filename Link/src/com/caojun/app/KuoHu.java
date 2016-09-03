package com.caojun.app;

import java.util.EmptyStackException;
import java.util.Stack;

public class KuoHu {
	public static void main(String[] args) {
		 
		System.out.println(isMate("(){([])}"));
		
	}

	public static boolean isMate(String str) {
		Stack<Character> stack = new Stack<Character>();
		int count = 0;//防止出现"(][)"为true的现象
		boolean isFit = false;
		char c = '0';
		char s;
		try {
			to: for (int i = 0; i < str.length(); i++) {
				count = i;
				c = str.charAt(i);
				switch (c) {
				case '(':
				case '[':
				case '{':
					stack.push(c);
					break;
				case ')':
					s = stack.pop();
					if (s != '(') {
						//不匹配
						break to;
					}
					break;
				case ']':
					s = stack.pop();
					if (s != '[') {
						//不匹配
						break to;
					}
					break;
				case '}':
					s = stack.pop();
					if (s != '{') {		
						//不匹配
						break to;
					}
					break;
				default:
					break;
				}

			}//end for
		
			if(stack.isEmpty() && count == (str.length()-1)){
				//str每一个字符都遍历完毕
				isFit = true;
			}else{
				//左边多 || 未遍历完就已跳出for循环
				isFit = false;
			}
		} catch (EmptyStackException e) {
			// 右边多
			isFit = false;
		}

		return isFit;
	}

}
