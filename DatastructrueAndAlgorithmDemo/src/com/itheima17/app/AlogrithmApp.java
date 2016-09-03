package com.itheima17.app;

import java.util.TreeSet;

public class AlogrithmApp {
	private static int count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		 int datas[] = new int[500000]; 
		 initData(datas); 
		 long start = System.currentTimeMillis();
		 //insertSort(datas);
		 quickSort(datas,0,datas.length - 1); 
		 long end = System.currentTimeMillis();
		 System.out.println(end - start);
		 

		// System.out.println(sum(150));

		/*move(30, 'a', 'b', 'c');
		System.out.println(count);*/
	}

	/**
	 * @param datas
	 *            数组
	 * @param start
	 *            起始下标（ 最左边的下标）
	 * @param end
	 *            结束的下标（最后边的下标)
	 * 
	 */
	public static void quickSort(int datas[], int start, int end) {
		if (start >= end) {
			//交叉
			return;
		}
		int middle = findMiddle(datas, start, end);// 找分界点
		quickSort(datas, start, middle - 1);// 左边一半
		quickSort(datas, middle + 1, end);// 右边一半
	}

	/**
	 * @param datas
	 * @param start
	 * @param end
	 * @return 数据分界点的下标
	 */
	private static int findMiddle(int datas[], int start, int end) {
		int middle = -1;
		// 找分界点的过程
		// 1. 以后最好一个数据做参照物
		int temp = datas[end];
		// 2. 定义left和right
		int left = start;
		int right = end - 1;
		// 3. 定义死循环找中间点
		while (true) {
			// 1. 从左边往右找第一个比参照物大的值
			while (left < end && datas[left] <= temp)
				left++;
			// 跳出while有两个条件： 1. left == end
			// 2. datas[left] > temp
			if (left == end) {
				// 说明参照物的值是最大的
				middle = end;
				break;// 结束
			}

			// 2. 从右边往左找第一个比惨照物小的值
			while (right >= start && datas[right] >= temp)
				right--;
			// 跳出while有两个条件： 1. right == start
			// 2. datas[right] < temp

			if (right == start - 1) {
				// 说明参数物是最小值，也算是交叉，交给下面代码处理
			}
			// 3. 判断 是否交叉
			if (left < right) {
				// 1. 没有交叉，继续找
				//交换left 和right的值
				change(datas,left,right);
				continue;
			} else {
				// 2. 交叉 ，中间点（交换left和参照物的值）
				//交换left和参照物的值
				change(datas,left,end);
				//left的位置把数据分成2半
				middle = left;
				break;//跳出
			}
		}

		return middle;

	}
	
	private static void change(int[] datas, int left ,int right){
		int t = datas[left];
		datas[left] = datas[right];
		datas[right] = t;
	}

	/**
	 * @param panNumber
	 *            盘的个数
	 * @param a
	 *            原柱子
	 * @param b
	 *            中间柱
	 * @param c
	 *            目标柱
	 */
	public static void move(int panNumber, char a, char b, char c) {
		//
		count++;
		if (panNumber != 1) {
			move(panNumber - 1, a, c, b);
			move(panNumber - 1, b, a, c);
		}
	}

	/**
	 * @param panNumber
	 *            盘的个数
	 * @param a
	 *            原柱子
	 * @param b
	 *            中间柱
	 * @param c
	 *            目标柱
	 */
	public static void moveGuocheng(int panNumber, char a, char b, char c) {
		//
		if (panNumber < 1) {
			throw new RuntimeException("panNumber must > 0");
		}
		if (panNumber == 1) {
			System.out.println("盘" + panNumber + "从" + a + "柱移动到" + c + "柱");
		} else {
			// 1.把n-1盘从a柱借助c柱移动b柱
			move(panNumber - 1, a, c, b);
			// 2.把第n个盘从a柱移动到c柱
			System.out.println("盘" + panNumber + "从" + a + "柱移动到" + c + "柱");
			// 3.把n-1盘从b柱借助a柱移动c柱
			move(panNumber - 1, b, a, c);
		}
	}

	/*
	 * 1 到n的和
	 */
	private static int sum(int number) {
		if (number < 1) {
			throw new RuntimeException("number must > 0");
		}

		if (number == 1) {
			return number;
		} else {
			return number + sum(number - 1);
			// 4 + 3 + 2 + 1
		}

	}

	private static void print(int[] datas) {
		for (int i = 0; i < datas.length; i++) {
			System.out.print(datas[i] + ",");
		}
		System.out.println();

	}

	/**
	 * 数组初始化
	 * 
	 * @param datas
	 */
	private static void initData(int datas[]) {
		for (int i = 0; i < datas.length; i++) {
			// 随机初始化数据
			datas[i] = (int) (Math.random() * 100) + 1;// 1到100随机数
		}
	}

	private static void insertSort(int datas[]) {
		// 插入排序
		for (int i = 1; i <= datas.length - 1; i++) {
			int temp = datas[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (datas[j] > temp) {
					datas[j + 1] = datas[j];// 瞬移
				} else {
					// 小于等于temp
					// 找到位置,放到该位置的后面

					break;// 结束
				}
			}
			// 放的位置
			datas[j + 1] = temp;//
			// 判断j 是否是-1

			/*
			 * if (j == -1) { //说明 temp是当前比较的值中是最小 datas[0] = temp; }
			 */
		}
	}

}
