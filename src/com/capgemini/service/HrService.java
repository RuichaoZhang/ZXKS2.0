package com.capgemini.service;


import com.capgemini.domain.Hr;

/**
 * 定义名为HrService的接口
 * @author chao538
 *
 */
public interface HrService {
	public Hr getHr(String hrName, String hrPassword);
}
