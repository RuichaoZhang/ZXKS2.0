package com.capgemini.test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.dao.TestRuleDao;
import com.capgemini.factory.DaoFactory;

public class Test_findTestRuleName {

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
		String testRuleName = "java试卷";
		TestRuleDao dao = DaoFactory.getInstance().getTestRuleDaoImpl();
		dao.fingByTestRuleName(testRuleName);
	}

}
