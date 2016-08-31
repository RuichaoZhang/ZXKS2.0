package com.capgemini.domain;

public class TestItem {
	private String testItemId;
	
	private String testItemContent;
	
	private String testItemState;
	
	private Test test;

	public TestItem() {
		super();
	}
	

	public TestItem(String testItemId, String testItemContent,
			String testItemState, Test test) {
		super();
		this.testItemId = testItemId;
		this.testItemContent = testItemContent;
		this.testItemState = testItemState;
		this.test = test;
	}


	public String getTestItemId() {
		return testItemId;
	}

	public void setTestItemId(String testItemId) {
		this.testItemId = testItemId;
	}

	public String getTestItemContent() {
		return testItemContent;
	}

	public void setTestItemContent(String testItemContent) {
		this.testItemContent = testItemContent;
	}

	public String getTestItemState() {
		return testItemState;
	}

	public void setTestItemState(String testItemState) {
		this.testItemState = testItemState;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	
}
