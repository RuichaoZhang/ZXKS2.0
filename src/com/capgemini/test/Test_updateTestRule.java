package com.capgemini.test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.dao.TestRuleDao;
import com.capgemini.domain.TestRule;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;

public class Test_updateTestRule {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String testRuleId = "11";
		String testRuleName = "尼玛";
		int testRuleTime = 111;
		TestRule testRule = new TestRule();
		testRule.setTestRuleId(testRuleId);
		testRule.setTestRuleName(testRuleName);
		testRule.setTestRuleTime(testRuleTime);
		
		TestRuleDao dao = DaoFactory.getInstance().getTestRuleDaoImpl();
		try {
			dao.update(testRule);
		} catch (ExceptionMessege e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
