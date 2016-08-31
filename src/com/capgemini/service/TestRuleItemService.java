package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.domain.TestRuleItem;
import com.capgemini.exception.ExceptionMessege;

/**
 * 定义名为TestRuleItemService的接口
 * @author bianbian 2015/11/29
 *
 */
public interface TestRuleItemService {
	    /**
	     * 增加试卷类型
	     * @param testRuleId 试卷Id
	     * @param testRuleItem 试卷类型数组
	     * @return 返回boolean变量，为true是成功，为false时失败
	     */
		public boolean addTestRuleItem(ArrayList<TestRuleItem> testRuleItems);
		/**
		 * 删除试卷类型
		 * @param testRuleId 试卷Id
		 * @return 返回boolean变量，为true是成功，为false时失败
		 */
		public boolean deleteTestRuleItem(String testRuleId);
		/**
		 * 修改试卷类型
		 * @param testRuleItemId 试卷类型Id
		 * @param testRuleItem   试卷类型数组
		 * @param testRuleId     试卷Id
		 * @return 返回boolean变量，为true是成功，为false时失败
		 * @throws ExceptionMessege  抛出异常
		 */
		public boolean updateTestRuleItem(String testRuleItem[],int Num) throws ExceptionMessege;
		/**
		 * 得到对应试卷Id的所有试卷类型
		 * @param testRuleId 试卷Id
		 * @return
		 */
		public List<TestRuleItem> findTestRuleItem(String testRuleId);
		
		/**
		 * 得到对应试卷Id的所有试卷类型的数目
		 * @param testRuleId 试卷Id
		 * @return
		 */
		public int findTestRuleItemNum(String testRuleId);
	
}