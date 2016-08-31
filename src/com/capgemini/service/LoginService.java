package com.capgemini.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义名为LoginService的接口
 * @author chao538
 *
 */
public interface LoginService {
	
	/**
	 * 定义Hr登录方法
	 * @param hrName
	 * @param hrPassword
	 * @return
	 */
	public String hrLogin(String hrName, String hrPassword);
	
	/**
	 * 定义考生的登录方法
	 * @param examineeTelephone 考生电话号
	 * @param examineePassword 考生密码
	 * @return
	 */
	public String examineeLogin(String examineeTelephone, String examineePassword);
	/**
	 * 定义Hr登出方法
	 * @param request
	 * @return
	 */
	public String logout(HttpServletRequest request, String user);
}
