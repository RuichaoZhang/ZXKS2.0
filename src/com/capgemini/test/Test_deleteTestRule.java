package com.capgemini.test;

import org.junit.Test;

import com.capgemini.dao.TestRuleDao;
import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.factory.DaoFactory;

public class Test_deleteTestRule {
	@Test
	public void deleteTestRule()
	{
		String testRuleId = "1";
		//TestRule testRule = new TestRule();
		TestRuleDao dao1 = DaoFactory.getInstance().getTestRuleDaoImpl();
		TestRuleItemDao dao2 = DaoFactory.getInstance().getTestRuleItemDaoImpl();
		dao1.delete(testRuleId);
		dao2.delete(testRuleId);
	}
}
