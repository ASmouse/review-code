package com.caojun.app;

import java.util.LinkedList;

public class Shushu {
	public static void main(String[] args) {
		count(5,3);
	}
	
	
	public static void count(int personNum , int num){
		LinkedList<Integer> data = new LinkedList<Integer>();
		//¸³Öµ
		for(int i = 1; i <= personNum ; i++){
			data.add(i);
		}
		int c;
		int count =0;
		while(!data.isEmpty()){
			 c = data.poll();
			 count++;
			 if(count % num ==0){
				 System.out.println(c);
			 }else{
				 data.add(c);
			 }
		}
	}
}
