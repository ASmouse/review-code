package com.itheima17.app;

import java.util.ArrayList;
import java.util.List;

public class CalcuAlgo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String exp = "-3-3.5*4/2*-3";
		//-3 - 7 *-3  21 -3 18
		System.out.println(calc(exp));
		
	}
	
	public static double calc(String exp){
		//1. 从表达式中取所有的数字
		List<Double> numbers = getNumbers(exp);
		//2. 从表达式中取所有的运算符
		List<Character> ops = getOps(exp);
		//3. 先遍历变量运算符容器中的x和/
		for (int i = 0; i < ops.size(); i++){
			if (ops.get(i) == '*' || ops.get(i) == '/'){
				//移 除符号
//				从数字容器取两个数字运算
				char op = ops.remove(i);
				double d1 = numbers.remove(i);
				double d2 = numbers.remove(i);
				if (op == '*') {
					d1 = d1 * d2;
				} else {
					d1 = d1 / d2;
				}
				//把结果放数字容器中的i的位置 
				numbers.add(i, d1);
				i--;
			}
		}
		
		//+-运算
		while(!ops.isEmpty()) {
			char op = ops.remove(0);
			double d1 = numbers.remove(0);
			double d2 = numbers.remove(0);
			if (op == '+') {
				d1 = d1 + d2;
			} else {
				d1 = d1 - d2;
			}
			numbers.add(0, d1);
		}
		return numbers.get(0);
	}
	
	private static String getExp(String exp){
		//把负数的-符号 转成@
		for (int i  = 0; i < exp.length(); i++) {
			if (i == 0 && exp.charAt(i) == '-'){
				exp = '@' + exp.substring(1);
			} else {
				if (exp.charAt(i) == '-') {
					//前有字符
					if (exp.charAt(i - 1) == '/' ||exp.charAt(i - 1) == '*'  ) {
						exp = exp.substring(0,i) + "@" + exp.substring(i + 1);
					}
				}
			}
		}
		return exp;
	}
     // 3+3.4*3/2.5*-3
	private static List<Character> getOps(String exp) {
		// TODO Auto-generated method stub
		List<Character> ops = new ArrayList<Character>();
		//3-3.4*3/2.5*@3
		exp = getExp(exp);//把表达式中-符号 转成@
		String[] split = exp.split("[0123456789\\.@]");
		for (String s:split) {
			if (!s.trim().equals("")){
				ops.add(s.charAt(0));
			}
		}
		return ops;
		
	}

	private static List<Double> getNumbers(String exp) {
		// TODO Auto-generated method stub
		List<Double> numbers = new ArrayList<Double>();
		//3-3.4*3/2.5*@3
		exp = getExp(exp);//把表达式中-符号 转成@
		String[] split = exp.split("[\\+\\-\\*/]");
		for (String s : split){
			if (s.charAt(0) == '@') {
				s = "-" + s.substring(1);
			}
			numbers.add(Double.parseDouble(s));
		}
		return numbers;
	}

}
