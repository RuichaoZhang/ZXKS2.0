package com.capgemini.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;

public class Test_updateTestRuleIetm {

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
		String testTypeId = "2";
		String testRuleId = "4";
		String testRuleItemId = "3";
		int testRuleItemNum = 78;
		TestType testType = new TestType();
		testType.setTestTypeId(testTypeId);
		TestRule testRule = new TestRule();
		testRule.setTestRuleId(testRuleId);
		TestRuleItem testRuleItem = new TestRuleItem(testRuleItemId,testRuleItemNum,testType,null);
		List<TestRuleItem> testRuleItems = new ArrayList<TestRuleItem>();
		testRuleItems.add(testRuleItem);
		testRule.setTestRuleItemList(testRuleItems);
		TestRuleItemDao dao = DaoFactory.getInstance().getTestRuleItemDaoImpl();
		try {
			dao.update(testRule);
		} catch (ExceptionMessege e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
