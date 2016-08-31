package com.capgemini.dao.impl;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.dao.TestItemDao;
import com.capgemini.domain.Test;
import com.capgemini.domain.TestItem;
import com.capgemini.util.DBConnection;
/**
 * 试题选项TestItemDaoImpl的Dao实现类
 * @author wanghuan
 * @since 2015-11-28
 *
 */

public class TestItemDaoImpl implements TestItemDao {

	public TestItemDaoImpl() {
	}

	/**
	 * 按ID删除试题选项
	 * @param 按照试题ID将试题选项删除
	 * @return 返回时true时删除成功，返回为false时删除失败
	 */
	@Override
	public boolean delete(String id) {
		boolean flag = false;
		String sql = "delete from testitem where testId = '"+id+"'";
		DBConnection connection = new DBConnection();
		if(connection.executeUpdate(sql) == 4){
			flag = true;			
		}	
		if (connection != null) {
			connection.close();
		}
		return flag;
	}

	/**
	 * 修改试题选项
	 * @param 为其传入一个TestItem的List对象
	 * @return 返回是true修改成功，返回是false修改失败
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean update(Object obj) {
		boolean flag = false;
//		int count = 0;
		ArrayList<TestItem> list = (ArrayList<TestItem>)obj;
		Iterator<TestItem> it = list.iterator();
		DBConnection connection = new DBConnection();
		while(it.hasNext()){
			TestItem testItem = it.next();
			String sql = "update testitem set" +
					" testItemContent = '"+testItem.getTestItemContent()+"'," +
					" testItemState = '"+testItem.getTestItemState()+"'" +
					" where testItemId = '"+testItem.getTestItemId()+"'";
		   connection.executeUpdate(sql);
		   flag = true;
		 }			
		
		if (connection != null) {
			connection.close();
		}
		return flag;
	}

	/**
	 * 保存试题选项
	 * @param 为其传入一个TestItem的对象集合
	 * @return 返回true时保存成功，返回false时保存失败
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean save(Object obj) {
		
		boolean flag = false;
		int count = 0;
		ArrayList<TestItem> list =(ArrayList<TestItem>)obj;
		Iterator<TestItem> it = list.iterator();
		DBConnection connection = new DBConnection();
		while (it.hasNext()) {
			TestItem testItem = it.next();
			String sql = "insert testitem values(" + "'"
					+ testItem.getTestItemId() + "'," + "'"
					+ testItem.getTestItemContent() + "'," + "'"
					+ testItem.getTestItemState() + "'," + "'"
					+ testItem.getTest().getTestId() + "')";
			if (connection.executeUpdate(sql) == 1) {
				count++;
			} 
		}
		if(count == list.size()){
			flag = true;			
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	}
	
	/**
	 * 通过试题ID查询试题选项
	 * @param 为其传入一个试题ID
	 * @return 返回一个试题对应的所有的试题选项
	 */

	@Override
	public List<TestItem> findByTestId(String testId) {
		ArrayList<TestItem> list = new ArrayList<TestItem>();
		
		String sql = "select * from testitem where testId='"+testId+"'";
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		TestItem testItem;
	
		try {
			while(rs.next()){
				testItem = new TestItem();
				testItem.setTestItemId(rs.getString("testItemId"));
				testItem.setTestItemContent(rs.getString("testItemContent"));
				testItem.setTestItemState(rs.getString("testItemState"));
				Test test = new Test();
				test.setTestId(testId);
				testItem.setTest(test);
				list.add(testItem);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}			
		}
		
		return list;
	}
	
	//未使用
	@Override
	public Object find(String item1, String item2, String item3, String item4) {
		return null;
	}

	//未使用
	@Override
	public Object findById(String id) {
		TestItem testItem = null;
		String sql = "select testitemState from testitem where testitemId='"+id+"'";
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		try {
			if(rs.next()){
				testItem = new TestItem();
				testItem.setTestItemState(rs.getString("testItemState"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return testItem;
	}

	@Override
	public List<?> findAll() {
		return null;
	}
}
