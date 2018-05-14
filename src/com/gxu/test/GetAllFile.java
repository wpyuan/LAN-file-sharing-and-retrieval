package com.gxu.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.gxu.utils.FilenameFilterUtil;

public class GetAllFile {
	static List<File> fileList = new ArrayList<>();
	static String searchInfo;
	public static void main(String[] args) {
		searchInfo = "ряЁЖ";
		find("C:/Users/fileGX/");
		System.out.println("----------------");
		for (File f : fileList) {
			System.out.println(f.toString());
		}
 
	}
	public static void find(String path) {
		File f = new File(path);
		if (!f.exists()) {
			System.out.println("The path not have file");
			return;
		}
		if (f.isFile()) {
			System.out.println(f.toString());
			if (f.getName().indexOf(searchInfo)!=-1) {
				fileList.add(f);
			}
			return;
		}
		System.out.println(f.toString());
		if (f.getName().indexOf(searchInfo)!=-1) {
			fileList.add(f);
		}
		File[] files = f.listFiles();
		for (File file : files) {
			find(file.getPath());
		}
	}

}
