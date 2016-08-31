package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.TestRule;

public interface TestRuleDao extends BaseDao{
	
	/**
	 * 通过试卷名查询
	 * @param testruleName 试卷名称
	 * @return 返回TestRule对象
	 */
	public TestRule fingByTestRuleName(String testruleName);
	
	/**
	 * 查询所有试卷名称
	 * @return 返回List对象
	 */
	public List<TestRule> findTestRuleName();
	
	/**
	 * 查询总记录数
	 * @return 返回记录的总条数
	 */
	public int getTotalRecords();
	
	/**
	 * 分页查询
	 * @param startIndex 每页开始的记录的索引编号
	 * @param pageSize   每页显示的记录条数
	 * @return 返回List对象
	 */
	public List<TestRule> findPageRecords(int startIndex,int pageSize);
	
	/**
	 * 对试卷进行判断
	 * @param testRuleName 试卷名称
	 * @return 返回boolean对象
	 */
	public boolean judgeTestRule(TestRule testRule);
	
}
