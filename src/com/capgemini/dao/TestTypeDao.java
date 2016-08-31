package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.TestType;

/**
 * TestTypeDao,定义TestType的方法
 * @author chao538
 */
public interface TestTypeDao extends BaseDao{
	
	/**
	 * 得到test对象通过testName查
	 * @param testName
	 * @return 试题类型的对象
	 */
	public TestType find(String testName);
	
	/**
	 * 查询记录总条数
	 * @return 返回总个数
	 */
	public int getTotalRecords();
	
	/**
	 * 查询分页数据
	 * @param startIndex 每页开始的记录的索引编号
	 * @param pageSize 每页显示的记录条数
	 * @return 一个List里面全是TestType
	 */
	public List<TestType> findPageRecords(int startIndex, int pageSize);
	/**
	 * 查询所有的试题类型
	 * 
	 * @return 返回一个TestType的集合
	 */
	public List<TestType> findAllTestType();
}
