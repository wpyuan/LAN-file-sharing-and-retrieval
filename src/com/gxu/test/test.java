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
			System.out.print("�ɶ�");
		}else {
			System.out.print("���ɶ�");
		}
		if (file.canWrite()) {
			System.out.print("��д");
		}else {
			System.out.print("����д");
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
