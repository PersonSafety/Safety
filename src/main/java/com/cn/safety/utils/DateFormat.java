package com.cn.safety.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具
 * @author tech
 *
 */
public class DateFormat {

	private static final String df1 = "yyyy-MM-dd HH:mm:ss"; 
	private static final String df2 = "yyyy-MM-dd HH:mm-ss"; 
	
	public static String dateToStr(Date date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		String s = df.format(date);
		return s;
	}
	public static Date strToDate(String dateStr){
		SimpleDateFormat df = new SimpleDateFormat(df1);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static void main(String[] args) {
		Date date = new Date();
		String s = dateToStr(date,df2);
		System.out.println(s);
		
	}
}










