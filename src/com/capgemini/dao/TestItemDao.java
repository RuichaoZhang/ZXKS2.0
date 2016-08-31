package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.TestItem;

public interface TestItemDao extends BaseDao{
	
	public List<TestItem> findByTestId(String testId);
	
}
