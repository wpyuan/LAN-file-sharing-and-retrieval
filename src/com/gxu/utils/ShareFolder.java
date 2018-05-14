package com.gxu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

public class ShareFolder {

	protected static Logger log = Logger.getLogger(ShareFolder.class);
	
	public static void createFile(String path){
		File file = new File(path);
		if (!file.exists()) {		
			System.out.println("not exist");
			try {
				file.createNewFile();		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			System.out.println("exist");
		}
	}

	public static void createFolder(String path){
		File file = new File(path);
		if (!file.exists()) {		
			System.out.println("not exist");
			file.mkdir();
		}else {
			System.out.println("exist");
		}
	}
	
	
	public static boolean isExist(String path) {
		File file = new File(path);
		if (!file.exists()) {	
			return false;
		}else {
			return true;
		}
	}
	
	public static void smbDelete(String path) throws MalformedURLException, SmbException {
		SmbFile file = new SmbFile(path);
		if (file.exists()) {
			file.delete();
		}
	}
	
	public static void delete(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
	
	
	public static int getDirectory(String smbUrl,String fileName) {
		String message = null;
		try {
			//String url = "smb://wpy:6666@192.168.1.12/" + fileName;
			String url = smbUrl + fileName;
			SmbFile file = new SmbFile(url);
			if (file.exists() && !file.isHidden()) {
				
				if (file.isDirectory()) {

					String startUrl = url;
					url += "testQx.txt";
					file = new SmbFile(url);

					while (file.exists()) {
						url = startUrl;
						url += "testQxs.txt";
						file.createNewFile(); 
					}

					if (!file.exists()) {
						file.createNewFile(); 
					}

					file = new SmbFile(url);
					if (file.exists()) {
						// System.out.println("create success");

						file.delete();
						return 1;
					} else {
						// System.out.print("create faild");
						return 0;
					}


				} else {
						
					return 1;
				}

			} else {
				
				return 0;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.print(e.getMessage()+"\t");
			message = e.getMessage();
			if (message != null && message.equals("Access is denied.")) {
				
			}
			return 0;
		}

	}

	public static List<SmbFile> getShareFolder(String smbUrl) {
		System.out.println("--- ShareFolder.getShareFolder()---");
		List<SmbFile> fileQX = new ArrayList<SmbFile>();
		try {
			// String smbUrl = "smb://wpy:6666@192.168.1.12/";
			SmbFile file = new SmbFile(smbUrl);

			if (file.exists() && !file.isHidden()) {
				SmbFile[] files = file.listFiles();
				System.out.println(smbUrl+"---------------");
				for (SmbFile f : files) {
					if (getDirectory(smbUrl,f.getName()) == 1) {
						System.out.println(f.getName());
						fileQX.add(f);
					}
				}
				System.out.println("-----------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:" + e.getMessage());
		}
		return fileQX;
	}

	
	public static int copy(String srcPath,String fileName,String destPath) {
		System.out.println(srcPath + "\t" +fileName + "\t" +destPath);
		int flag = -1;
		try {

			File src = new File(srcPath);
			File dest = new File(destPath);

			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.info(df.format(day));

			if (dest.exists()) {
				log.info("exist");
				flag = 2;
			}else {
				flag = 1;
			}
			log.info("copying...");
			FileUtils.copyFile(src, dest);
			log.info("success");

			Date days = new Date();
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.info(dfs.format(days));

			
			
		} catch (IOException e) {
			
			
			e.printStackTrace();
			log.info("error");
			flag = 0;
		}
		return flag;
	}
	
	public static int upload() {
		
		return 0;
	}
	
}
