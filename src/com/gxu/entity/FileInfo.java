package com.gxu.entity;
/**
 * 信息检索实体
 * @时间 2018/4/22 13点12分
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInfo {
	private String name;
	private String path;
	private String lastModified;//修改日期
	private boolean isDirectory;
	private int keyWord;//关键字起始位置
	private int keyWordLength;//关键字长度
	
	public int getKeyWordLength() {
		return keyWordLength;
	}
	public void setKeyWordLength(int keyWordLength) {
		this.keyWordLength = keyWordLength;
	}
	public int getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(int keyWord) {
		this.keyWord = keyWord;
	}
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(long lastModified) {
		
		this.lastModified = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(lastModified));
	}
	@Override
	public String toString() {
		return "FileInfo [name=" + name + ", path=" + path + ", lastModified=" + lastModified + ", isDirectory="
				+ isDirectory + "]";
	}
	
}
