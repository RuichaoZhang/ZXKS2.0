package com.capgemini.domain;

import java.util.List;

public class ExaminationPaper {
	
	/**
	 * 考试界面所有的试题
	 */
	private List<Test> testList;
	
	/**
	 * 页面所显示的时间
	 */
	private int time;
	
	/**
	 * 页面的题目总数
	 */
	private int totalTest;

	public ExaminationPaper() {
		super();
	}

	public ExaminationPaper(List<Test> testList, int time, int totalTest) {
		super();
		this.testList = testList;
		this.time = time;
		this.totalTest = totalTest;
	}

	public List<Test> getTestList() {
		return testList;
	}

	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTotalTest() {
		return totalTest;
	}

	public void setTotalTest(int totalTest) {
		this.totalTest = totalTest;
	}
	
}
