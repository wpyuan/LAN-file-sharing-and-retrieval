package com.gxu.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




/**
 * @author 王培源
 * 创建时间 2018/03/04
 * 类描述：字符编码转换工具
 * 版本：v1.0 
 */
public class CodeChangeUtil {
	
	public static String changeUTF8(String s){
		try {
			if(s!=null)
				s=URLDecoder.decode(s, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return s;		
	}
}
