package com.gxu.test;

import java.io.File;
import java.io.FilenameFilter;

import com.gxu.utils.FilenameFilterUtil;

public class testFileSearch {

	public static void main(String[] args) {
		File f = new File("C:/Users/fileGX/");  
		MyFilter filter = new MyFilter("(2)");
		String[] files = f.list(filter);
		System.out.println("length="+files.length);
		for(String a:files){
			System.out.println(a);
		}
		System.out.println("----------------");
		files = f.list();
		System.out.println("length="+files.length);
		for(String a:files){
			System.out.println(a);
		}
 
	}
	static class MyFilter implements FilenameFilter{
		private String type;
		public MyFilter(String type){
			this.type = type;
		}
		public boolean accept(File dir,String name){
			if (name.indexOf(type)==-1) {
				return false;
			} else {
				return true;
			}
		}
	}


}
