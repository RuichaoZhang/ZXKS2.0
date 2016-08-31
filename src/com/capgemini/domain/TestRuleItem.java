package com.capgemini.domain;

public class TestRuleItem {
	
	private String testRuleItemId;
	
	private int testRuleItemNum;
	
	private TestType testType;
	
	private TestRule testRule;

	

	public TestRuleItem() {
		super();
	}
	

	public TestRuleItem(String testRuleItemId, int testRuleItemNum,
			TestType testType, TestRule testRule) {
		super();
		this.testRuleItemId = testRuleItemId;
		this.testRuleItemNum = testRuleItemNum;
		this.testType = testType;
		this.testRule = testRule;
	}


	public String getTestRuleItemId() {
		return testRuleItemId;
	}

	public void setTestRuleItemId(String testRuleItemId) {
		this.testRuleItemId = testRuleItemId;
	}

	public int getTestRuleItemNum() {
		return testRuleItemNum;
	}

	public void setTestRuleItemNum(int testRuleItemNum) {
		this.testRuleItemNum = testRuleItemNum;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public TestRule getTestRule() {
		return testRule;
	}

	public void setTestRule(TestRule testRule) {
		this.testRule = testRule;
	}

	
	
}
