package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.DBConnection;
/**
 * 试卷类型的Dao实现层
 * @author BianBian 2015/11/29
 *
 */
public class TestRuleItemDaoImpl implements TestRuleItemDao{

	/**
	 * 删除试卷类型
	 */
	@Override
	public boolean delete(String id) {
		// 定义boolean类型的flag
		boolean flag = false;
		//定义sql
		String sql = "delete from testruleitem where testRuleId='"+id+"'";
		//建立数据库连接，并执行sql
		DBConnection connection = new DBConnection();
		if(connection.executeUpdate(sql) == 1)
		{
			flag = true;
		}
		if(connection != null)
		{
			connection.close();
		}
		return flag;
	}

	/**
	 * 修改试卷类型
	 */
	@Override
	public boolean update(Object obj){
		// 定义一个boolean类型的flag
		boolean flag = false;
		//把obj强转为TestRule对象
		TestRule testRule = (TestRule) obj;
		//创建List<TestRuleItem>对象
		List<TestRuleItem> testRuleItems = testRule.getTestRuleItemList();
		//建立数据库连接
		DBConnection connection = new DBConnection();
		int b = 0;
		for(TestRuleItem testRuleItem : testRuleItems)
		{
			//得到试卷类型id
			String testRuleItemId = testRuleItem.getTestRuleItemId();
			//得到试卷类型总数
			int testRuleItemNum = testRuleItem.getTestRuleItemNum();
			//得到试题类型Id
			String testTypeId = testRuleItem.getTestType().getTestTypeId();
			//定义sql
			String sql = "UPDATE testruleitem SET testRuleItemNum="+testRuleItemNum+""
					+ ",testtypeId='"+testTypeId+"' where testRuleItemId='"+testRuleItemId+"'";
			if(connection.executeUpdate(sql) == 1)
			{
				b++;
			}
		}
		if(b == testRuleItems.size())
		{
			flag = true;
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	}

	/**
	 * 新增试卷类型
	 */
	@Override
	public boolean save(Object obj) {
		// 定义一个flag
		boolean flag = false;
		TestRuleItem testRuleItem = (TestRuleItem) obj;

		// 建立数据库连接
		DBConnection connection = new DBConnection();
		
		// 得到试卷类型Id
		String testRuleItemId = testRuleItem.getTestRuleItemId();
		// 得到试卷类型总数
		int testRuleItemNum = testRuleItem.getTestRuleItemNum();
		// 得到试题类型Id
		String testTypeId = testRuleItem.getTestType().getTestTypeId();
		// 得到试卷Id
		String testRuleId = testRuleItem.getTestRule().getTestRuleId();
		// 定义sql
		String sql = "insert into testruleitem (testRuleItemId,testRuleItemNum,testTypeId,testRuleId) "
				+ "values('"
				+ testRuleItemId
				+ "',"
				+ testRuleItemNum
				+ ",'"
				+ testTypeId + "','" + testRuleId + "')";
		if (connection.executeUpdate(sql) == 1) {
			flag = true;
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	}

	@Override
	public Object find(String item1, String item2, String item3, String item4) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过试卷类型Id查询
	 */
	@Override
	public Object findById(String id) {
		//定义TestRuleItem对象
		TestRuleItem testRuleItem = null;
		//定义sql
		String sql = "select * from testruleitem where testRuleItemId='"+id+"'";
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next())
			{
				//得到试题类型Id
				String testTypeId = rs.getString("testTypeId");
				//得到试卷Id
				String testRuleId = rs.getString("testRuleId");
				//通过Id得到试题类型对象
				TestType testType = (TestType) DaoFactory.getInstance().getTestTypeDaoImpl().findById(testTypeId);
				//通过Id得到试卷对象
				TestRule testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(testRuleId);
				//得到试卷类型对象
				testRuleItem = new TestRuleItem(rs.getString("testRuleItemId"),
						rs.getInt("testRuleItemNum"),testType,testRule);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (connection != null) {
				connection.close();
			}
		}
		return testRuleItem;
	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 找到对应试卷的所有试卷类型
	 */
	@Override
	public List<TestRuleItem> findTestRuleItem(String testRuleId) {
		// 定义List<TestRuleItem>对象
		List<TestRuleItem> testRuleItems = new ArrayList<TestRuleItem>();
		// 定义TestRule对象
		TestRule testRule = null;
		// 定义TestRuleItem对象
		TestRuleItem testRuleItem = null;
		//定义TestType对象
		TestType testType = null;
		// 定义sql
		String sql = "select * from testruleitem where testRuleId='"+testRuleId+"'";
		// 建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next())
			{
				//得到试卷类型Id
				String testRuleItemId = rs.getString("testRuleItemId");
				//得到试卷类型的个数
				int testRuleItemNum = rs.getInt("testRuleItemNum");
				//得到试题类型的Id
				String testTypeId = rs.getString("testTypeId");
				//得到试题类型对象
				testType = (TestType) DaoFactory.getInstance().getTestTypeDaoImpl().findById(testTypeId);
				//得到试卷对象
				testRule = new TestRule();
				//将试卷Id放进试卷对象
				testRule.setTestRuleId(testRuleId);
				//得到试卷类型对象
				testRuleItem = new TestRuleItem(testRuleItemId,testRuleItemNum,testType,testRule);
				//将试卷类型对象放进List对象中
				testRuleItems.add(testRuleItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (connection != null) {
				connection.close();
			}
		}
		return testRuleItems;
	}

	/**
	 * 找到对应试卷的所有试卷类型的个数
	 */
	@Override
	public int findTestRuleItemNum(String testRuleId) {
		// 定义count
		int count = 0;
		// 定义sql
		String sql = "SELECT COUNT(*) FROM testruleitem WHERE testruleId='"+testRuleId+"'";
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//得到结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			if(rs.next())
			{
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (connection != null) {
				connection.close();
			}
		}
		return count;
	}
}
