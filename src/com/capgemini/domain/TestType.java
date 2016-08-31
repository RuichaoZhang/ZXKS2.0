package com.capgemini.domain;

public class TestType {
	
	private String testTypeId;
	
	private String testTypeName;

	public TestType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestType(String testTypeId, String testTypeName) {
		super();
		this.testTypeId = testTypeId;
		this.testTypeName = testTypeName;
	}

	public String getTestTypeId() {
		return testTypeId;
	}

	public void setTestTypeId(String testTypeId) {
		this.testTypeId = testTypeId;
	}

	public String getTestTypeName() {
		return testTypeName;
	}

	public void setTestTypeName(String testTypeName) {
		this.testTypeName = testTypeName;
	}

	
}
