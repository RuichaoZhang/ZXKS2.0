package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.TestRule;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.util.Page;


/**
 * 定义名为TestRuleService的接口
 * @author 卞治渊   2015/11/25
 * 
 */
public interface TestRuleService {
	
	/**
	 * 增加试卷
	 * @param testRuleId   试卷Id
	 * @param testRuleName 试卷名称
	 * @param testRuleTime 试卷时间
	 * @return 返回boolean变量，为true是成功，为false时失败
	 */
	public boolean addTestRule(String testRuleId,String testRuleName,int testRuleTime);
	
	/**
	 * 删除试卷
	 * @param testRuleId  试卷Id
	 * @return 返回boolean变量，为true是成功，为false时失败
	 */
	public boolean deleteTestRule(String testRuleId);
	
	/**
	 * 修改试卷
	 * @param testRuleId    试卷Id
	 * @param testRuleName  试卷名称
	 * @param testRuleTime  试卷时间
	 * @return 返回boolean变量，为true是成功，为false时失败
	 * @throws ExceptionMessege 抛出异常
	 */
	public boolean updateTestRule(String testRuleId,String testRuleName,int testRuleTime) throws ExceptionMessege;
	
	/**
	 * 通过试卷名查询试卷
	 * @param testRuleName 试卷名称
	 * @return 返回TestRule对象
	 */
	public TestRule findByTestRuleName(String testRuleName);
	
	/**
	 * 通过试卷ID查询试卷
	 * @param testRuleId 试卷Id
	 * @return 返回TestRule对象
	 */
	public TestRule findByTestRuleId(String testRuleId);
	
	/**
	 * 查询所有试卷名称
	 * @return 返回List对象
	 */
	public List<TestRule> findTestRuleName();
	
	/**
	 * 分页查询
	 * @param pageNum 当前页码
	 * @return 返回page对象
	 */
	Page findPageRecords(String pageNum);
	
	/**
	 * 判断试卷
	 * @param testRuleName 试卷名称
	 * @return 返回boolean对象
	 */
	public boolean judgeTestRule(String testRuleName,String testRuleItem[],int testRuleTime,int Num);
}