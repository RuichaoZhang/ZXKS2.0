package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.TestRuleItem;

/**
 * 试卷类型Dao层
 * @author BianBian
 *
 */
public interface TestRuleItemDao extends BaseDao{
	/**
	 * 找到对应试卷的所有试卷类型
	 * @param testRuleId 试卷Id
	 * @return 
	 */
	public List<TestRuleItem> findTestRuleItem(String testRuleId);
	
	/**
	 * 找到对应试卷的所有试卷类型的个数
	 * @param testRuleId 试卷Id
	 * @return
	 */
	public int findTestRuleItemNum(String testRuleId);
	
}
