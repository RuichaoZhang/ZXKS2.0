package com.capgemini.test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.factory.DaoFactory;

public class Test_findRuleItem {

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
		String testRuleItemId = "1";
		TestRuleItemDao dao = DaoFactory.getInstance().getTestRuleItemDaoImpl();
		dao.findById(testRuleItemId);
	}

}
