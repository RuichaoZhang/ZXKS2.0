package com.capgemini.util;

public class Config {
	
	/**
	 * 成功
	 */
	public final static String SUCCESS = "SUCCESS";
	
	/**
	 * 失败
	 */
	public final  static String FAIL = "FAIL";
	
	/**
	 * 考试默认密码
	 */
	public final  static String PASSWORD = MD5Util.encode("123");
	
	/**
	 * 考生未考试
	 */
	public final  static String NOEXAMING = "0";
	
	/**
	 * 考生正在考试状态
	 */
	public final  static String EXAMING = "1";
	
	/**
	 * 考生已通过
	 */
	public final  static String PASSINGEXAMING = "2";
	
	/**
	 * 考生未通过
	 */
	public final  static String NOPASSINGEXAMING = "3";
}
