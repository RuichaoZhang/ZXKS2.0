package com.capgemini.domain;

import java.util.List;

public class TestRule {
	
	private String testRuleId;
	private String testRuleName;	
	private int testRuleTime;
	
	private List<TestRuleItem> testRuleItemList;

	public TestRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public TestRule(String testRuleId, String testRuleName, int testRuleTime,
			List<TestRuleItem> testRuleItemList) {
		super();
		this.testRuleId = testRuleId;
		this.testRuleName = testRuleName;
		this.testRuleTime = testRuleTime;
		this.testRuleItemList = testRuleItemList;
	}


	public String getTestRuleId() {
		return testRuleId;
	}

	public void setTestRuleId(String testRuleId) {
		this.testRuleId = testRuleId;
	}

	public String getTestRuleName() {
		return testRuleName;
	}

	public void setTestRuleName(String testRuleName) {
		this.testRuleName = testRuleName;
	}

	public int getTestRuleTime() {
		return testRuleTime;
	}

	public void setTestRuleTime(int testRuleTime) {
		this.testRuleTime = testRuleTime;
	}

	public List<TestRuleItem> getTestRuleItemList() {
		return testRuleItemList;
	}

	public void setTestRuleItemList(List<TestRuleItem> testRuleItemList) {
		this.testRuleItemList = testRuleItemList;
	}

	
	
	
}
