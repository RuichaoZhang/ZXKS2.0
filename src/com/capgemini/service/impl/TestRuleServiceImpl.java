package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.TestRuleDao;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.TestRuleService;
import com.capgemini.util.Page;

/**
 * 试卷管理Service层的实现
 * @author BianBian 2015/11/29
 *
 */
public class TestRuleServiceImpl implements TestRuleService{

	/**
	 * 通过工厂到的TestRuleDao的对象
	 */
	TestRuleDao testRuleDao = DaoFactory.getInstance().getTestRuleDaoImpl();
	
	/**
	 * 新增试卷
	 */
	@Override
	public boolean addTestRule(String testRuleId,String testRuleName,int testRuleTime) {
		//得到试卷对象
		TestRule testRule = new TestRule();
		//将试卷Id放进试卷对象
		testRule.setTestRuleId(testRuleId);
		//将试卷名称放进试卷对象
		testRule.setTestRuleName(testRuleName);
		//将试卷时间放进试卷对象
		testRule.setTestRuleTime(testRuleTime);

		return testRuleDao.save(testRule);
	}

	@Override
	public boolean deleteTestRule(String testRuleId) {
		
		return testRuleDao.delete(testRuleId);
	}

	/**
	 * 修改试卷
	 */
	@Override
	public boolean updateTestRule(String testRuleId,String testRuleName,int testRuleTime) throws ExceptionMessege {
		//得到试卷对象
		TestRule testRule = new TestRule();
		//将试卷Id放进试卷对象
		testRule.setTestRuleId(testRuleId);
		//将试卷名称放进试卷对象
		testRule.setTestRuleName(testRuleName);
		//将试卷时间放进试卷对象
		testRule.setTestRuleTime(testRuleTime);
		
		return testRuleDao.update(testRule);
	}

	/**
	 * 通过试卷名称查询
	 */
	@Override
	public TestRule findByTestRuleName(String testRuleName) {
		
		return testRuleDao.fingByTestRuleName(testRuleName);
	}

	/**
	 * 通过试卷Id查询
	 */
	@Override
	public TestRule findByTestRuleId(String testRuleId) {
		
		return (TestRule) testRuleDao.findById(testRuleId);
	}

	/**
	 * 查询所有试卷名称
	 */
	@Override
	public List<TestRule> findTestRuleName() {
		
		return testRuleDao.findTestRuleName();
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page findPageRecords(String pageNum) {
		//得到当前页码
		int num = Integer.parseInt(pageNum);
		//得到page对象
		Page page = new Page(num, testRuleDao.getTotalRecords());
		//得到List对象
		List<TestRule> records = testRuleDao.findPageRecords(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);
		return page;
	}

	@Override
	public boolean judgeTestRule(String testRuleName,String testRuleItem[],int testRuleTime,int Num) {
		//得到testRule对象
		TestRule testRule = new TestRule();
		testRule.setTestRuleName(testRuleName);
		testRule.setTestRuleTime(testRuleTime);
		
		TestType testType = null;
		//生成List<TestRuleItem>对象
		List<TestRuleItem> testRuleItems = new ArrayList<TestRuleItem>();
//		int length = testRuleItem.length;
		for(int i = 0;i < Num;i++)
		{
			
			//将testRuleItem[]中的数据取出
			String str1 = testRuleItem[i];
			//将字符串以“_”为分隔符拆开
			String str2[] = str1.split("_");
			//得到试题类型Id
			String testTypeId = str2[1];
			//通过试题类型Id得到试题类型对象
			testType = (TestType) DaoFactory.getInstance().getTestTypeDaoImpl().findById(testTypeId);
			//得到试题类型个数
			int testRuleItemNum = Integer.parseInt(str2[2]);
			//创建一个TestRuleItem对象
			TestRuleItem tRuleItem = new TestRuleItem();
			//将testRuleItemId[]中的数据取出
			String testRuleItemId = str2[0];
			//往TestRuleItem对象里装参数
			tRuleItem.setTestRuleItemId(testRuleItemId);
			tRuleItem.setTestRuleItemNum(testRuleItemNum);
			tRuleItem.setTestType(testType);
			testRuleItems.add(tRuleItem);
			System.out.println("testRuleItems-------------------");
			System.out.println(testRuleItems);
			//将List对象装进testRule对象
			testRule.setTestRuleItemList(testRuleItems);
			
		}
		return testRuleDao.judgeTestRule(testRule);
	}

}
