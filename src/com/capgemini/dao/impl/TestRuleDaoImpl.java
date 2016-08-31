package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.TestRuleDao;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.DBConnection;

public class TestRuleDaoImpl implements TestRuleDao{

	/**
	 * 删除试卷模板中的试题类型以及数目
	 * @author 卞治渊
	 * @since 2015/11/12
	 */
	@Override
	public boolean delete(String id) {
		// 定义一个boolean类型的flag
		boolean flag = false;
		//定义sql
		String sql = "delete from testrule where testRuleId='"+id+"'";
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
	 * 修改试题
	 * @author 卞治渊
	 * 
	 */
	@Override
	public boolean update(Object obj) {
		//定义boolean类型的flag
		boolean flag = false;
		//定义TestRule对象
		TestRule testRule = (TestRule) obj;
//		//得到试题类型的List对象
//		List<TestRuleItem> testRuleItems = testRule.getTestRuleItemList();
		//得到试卷的Id
		String testRuleId = testRule.getTestRuleId();
		String testRuleName = testRule.getTestRuleName();
		int testRuleTime = testRule.getTestRuleTime();
		//定义sql
		String sql = "UPDATE testrule SET testRuleName='"+testRuleName+"',testRuleTime="+testRuleTime+" "
				+ "where testRuleId='"+testRuleId+"'";

		//建立数据库连接，并执行sql
		DBConnection connection = new DBConnection();
		
		if(connection.executeUpdate(sql) == 1)
		{
			flag = true;
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	}

	/**
	 * 添加试题模板
	 * @author 卞治渊
	 * @since 2015/11/24
	 */
	@Override
	public boolean save(Object obj) {
		// 定义一个boolean类型的flag
		boolean flag = false;
		// 定义一个TestRule对象
		TestRule testRule = (TestRule) obj;
		
		//定义sql1
		String sql = "insert into testrule(testRuleId,testRuleName,testRuleTime) "
				+ "values('"+testRule.getTestRuleId()+"','"+testRule.getTestRuleName()+"',"+
				testRule.getTestRuleTime()+")";
		
		DBConnection connection = new DBConnection();
		int a = connection.executeUpdate(sql);
		if(a == 1)
		{
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
	 * 通过ID查询
	 * @author 卞治渊
	 * @since 2015/11/24
	 */
	@Override
	public Object findById(String id) {
		// 定义一个TestRule对象
		TestRule testRule = null;
		//定义一个List<TestRuleItem>对象
		List<TestRuleItem> testRuleItems = new ArrayList<TestRuleItem>();
		//定义sql
		String sql1 = "select * from testrule where testRuleId='"+id+"'";
		//建立数据库连接，并执行sql
		DBConnection connection = new DBConnection();
		ResultSet rs1 = connection.executeQuery(sql1);
		try {
			while(rs1.next())
			{
				String testruleId = rs1.getString("testRuleId");
				String sql2 = "select * from testruleitem where testRuleId='"+testruleId+"'";
//				System.out.println("testDao--------------------------");
//				System.out.println(sql2);
//				System.out.println("testDao--------------------------");
				ResultSet rs2 = connection.executeQuery(sql2);
				while(rs2.next())
				{
					//得到试题类型Id
					String testtypeId = rs2.getString("testTypeId");
					TestType testType = (TestType) DaoFactory.getInstance().getTestTypeDaoImpl().findById(testtypeId);
					TestRuleItem testRuleItem = new TestRuleItem();
					testRuleItem.setTestRuleItemId(rs2.getString("testRuleItemId"));
					testRuleItem.setTestRuleItemNum(rs2.getInt("testRuleItemNum"));
					testRuleItem.setTestType(testType);
					testRuleItems.add(testRuleItem);
				}
				testRule = new TestRule(testruleId,rs1.getString("testRuleName"),rs1.getInt("testRuleTime"),testRuleItems);
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
		return testRule;
	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过试卷类型名称查询
	 */
	@Override
	public TestRule fingByTestRuleName(String testruleName) {
		// 定义一个TestRule对象
				TestRule testRule = null;
				//定义一个List<TestRuleItem>对象
				List<TestRuleItem> testRuleItems = new ArrayList<TestRuleItem>();
				//定义sql1
				String sql1 = "select * from testrule where testRuleName='"+testruleName+"'";
				//建立数据库连接，并执行sql1
				DBConnection connection = new DBConnection();
				//定义rs1结果集
				ResultSet rs1 = connection.executeQuery(sql1);
				try {
					//当试卷表查询成功时
					while(rs1.next())
					{
						//得到试卷Id
						String testruleId = rs1.getString("testRuleId");
						//定义sql2
						String sql2 = "select * from testruleitem where testRuleId='"+testruleId+"'";
						//定义rs2结果集
						ResultSet rs2 = connection.executeQuery(sql2);
						//当试卷类型表查询成功时
						while(rs2.next())
						{
							//得到试题类型Id
							String testtypeId = rs2.getString("testTypeId");
							//倒是试题类型对象
							TestType testType = (TestType) DaoFactory.getInstance().getTestTypeDaoImpl().findById(testtypeId);
							//得到试卷类型对象
							TestRuleItem testRuleItem = new TestRuleItem();
							//向试卷类型对象中放入参数
							testRuleItem.setTestRuleItemId(rs2.getString("testRuleItemId"));
							testRuleItem.setTestRuleItemNum(rs2.getInt("testRuleItemNum"));
							testRuleItem.setTestType(testType);
							//将试卷类型对象放入List对象
							testRuleItems.add(testRuleItem);
						}
						//得到试卷对象
						testRule = new TestRule(testruleId,rs1.getString("testRuleName"),rs1.getInt("testRuleTime"),testRuleItems);
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
				return testRule;
	}

	/**
	 * 查询所有试卷名称
	 */
	@Override
	public List<TestRule> findTestRuleName() {
		//定义List<TestRule>对象
		List<TestRule> testRules = new ArrayList<TestRule>();
		//定义TestRule对象
		TestRule testRule = null;
		//定义sql语句
		String sql = "select * from testrule";
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next())
			{
				//得到试卷Id
				String testRuleId = rs.getString("testRuleId");
				//得到试卷名称
				String testRuleName = rs.getString("testRuleName");
				//得到试卷对象
				testRule = new TestRule();
				//将得到的参数放进testRule对象
				testRule.setTestRuleId(testRuleId);
				testRule.setTestRuleName(testRuleName);
				//将testRule对象放进List对象
				testRules.add(testRule);
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
		return testRules;
	}

	/**
	 * 得到记录总条数
	 */
	@Override
	public int getTotalRecords() {
		//定义一个int类型的count
		int count = 0;
		//定义sql
		String sql = "select count(*) from testrule where 1=1";
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//得到结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			if (rs.next()) {
				count = rs.getInt(1);
				return count==0?1:count;
			}else
				return count==0?1:count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return count==0?1:count;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public List<TestRule> findPageRecords(int startIndex, int pageSize) {
		//定义List<TestRule>对象
		List<TestRule> testRules = new ArrayList<TestRule>();
		// 定义一个TestRule对象
		TestRule testRule = null;
		//定义一个List<TestRuleItem>对象
		List<TestRuleItem> testRuleItems = new ArrayList<TestRuleItem>();
		//定义sql1
		String sql1 = "select * from testrule limit "+startIndex*pageSize+","+pageSize+"";
		//建立数据库连接，并执行sql1
		DBConnection connection = new DBConnection();
		//定义rs1结果集
		ResultSet rs1 = connection.executeQuery(sql1);
		try {
			//当试卷表查询成功时
			while(rs1.next())
			{
				//得到试卷Id
				String testruleId = rs1.getString("testRuleId");
				//定义sql2
				String sql2 = "select * from testruleitem where testRuleId='"+testruleId+"'";
				//定义rs2结果集
				ResultSet rs2 = connection.executeQuery(sql2);
				//当试卷类型表查询成功时
				while(rs2.next())
				{
					//得到试题类型Id
					String testtypeId = rs2.getString("testTypeId");
					//倒是试题类型对象
					TestType testType = (TestType) DaoFactory.getInstance().getTestTypeDaoImpl().findById(testtypeId);
					//得到试卷类型对象
					TestRuleItem testRuleItem = new TestRuleItem();
					//向试卷类型对象中放入参数
					testRuleItem.setTestRuleItemId(rs2.getString("testRuleItemId"));
					testRuleItem.setTestRuleItemNum(rs2.getInt("testRuleItemNum"));
					testRuleItem.setTestType(testType);
					//将试卷类型对象放入List对象
					testRuleItems.add(testRuleItem);
				}
				//得到试卷对象
				testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(testruleId);
				
				//testRule = new TestRule(testruleId,rs1.getString("testRuleName"),rs1.getInt("testRuleTime"),testRuleItems);
				testRules.add(testRule);
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
		return testRules;
	}

	@Override
	public boolean judgeTestRule(TestRule testRule) {
		//定义一个boolean类型的flag
		boolean flag = true;
		//创建List<TestRuleItem>对象
		List<TestRuleItem> testRuleItems = testRule.getTestRuleItemList();
		//定义sql
		String sql = "select * from testrule where testRuleName='"+testRule.getTestRuleName()+"'"
				+ " and testRuleTime="+testRule.getTestRuleTime()+""; 
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			if(rs.next())
			{
				for(TestRuleItem testRuleItem : testRuleItems)
				{
					//得到试卷类型id
					String testRuleItemId = testRuleItem.getTestRuleItemId();
					//得到试卷类型总数
					int testRuleItemNum = testRuleItem.getTestRuleItemNum();
					//得到试题类型Id
					String testTypeId = testRuleItem.getTestType().getTestTypeId();
					//定义sql
					String sql1 = "select * from testruleitem where testRuleItemId='"+testRuleItemId+"'";
					System.out.println("testRuleItemId---");
					System.out.println(testRuleItemId);
					ResultSet rs1 = connection.executeQuery(sql1);
					while(rs1.next())
					{
						String testTypeId1 = rs1.getString("testtypeId");
						int testRuleItemNum1 = rs1.getInt("testruleitemNum");
						System.out.println("lala-------------");
						System.out.println(testTypeId1);
						System.out.println(testRuleItemNum1);
						if(testTypeId.equals(testRuleItemId) && testRuleItemNum == testRuleItemNum1)
						{
							flag = false;
						}
					}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
