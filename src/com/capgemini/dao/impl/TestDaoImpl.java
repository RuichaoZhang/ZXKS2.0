package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.dao.TestDao;
import com.capgemini.dao.TestItemDao;
import com.capgemini.dao.TestTypeDao;
import com.capgemini.domain.Test;
import com.capgemini.domain.TestItem;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.DBConnection;
import com.capgemini.util.GetUUID;

/**
 * 试题管理的Dao实现类
 * @author wanghuan
 * @since 2015-11-25
 */
public class TestDaoImpl implements TestDao {
	private TestItemDao testItemDao = DaoFactory.getInstance().getTestItemDaoImpl();
	public TestDaoImpl() {
	}
	/**
	 * 删除方法
	 * @param 通过id删除对象
	 * @return返回true则删除成功,返回false则删除失败
	 *  
	 */
	@Override
	public boolean delete(String id) {
		boolean flag = false;

		String sql = "delete from test where testId='" + id + "'";

		DBConnection connection = new DBConnection();
		int count = connection.executeUpdate(sql);
		if (count == 1) {
			flag = true;
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	}
	/**
	 *  修改方法
	 *  @param 为其传入一个试题对象，对试题进行修改
	 *  @return 返回true则修改成功,返回false则修改失败
	 */

	@Override
	public boolean update(Object obj) {
		boolean flag = false;
		Test test = (Test)obj;
		System.out.println(test.getTestType().getTestTypeId());
		String sql = "update test set " +
				" testSubject = '"+test.getTestSubject()+"'," +
				" testScore = "+test.getTestScore()+"," +
				" testTypeId = '"+test.getTestType().getTestTypeId()+"'" +
				" where testId = '"+test.getTestId()+"'";
		
		System.out.println(sql+"88888888888888888");
		DBConnection connection = new DBConnection();
		if(connection.executeUpdate(sql)==1){
			flag = true;
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	}
	/**
	 * 保存方法
	 * @param 传入一个试题类型的对象
	 * @return 返回true则存入成功，返回false则存入失败
	 */

	@Override
	public boolean save(Object obj) {
		boolean flag = false;
		Test test = (Test)obj;
		String sql = "insert test values(" +
				"'"+test.getTestId()+"'," +
				"'"+test.getTestSubject()+"'," +
				"'"+test.getTestType().getTestTypeId()+"'," +
						""+test.getTestScore()+")";
		
		DBConnection connection = new DBConnection();
		if(connection.executeUpdate(sql)==1){
			flag = true;			
		}
		if (connection != null) {
			connection.close();
		}		
		return flag;
	}	
	
	/**
	 * 根据试题类型查询试题集合
	 * @param 传入一个试题类型的名称
	 * @return 返回一个试题的集合
	 * 考试系统中用到此方法。																																																																																																																																																																																									
	 */
	
	@Override
	public List<Test> findByTestType(TestType testType) {
		ArrayList<Test> list = new ArrayList<Test>();
		
		String sql = "select * from test where testTypeId = '"+testType.getTestTypeId()+"'";
		DBConnection connection = new DBConnection();
		Test test ;
		try {
			ResultSet rs = connection.executeQuery(sql);
			if(rs.next()){
				test = new Test();
				test.setTestId(rs.getString("testId"));
				test.setTestSubject(rs.getString("testSubject"));
				test.setTestScore(rs.getInt("testScore"));
				test.setTestType(testType);
				list.add(test);
				
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

	/**
	 *获取试题的数量
	 *@return 返回试题的数量 
	 */
	@Override
	public int getTotalRecords(String testSubject, TestType testType,
			String testItemTrue) {
		int num = 0;
		StringBuilder sb;
		sb = new StringBuilder(
				"select COUNT(DISTINCT test.testId) " +
				 "from test,testitem,testtype where "
				+ "test.testId = testitem.testId and " +
				"test.testTypeId = testtype.testTypeId");

		
		if (!(testSubject == null || "".equals(testSubject))) {
			sb.append(" and test.testSubject like '%" + testSubject + "%'");
		}		
		if (!(testType == null)) {
			sb.append(" and test.testTypeId = '" + testType.getTestTypeId()	+ "'");
		}		
		if (!(testItemTrue == null || "".equals(testItemTrue))) {
			sb.append(" and testitem.testItemContent like '%" + testItemTrue + "%'");
		}	
		String sql = sb.toString();
		System.out.println(sql+"///////////////");
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next()){
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (connection != null) {
			connection.close();
		}
		return num;
	}
	
	

	@Override
	public int getTotalRecords() {
		int num = 0;
		String sql = "select count(*) from test";
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next()){
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (connection != null) {
			connection.close();
		}
		return num;
	}

	/**
	 * 根据参数查询得到试题集合
	 * @param testSubject 参数试题题目（模糊查询）
	 * @param testType 参数试题类型
	 * @param startIndex 参数页数
	 * @param pageSize 参数一页最多的显示记录
	 * @return 返回一个试题对象的集合
	 */
	@Override
	public List<Test> findByLikeTest(String testSubject,TestType testType,
			String testItemTrue,int startIndex, int pageSize) {
		
		ArrayList<Test> list = new ArrayList<Test>();
		
		StringBuilder sb;

		sb = new StringBuilder(
				"select DISTINCT test.testId,test.testSubject,test.testScore,test.testTypeId,testtype.testTypeName " +
				 "from test,testitem,testtype where "
				+ "test.testId = testitem.testId and " +
				"test.testTypeId = testtype.testTypeId");

		
		if (!(testSubject == null || "".equals(testSubject))) {
			sb.append(" and test.testSubject like '%" + testSubject + "%'");
		}		
		//此处出问题了
		//System.out.println((testType == null)+"////");//结果时true
		if (!(testType == null)) {
			sb.append(" and test.testTypeId = '" + testType.getTestTypeId()	+ "'");
		}		
		if (!(testItemTrue == null || "".equals(testItemTrue))) {
			sb.append(" and testitem.testItemContent like '%" + testItemTrue + "%'");
		}		
		sb.append(" limit " + startIndex * pageSize + "," + 4 + "");
		String sql = sb.toString();

		DBConnection connection = new DBConnection();
		
		Test test ;
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				test = new Test();
				test.setTestId(rs.getString("test.testId"));
				test.setTestSubject(rs.getString("test.testSubject"));
				test.setTestScore(rs.getInt("test.testScore"));
				test.setTestType(new TestType(rs.getString("test.testTypeId"),rs.getString("testtype.testTypeName")));
				list.add(test);
				
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
	
	
	/**
	 *查询所有的试题
	 * @return 返回试题对象的集合
	 */
	@Override
	public List<Test> findAllTest(int startIndex, int pageSize) {
		List<Test> tests = new ArrayList<Test>();

		//多表连接，将试题类型名称查出来
		String sql = "select test.testId,test.testSubject,test.testTypeId,test.testScore,testtype.testTypeName" +
				" from test,testtype where test.testTypeId = testtype.testTypeId  limit " + startIndex * pageSize + "," + pageSize + "";
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				Test test = new Test(rs.getString("test.testId"),rs.getString("test.testSubject"),
						new TestType(rs.getString("test.testTypeId"),rs.getString("testtype.testTypeName")),
						rs.getInt("testScore"));
				tests.add(test);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		// 返回数据
		return tests;
	}
	

	/**
	 * 根据试题的编号查询试题
	 * @param testId
	 * @return 返回一个试题的对象
	 */
	@Override
	public Test findByTestId(String testId) {
		Test test = new Test();
		String sql = "select * from test,testtype where test.testTypeId = testtype.testTypeId and testId = '"+testId+"'";
		DBConnection connection = new DBConnection();
		
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next()){
				test.setTestId(testId);
				test.setTestSubject(rs.getString("test.testSubject"));
				test.setTestScore(Integer.parseInt(rs.getString("test.testScore")));
				test.setTestType(new TestType(rs.getString("testtype.testTypeId"),rs.getString("testtype.testTypeName")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}			
		}		
		return test;
	}
	
	/**
	 * 生成试题
	 * @param testRules
	 * @return
	 */
	@Override
	public List<Test> generateTest(List<TestRuleItem> testRuleItems) {
		ArrayList<Test> tests = new ArrayList<Test>();
		TestRuleItem testRuleItem;
		Iterator<TestRuleItem> it = testRuleItems.iterator();
		DBConnection connection = new DBConnection();
		while(it.hasNext()){
			testRuleItem = it.next();
			String sql = "select * from test,testtype where test.testTypeId = testtype.testTypeId and " +
						" test.testTypeId = '" + testRuleItem.getTestType().getTestTypeId() +
						"' order by rand() limit 0,"+testRuleItem.getTestRuleItemNum()+"";
			ResultSet rs = connection.executeQuery(sql);
			try {
				Test test ;
				while(rs.next()){
					TestType testType = new TestType(rs.getString("testtype.testTypeId"),rs.getString("testtype.testTypeName"));
					test = new Test(rs.getString("test.testId"),rs.getString("test.testSubject"),testType,Integer.parseInt(rs.getString("test.testScore")));
					ArrayList<TestItem> testItems = (ArrayList<TestItem>) testItemDao.findByTestId(test.getTestId());
					test.setTestItemList(testItems);
					tests.add(test);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (connection != null) {
			connection.close();
		}	
		return tests;
	}

	//未使用
	@Override
	public Object find(String item1, String item2, String item3, String item4) {
		return null;
	}

	//未使用
	@Override
	public Object findById(String id) {
		return null;
	}

	//未使用
	@Override
	public List<?> findAll() {
		return null;
	}
	
	/**
	 * 批量插入试题
	 */
	@Override
	public void insertAllTest(List<Test> tests) {
		
		List<String> testSqls = new ArrayList<String>();
		List<TestItem> testItems = new ArrayList<TestItem>();
		List<String> testItemSqls = new ArrayList<String>();
		
		//遍历试题集合得到插入试题表的sql
		for (Test test: tests) {
			String testId = GetUUID.getUUID();
			String testName = test.getTestType().getTestTypeName();
			
			TestTypeDao testTypeDao = DaoFactory.getInstance().getTestTypeDaoImpl();
			
			TestType testType = testTypeDao.find(testName);
			String testSql = new String("insert test values(" +
					"'" + testId + "'," +
					"'" + test.getTestSubject() + "'," +
					"'" + testType.getTestTypeId() + "'," +
							""+test.getTestScore() + ")");
			
			//得到试题里面的试题的条目的集合
			testItems = test.getTestItemList();
			
			//遍历集合得到插入试题条目的sql
			for (TestItem testItem : testItems) {
				System.out.println("我是testItem-----------------------------------");
				String testItemId = GetUUID.getUUID(); 
				String testItemSql = new String("insert testitem values(" + "'"
						+ testItemId + "'," + "'"
						+ testItem.getTestItemContent() + "'," + "'"
						+ testItem.getTestItemState() + "'," + "'"
						+ testId + "')");
				testItemSqls.add(testItemSql);
			}
			testSqls.add(testSql);
		}
		
		//得到链接
		DBConnection connection = new DBConnection();
		
		//执行sql
		connection.executeBatch(testSqls);
		connection.executeBatch(testItemSqls);
		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public Test findByTestSubject(String testSubject) {
		Test test = null;
		String sql = "select * from test,testtype where test.testTypeId = testtype.testTypeId and testSubject = '"+testSubject+"'";
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next()){
				test = new Test();
				test.setTestId(rs.getString("test.testId"));
				test.setTestSubject(testSubject);
				test.setTestScore(Integer.parseInt(rs.getString("test.testScore")));
				test.setTestType(new TestType(rs.getString("testtype.testTypeId"),rs.getString("testtype.testTypeName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}			
		}		
		return test;
	}

	@Override
	public int findByTestTypeName(String testTypeName) {
		int num = 0;
		String testTypeId = null;
		
		String sqlTestTypeId = "select testtypeId from testtype where testtypeName='"+testTypeName+"'";
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println(sqlTestTypeId);
		DBConnection dbConnection = new DBConnection();
		
		ResultSet rs = dbConnection.executeQuery(sqlTestTypeId);
		try {
			if(rs.next()){
				testTypeId = rs.getString(1);
				System.out.println(testTypeId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select count(*) from test where testtypeId='"+testTypeId+"'";
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println(sql);
		ResultSet rss = dbConnection.executeQuery(sql);
		try {
			if(rss.next()){
				num = rss.getInt(1);
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
				System.out.println(num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
