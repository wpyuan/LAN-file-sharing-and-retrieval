package com.gxu.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.gxu.entity.FileInfo;

import jcifs.smb.SmbFile;

public interface HomeService {
	public void downLoad(HttpServletRequest request,HttpServletResponse response, String path, String name) throws Exception;
	public int upLoad(HttpServletRequest request,String srcPath,String fileName,String destPath)throws Exception;
	public File[] getFileArray(HttpServletRequest request, String openPath)throws Exception; 
	public List<SmbFile> getFileListBySmb(HttpServletRequest request)throws Exception; 
	public int createFolder(String path,String type)throws Exception;
	public int upLoadByHTTP()throws Exception;
	public List<FileInfo> getListByInfo(String path,String searchInfo)throws Exception;
	public List<FileInfo> getSmbListByInfo(String path,String searchInfo)throws Exception;
}
