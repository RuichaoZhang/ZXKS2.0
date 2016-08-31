package com.capgemini.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换的类
 * @author chao538
 *
 */
public class DateUtil {
	
	/**
	 * 定义日期格式 
	 */
	public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 日期转换成字符串
	 * @return 返回字符串
	 */
	public static String DateToString(Date date){
		String s = format.format(date);
		return s;
	}
	
	/**
	 * 字符串转换成日期
	 * @return 返回日期格式
	 * @throws ParseException 传入的字符串格式与转换格式不匹配,抛出该异常 
	 */
	public static Date StringToDate(String string) throws ParseException{
		Date date = null;
		date = format.parse(string);
		return date;
	}
}
