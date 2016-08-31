package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.Grade;

/**
 * GradeDao,定义Grade的方法
 * @author Administrator
 */

public interface GradeDao extends BaseDao{

	/**
	 * 模糊查询方法
	 * 
	 * @param   传入查询的条件examineeName, examineeSex, examineeSchool, ppositionName, examineeState, startIndex, pageSize
	 * @return  返回按条件查询出的成绩集合
	 */
	public List<Grade> findByLike(String examineeName, String examineeSex,
			String examineeSchool, String ppositionName, String examineeState,int startIndex, int pageSize);
	
	/**
	 * 查询记录总条数
	 * 
	 * @param   传入查询的条件examineeName, examineeSex, examineeSchool, ppositionName, examineeState
	 * @return  返回总记录数
	 */
	public int getTotalRecords(String examineeName, String examineeSex,
			String examineeSchool, String ppositionName, String examineeState);
	
	/**
	 * 查询职位信息 
	 * 
	 * @param  无
	 * @return 返回grade集合，包含职位信息
	 */
	 public List<Grade> findPposition();
	
	/**
	 * 查询分页数据
	 * @param startIndex 每页开始的记录的索引编号
	 * @param pageSize 每页显示的记录条数
	 * @return 一个List里面全是Grade
	 */
	public List<Grade> findPageRecords(int startIndex, int pageSize);
}
