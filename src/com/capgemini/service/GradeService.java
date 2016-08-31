package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.Grade;
import com.capgemini.util.Page;

/**
 * 定义名为GradeService的接口
 * @author Administrator
 *
 */
public interface GradeService {
	
	
	
	/**
	 * 根据用户页码返回封装了分页有关数据的对象
	 * 
	 * @param   pageNum
	 * @return  返回查询成绩集合及分页信息
	 */
	public Page findPageRecords(String pageNum);
    
	/**
	 * 模糊查询，根据用户页码返回封装了分页有关数据的对象
	 * 
	 * @param   传入查询的条件examineeName, examineeSex, examineeSchool, ppositionName, examineeState, pageNum
	 * @return  返回按条件查询出的成绩集合及分页信息
	 */
	public Page findByLike(String examineeName, String examineeSex,
			String examineeSchool, String ppositionName, String examineeState,String pageNum);
	
	/**
	 * 查询职位
	 * 
	 * @param  无
	 * @return 返回grade集合，包含职位信息
	 */
	public List<Grade> findPposition();
	
}

