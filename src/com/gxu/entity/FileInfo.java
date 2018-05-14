package com.gxu.entity;
/**
 * ��Ϣ����ʵ��
 * @ʱ�� 2018/4/22 13��12��
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInfo {
	private String name;
	private String path;
	private String lastModified;//�޸�����
	private boolean isDirectory;
	private int keyWord;//�ؼ�����ʼλ��
	private int keyWordLength;//�ؼ��ֳ���
	
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
