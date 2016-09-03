package com.caojun.app;

import java.util.Iterator;

public class App {
	public static void main(String[] args) {
		MyDoubleLink<String> datas = new MyDoubleLink<String>();
		datas.add("aaa");
		datas.add("bbb");
		datas.add("ccc");
		System.out.println(datas);
		
		datas.remove("ccc");
		System.out.println(datas);
		
		Iterator<String> iterator = datas.iterator();
		while(iterator.hasNext()){
			datas.add("ddd");
			System.out.println(iterator.next());
		}
		
	}
}
