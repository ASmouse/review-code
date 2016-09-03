package com.caojun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class App {
	public static void main(String[] args) {
	/*	String json = "{\"gd\":[\"sz\",\"gz\"]}";
		Gson gson = new Gson();
		
		JsonData data  = (JsonData) gson.fromJson(json, JsonData.class);
		System.out.println(data.gd.get(0));*/
		
		try {
			String json =  stream2String(new FileInputStream(new File("categories.json")));
			
			Gson gson = new Gson();
			JsonData data = gson.fromJson(json,JsonData.class);
			System.out.println(data.data.get(0).children.get(0).title);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String stream2String(InputStream is){
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			while((line = reader.readLine())!=null){
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		return sb.toString();
	}
}
