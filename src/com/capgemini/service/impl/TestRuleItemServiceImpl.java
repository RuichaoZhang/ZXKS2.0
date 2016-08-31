package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




import com.capgemini.dao.TestRuleDao;
import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.TestRuleItemService;
/**
 * TestRuleItemService的实现类
 * @author BianBian 2015/11/25
 *
 */
public class TestRuleItemServiceImpl implements TestRuleItemService{

	/**
	 * 通过工厂得到Dao
	 */
	TestRuleItemDao testRuleItemDao = DaoFactory.getInstance().getTestRuleItemDaoImpl();
	TestRuleDao testRuleDao = DaoFactory.getInstance().getTestRuleDaoImpl();
	/**
	 * 增加试卷类型
	 */
	@Override
	public boolean addTestRuleItem(ArrayList<TestRuleItem> testRuleItems) {
		//ArrayList<TestRuleItem> testRuleItemList = testRuleItems;
		Iterator<TestRuleItem> it = testRuleItems.iterator();
		boolean flag = false;
		int count = 0;
		while(it.hasNext()){
			TestRuleItem testRuleItem = it.next();
			if(testRuleItemDao.save(testRuleItem)){
				count++;				
			}
		}
		if(count == testRuleItems.size()){
			flag = true;
		}
		return flag;
		
	}

	/**
	 * 删除试卷类型
	 */
	@Override
	public boolean deleteTestRuleItem(String testRuleId) {
		
		return testRuleItemDao.delete(testRuleId);
	}

	/**
	 * 修改试题类型
	 * @throws ExceptionMessege 
	 */
	@Override
	public boolean updateTestRuleItem(String testRuleItem[] ,int Num) throws ExceptionMessege {
		TestRule testRule = new TestRule();
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
		return testRuleItemDao.update(testRule);
	}

	/**
	 * 得到对应试卷Id的所有试卷类型
	 */
	@Override
	public List<TestRuleItem> findTestRuleItem(String testRuleId) {
		
		return testRuleItemDao.findTestRuleItem(testRuleId);
	}

	/**
	 * 得到对应试卷Id的所有试卷类型的数目
	 */
	@Override
	public int findTestRuleItemNum(String testRuleId) {
		
		return testRuleItemDao.findTestRuleItemNum(testRuleId);
	}

}
