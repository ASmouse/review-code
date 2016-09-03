package com.itheima17.app;

import java.util.LinkedList;

public class Shushu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		count(5, 3);
	}

	public static void count2(int personNumber, int number) {
		int[] datas = new int[personNumber];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = i + 1;
		}

		int xiaBiao = 0;

		int dunCount = 0;// 记录蹲下的人数的数
		int dunNumber = 0;// 蹲下的人的个数
		int shiji = 0;
		while (dunNumber < personNumber) {

			// 判断该位置的人是否蹲下
			if (datas[xiaBiao % personNumber] == 0) {
				// 蹲下
				dunCount++;
			} else {
				// 继续循环
				shiji = xiaBiao + 1 - dunCount;
				if (shiji % number == 0) {
					//要蹲下
					System.out.println(datas[xiaBiao % personNumber]);
					datas[xiaBiao % personNumber] = 0;//0表示蹲下	
					dunNumber++;//记录蹲下人的数量	
				} 
			}
			
			xiaBiao++;

		}

		// 数数
	}

	/**
	 * @param personNumber
	 *            人数
	 * @param number
	 *            数的数
	 */
	public static void count(int personNumber, int number) {
		LinkedList<Integer> datas = new LinkedList<Integer>();
		// 所有人排一排（队列）
		for (int i = 1; i <= personNumber; i++) {
			datas.add(i);
		}

		int counts = 0; // 计数器
		while (!datas.isEmpty()) {
			// 对不为空
			// 出队
			int person = datas.poll();// 移除队列的头

			counts++;// 计数器++

			// 判断是否是number的整数倍
			if (counts % number == 0) {
				// 站在旁边
				System.out.println(person);
			} else {
				// 继续站到队列的尾部
				datas.add(person);
			}
		}
	}

}
