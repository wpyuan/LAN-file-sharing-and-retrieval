package com.gxu.test;

import java.net.MalformedURLException;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

public class test {
	public static void main(String args[]) throws SmbException, MalformedURLException {

		//String smbUrl = "smb://wpy:6666@192.168.1.6/";
		String smbUrl = "smb://192.168.1.24/";
		//String smbUrl = "smb://192.168.1.6/";
		SmbFile file = new SmbFile(smbUrl);
		if (file.canRead()) {
			System.out.print("可读");
		}else {
			System.out.print("不可读");
		}
		if (file.canWrite()) {
			System.out.print("可写");
		}else {
			System.out.print("不可写");
		}
		
		if (file.exists() && !file.isHidden()) {
			SmbFile[] files = file.listFiles();
			System.out.println(smbUrl+"---------------");
			for (SmbFile f : files) {
				
				System.out.println(f.getName());
				
			}
			System.out.println("-----------------");
		}
	}
}
