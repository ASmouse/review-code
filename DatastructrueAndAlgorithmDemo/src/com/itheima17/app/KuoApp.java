package com.itheima17.app;

import java.util.Stack;

public class KuoApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPiPei("(][)"));
	}

	/**
	 * @param exp
	 *            括弧的表达式
	 * @return 括弧是否匹配
	 */
	public static boolean isPiPei(String exp) {
		// 变历所有字符，如果是左括弧，放到栈中，如果是右括弧，取栈的数据，判断是否匹配，
		// 如果是失败程序终止（不匹配）,如果匹配继续找下一个字符，一直到最后一个字符
		// a 判断容器是否为空 b 判断取出的数据是否为空
		boolean isPiPei = false;
		char left = 0;
		MyDoubleLink<Character> kuohu = new MyDoubleLink<Character>();
		try {
			to: for (int i = 0; i < exp.length(); i++) {
				char c = exp.charAt(i);// 取字符
				switch (c) {
				case '(':
				case '[':
				case '{':
					// 把括弧放到栈中
					kuohu.push(c);
					break;
				case ')':
					left = kuohu.pop();// 取出 移除
					if (left != '(') {
						// 跳出循环  不匹配
						break to;
					}
					break;
				case ']':
					left = kuohu.pop();// 取出 移除
					if (left != '[') {
						// 跳出循环  不匹配
						break to;
					}
					break;
				case '}':
					left = kuohu.pop();// 取出 移除
					if (left != '{') {
						// 跳出循环  不匹配
						break to;
					}
					break;
				default:
					break;
				}//end switch (c) {
			}// end for (int i = 0; i < exp.length(); i++) {
		    //for循环结束
		    if (kuohu.isEmpty()) {
		    	//空
		    	isPiPei = true;
		    } else {
		    	//多了左括弧
		    	isPiPei = false;//可以不写
		    }
		} catch (Exception e) {
			//取不到数据，说明多了右括弧
			//e.printStackTrace();
			isPiPei = false;//可以不写
		}

		return isPiPei;
	}

}
