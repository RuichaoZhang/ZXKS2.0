package com.capgemini.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.dao.TestRuleDao;
import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.GetUUID;

/**
 * 测试试卷Dao实现类
 * @author BianBian
 *
 */
public class Test_saveTestRule_BZY {
	
//	public static void main(String[] args) {
//		TestRule rule = new TestRule();
//		List<TestRuleItem> items = new ArrayList<TestRuleItem>();
//		String testruleId = GetUUID.getUUID().toString();
//		String testruleitemId = GetUUID.getUUID().toString();
//		int testruleitemNum = 10;
//		String testruleName = "数据库试卷";
//		int testruleTime = 120;
//		TestRuleItem item = new TestRuleItem(testruleitemId, testruleitemNum, null, null);
//		rule.setTestruleId(testruleId);
//		rule.setTestruleName(testruleName);
//		rule.setTestruleTime(testruleTime);
//		rule.setItems(items);
//		TestRuleDao dao1 = DaoFactory.getInstance().getTestRuleDaoImpl();
//		TestRuleItemDao dao2 = DaoFactory.getInstance().getTestRuleItemDaoImpl();
//		dao1.save(rule);
//		dao2.save(rule);
//	}
	@Test
	public void test_saveRule()
	{
		TestRule rule = new TestRule();
		List<TestRuleItem> items = new ArrayList<TestRuleItem>();
		String testruleId = GetUUID.getUUID().toString();
		String testruleName = "数据库试卷";
		int testruleTime = 120;
		rule.setTestRuleId(testruleId);
		rule.setTestRuleName(testruleName);
		rule.setTestRuleTime(testruleTime);
		rule.setTestRuleItemList(items);
		TestRuleDao dao1 = DaoFactory.getInstance().getTestRuleDaoImpl();
		TestRuleItemDao dao2 = DaoFactory.getInstance().getTestRuleItemDaoImpl();
		dao1.save(rule);
		dao2.save(rule);
	}
}
