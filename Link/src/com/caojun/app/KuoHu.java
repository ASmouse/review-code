package com.caojun.app;

import java.util.EmptyStackException;
import java.util.Stack;

public class KuoHu {
	public static void main(String[] args) {
		 
		System.out.println(isMate("(){([])}"));
		
	}

	public static boolean isMate(String str) {
		Stack<Character> stack = new Stack<Character>();
		int count = 0;//��ֹ����"(][)"Ϊtrue������
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
						//��ƥ��
						break to;
					}
					break;
				case ']':
					s = stack.pop();
					if (s != '[') {
						//��ƥ��
						break to;
					}
					break;
				case '}':
					s = stack.pop();
					if (s != '{') {		
						//��ƥ��
						break to;
					}
					break;
				default:
					break;
				}

			}//end for
		
			if(stack.isEmpty() && count == (str.length()-1)){
				//strÿһ���ַ����������
				isFit = true;
			}else{
				//��߶� || δ�������������forѭ��
				isFit = false;
			}
		} catch (EmptyStackException e) {
			// �ұ߶�
			isFit = false;
		}

		return isFit;
	}

}
