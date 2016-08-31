package com.capgemini.util;

import java.util.List;

/**
 * 界面上所有与分页有关的都找此类
 * @author chao538
 *
 */
public class Page {
	
	@SuppressWarnings("rawtypes")
	private List records;
	
	//每页显示的记录条数
	private int pageSize = 7;
	
	//用户要看的页码,即当前页码
	private int pageNum;
	
	//总页数
	private int totalPage;
	
	//开始的索引
	private int startIndex;
	
	//总记录数
	private int totalrecords;
	
	public Page(int pageNum, int totalrecords){
		
		this.pageNum = pageNum;
		this.totalrecords = totalrecords;
		
		//计算每页开始记录的索引
		startIndex = (pageNum - 1);
		
		//计算总页数
		totalPage = totalrecords%pageSize == 0 ? totalrecords/pageSize : (totalrecords / pageSize + 1);
	}
	
	@SuppressWarnings("rawtypes")
	public List getRecords() {
		return records;
	}

	@SuppressWarnings("rawtypes")
	public void setRecords(List records) {
		this.records = records;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(int totalrecords) {
		this.totalrecords = totalrecords;
	}
	
	
}
