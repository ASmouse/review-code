package com.itheima17.app;

public class TreeApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTree datas = new MyTree();
		datas.add(33);
		datas.add(39);
		datas.add(13);
		datas.add(25);
		datas.add(17);
		datas.add(21);
		datas.add(45);
		datas.add(35);
		
		datas.update(13,31);
		datas.print();
	}

}
