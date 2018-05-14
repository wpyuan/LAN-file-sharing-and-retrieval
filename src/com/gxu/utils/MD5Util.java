package com.gxu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


/**
 * @时间 2018/2/22 09点49分
 */
public class MD5Util {
	/*
	 * 静态类方法，获取字符串加密后的md5码
	 */
	public static String getMD5(String password){
		String newstr = null;
		try {
			//创建一个MD5的消息签名			
			MessageDigest md5=MessageDigest.getInstance("md5");
			//创建一个beseenconder对象
			BASE64Encoder base=new BASE64Encoder();
			//获取字符串的md5码
			newstr=base.encode(md5.digest(password.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newstr;
	}

}
