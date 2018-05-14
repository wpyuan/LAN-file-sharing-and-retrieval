package com.gxu.test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import com.gxu.utils.SearchInfoUtil;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

public class testSearch {
	static SearchInfoUtil searchInfoUtil = new SearchInfoUtil();
	public static void main(String args[]) throws MalformedURLException, SmbException {
		/*List<File> fList= searchInfoUtil.run("C:/Users/fileGX/", "(2)");
		for (File file : fList) {
			System.out.println(file.toString());
		}*/
		SmbFile smbFile = new SmbFile("smb://192.168.1.24/Users/fileGX/");
		SmbFile[] smbFiles = smbFile.listFiles();
		for (SmbFile smbFile2 : smbFiles) {
			System.out.println(smbFile2.toString());
		}
		System.out.println("---------------------------------");
		List<SmbFile> fList = null;
		try {
			fList = searchInfoUtil.smbRun("smb://192.168.1.24/Users/fileGX/", "d");
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (SmbFile file : fList) {
			System.out.println(file.toString());
		}
	}

}
