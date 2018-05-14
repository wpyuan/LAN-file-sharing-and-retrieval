package com.gxu.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
	public File[] getFileArray(String path,HttpServletRequest request)throws Exception;
	public String getVisitorIp(HttpServletRequest request)throws Exception;
	public String createShareFolder(HttpServletRequest request)throws Exception;
}
