package com.gxu.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.springframework.ui.context.Theme;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import sun.misc.FormattedFloatingDecimal;

public class SearchInfoUtil {
	private List<File> fileList = new ArrayList<>();
	private List<SmbFile> smbFileList = new ArrayList<>();
	public List<File> getFileList() {
		return fileList;
	}
	
	public List<File> run(String path,String info) {
		fileList = new ArrayList<>();
		find(path,info);
		return fileList;
	}
	public List<SmbFile> smbRun(String path,String info) throws SmbException, MalformedURLException {
		smbFileList = new ArrayList<>();
		smbFind(path,info);
		return smbFileList;
	}
	public void find(String path,String info) {
		File f = new File(path);
		if (!f.exists()) {
			//System.out.println("The path not have file");
			return;
		}
		if (f.isHidden()) {
			//System.out.println("The is f is hidden");
			return;
		}
		if (f.isFile()) {
			//System.out.println(f.toString());
			if (f.getName().indexOf(info)!=-1) {
				fileList.add(f);
			}
			return;
		}
		//System.out.println(f.toString());
		if (f.getName().indexOf(info)!=-1) {
			fileList.add(f);
		}
		File[] files = f.listFiles();
		for (File file : files) {
			find(file.getPath(),info);
		}
	}
	public void smbFind(String path,String info) throws SmbException, MalformedURLException {
		SmbFile f = new SmbFile(path);
		if (!f.exists()) {
			//System.out.println("The path not have file");
			return;
		}
		//System.out.println("f.isHidden()"+f.isHidden());
		if (f.isHidden()) {
			//System.out.println("The is f is hidden");
			return;
		}
		if (f.isFile()) {
			//System.out.println(f.toString());
			if (f.getName().indexOf(info)!=-1) {
				//System.out.println(f.toString());
				smbFileList.add(f);
			}
			return;
		}
		//System.out.println(f.toString());
		if (f.getName().indexOf(info)!=-1) {
			//System.out.println(f.toString());
			smbFileList.add(f);
		}
		SmbFile[] files = f.listFiles();
		for (SmbFile file : files) {
			smbFind(file.getPath(),info);
		}
	}
	
}
