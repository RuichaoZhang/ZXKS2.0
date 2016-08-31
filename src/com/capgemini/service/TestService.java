package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.Test;
import com.capgemini.domain.TestType;
import com.capgemini.util.Page;


/**
 * 定义名为TestService的接口
 * @author chao538
 *
 */
public interface TestService {

	public boolean deleteTest(String testId);
	
	public boolean addTest(Test test);
	
	public boolean updateTest(Test test);
	/**
	 * 查询全部试题
	 * @return  返回试题集合
	 */
	public Page findAllTest(int PageNum);
	/**
	 * 根据试题类型的集合查询试题
	 * @param lists
	 * @return 返回试题集合
	 */
	public List<Test> findByTestTypeNameTest(List<TestType> lists); 

	/**
	 * 条件查询试题
	 * @param testSubject
	 * @param testType
	 * @param testItemTrue
	 * @param startIndex
	 * @param pageSize
	 * @return 返回试题的集合
	 */
	public Page findByLikeTest(String testSubject,String testTypeName,
			String testItemTrue,int pageNum);
	
	/**
	 * 根据试题的编号查询试题
	 * @param testId
	 * @return 返回一个试题的对象
	 */
	public Test findByTestId(String testId);
	
	/**
	 * 试题的批量导入
	 * @param testId
	 * @return 返回一个试题的对象
	 */
	public void saveAllTest(List<Test> tests);
	
	/**
	 * 根据试题题目查询试题
	 * @param testSubject
	 * @return 
	 */
	public Test findByTestSubject(String testSubject);
	
	/**
	 * 根据试题类型ID查询该试题的个数
	 * @param testTypeId
	 * @return
	 */
	public int findByTestTypeName(String testTypeId);
	
}