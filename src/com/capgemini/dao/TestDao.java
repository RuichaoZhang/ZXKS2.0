package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.Test;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;

public interface TestDao extends BaseDao{
	
	/**
	 * 查询记录总条数
	 */
	
	public int getTotalRecords(String testSubject, TestType testType,
			String testItemTrue);
	
	/**
	 * 查询分页数据
	 * @param startIndex 每页开始的记录的索引编号
	 * @param pageSize 每页显示的记录条数
	 * @return
	 */
	public int getTotalRecords();
	
	
	public List<Test> findByTestType(TestType testType);
	/**
	 *查询所有的试题
	 * @return 返回试题对象的集合
	 */
	
	public List<Test> findAllTest(int startIndex, int pageSize);

	public List<Test> findByLikeTest(String testSubject, TestType testType,
			String testItemTrue, int startIndex, int pageSize);
	
	/**
	 * 根据试题的编号查询试题
	 * @param testId
	 * @return 返回一个试题的对象
	 */
	public Test findByTestId(String testId);
	

	public List<Test> generateTest(List<TestRuleItem> testRuleItems); 

	/**
	 * 批量插入试题
	 * @param tests 试题列表
	 */
	public void insertAllTest(List<Test> tests);
	
	/**
	 * 根据试题题目查询试题
	 * @param testSubject
	 * @return 返回一个试题对象
	 */
	public Test findByTestSubject(String testSubject);
	
	/**
	 * 根据试题类型ID查询该试题的个数
	 * @param testTypeId
	 * @return
	 */
	public int findByTestTypeName(String testTypeId);
}