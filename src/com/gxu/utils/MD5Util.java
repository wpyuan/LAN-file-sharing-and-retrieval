package com.gxu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


/**
 * @ʱ�� 2018/2/22 09��49��
 */
public class MD5Util {
	/*
	 * ��̬�෽������ȡ�ַ������ܺ��md5��
	 */
	public static String getMD5(String password){
		String newstr = null;
		try {
			//����һ��MD5����Ϣǩ��			
			MessageDigest md5=MessageDigest.getInstance("md5");
			//����һ��beseenconder����
			BASE64Encoder base=new BASE64Encoder();
			//��ȡ�ַ�����md5��
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
