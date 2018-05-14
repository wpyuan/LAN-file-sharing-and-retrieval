package com.gxu.service;

import javax.servlet.http.HttpServletRequest;

public interface TransferService {
	
	public int testlinkByIP(HttpServletRequest request,String receiveIP)throws Exception;//≤‚ ‘¡¨Ω”
	public boolean transfer(HttpServletRequest request,String receiveIP,String path,String fileName)throws Exception;
}
