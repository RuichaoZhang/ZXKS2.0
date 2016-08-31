package com.capgemini.domain;

import java.util.List;

public class Test {
	private String testId;
	
	private String testSubject;
	
	private TestType testType;
	
	private int testScore;
	
	private String testItemTrue;//用来存放试题的正确答案，但是在数据库中没有此字段
	
	private List<TestItem> testItemList;

	public List<TestItem> getTestItemList() {
		return testItemList;
	}

	public void setTestItemList(List<TestItem> testItemList) {
		this.testItemList = testItemList;
	}

	public Test() {
		super();
	}

	public Test(String testId, String testSubject, TestType testType,
			int testScore) {
		super();
		this.testId = testId;
		this.testSubject = testSubject;
		this.testType = testType;
		this.testScore = testScore;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestSubject() {
		return testSubject;
	}

	public void setTestSubject(String testSubject) {
		this.testSubject = testSubject;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public int getTestScore() {
		return testScore;
	}

	public void setTestScore(int testScore) {
		this.testScore = testScore;
	}

	public String getTestItemTrue() {
		return testItemTrue;
	}

	public void setTestItemTrue(String testItemTrue) {
		this.testItemTrue = testItemTrue;
	}
	
	
	
}
