package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.ExamineeDao;
import com.capgemini.dao.TestTypeDao;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.TestType;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.Config;
import com.capgemini.util.DBConnection;
import com.capgemini.util.GetUUID;

/**
 * 试题类型管理的Dao的实现类
 * 
 * @author chao538
 * @since 2015-11-24
 */
public class TestTypeDaoImpl implements TestTypeDao {

	/**
	 * 删除方法
	 * 
	 * @param传入id来删除
	 * @return返回true则删除成功,返回false则删除失败
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(String id) {

		// 定义一个标记flag初始值为false
		boolean flag = false;
		ExamineeDao examineeDao = DaoFactory.getInstance().getExamineeDaoImpl();
		List<Examinee> examinees = (List<Examinee>) examineeDao.findAll();
		for (Examinee examinee : examinees) {
			if (examinee.getExamineeState().equals(Config.EXAMING)) {
				flag = false;
				return flag;
			}
		}
		// 定义sql语句
		String sql = "delete from testtype where testtypeId='" + id + "'";
		System.out.println("我是TestType的Dao");
		System.out.println(sql);
		// 得到连接并且执行sql
		DBConnection connection = new DBConnection();
		if (connection.executeUpdate(sql) == 1) {
			flag = true;
		}

		// 如果链接没有关闭,则关闭链接
		if (connection != null) {
			connection.close();
		}

		// 返回成功或者失败
		return flag;
	}

	// 未使用
	@Override
	public Object find(String item1, String item2, String item3, String item4) {
		return null;
	}

	// 未使用
	@Override
	public List<?> findAll() {
		// 定义一个List<TestType>的集合
		List<TestType> testTypes = new ArrayList<TestType>();

		// 定义查询语句
		String sql = "select * from testtype";
		System.out.println(sql);
		// 得到链接并且执行SQL语句
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			System.out.println(rs.toString());
			while (rs.next()) {
				TestType testType = new TestType(rs.getString("testtypeId"),
						rs.getString("testtypeName"));
				testTypes.add(testType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		// 返回数据
		return testTypes;
	}

	/**
	 * 新增
	 * 
	 * @param 一个Object类型的对象
	 * @return 是否成功
	 */
	@Override
	public boolean save(Object obj) {

		// 定义一个flag变量
		boolean flag = false;

		// 将传入的Object类型的参数强转成Examinee类型的
		TestType testType = (TestType) obj;

		// 手工设置考生Id通过GetUUID来设置
		testType.setTestTypeId(GetUUID.getUUID().toString());

		// 定义查询SQL语句
		String sql = "insert into testtype" + "	(testtypeId, testtypeName) "
				+ "values" + "	('" + testType.getTestTypeId() + "', " + "'"
				+ testType.getTestTypeName() + "')";

		// 得到链接
		DBConnection connection = new DBConnection();

		// 如果执行成功则flag为true
		if (connection.executeUpdate(sql) == 1) {
			flag = true;
		}

		// 如果链接没有关闭,则关闭链接
		if (connection != null) {
			connection.close();
		}

		// 返回flag
		return flag;
	}

	/**
	 * 修改方法
	 * 
	 * @param 传入一个对象
	 * @return 返回成功或者失败
	 */
	@Override
	public boolean update(Object obj) {

		// 定义一个flag的变量
		boolean flag = false;

		// 将传入的参数强转成TestType
		TestType testType = (TestType) obj;

		// 得到链接并且执行SQL语句
		String sql = "update testtype " + "set " + "	testtypeName='"
				+ testType.getTestTypeName() + "' " + " where  "
				+ "	testtypeId = '" + testType.getTestTypeId() + "'";
		DBConnection connection = new DBConnection();

		// 如果执行成功则给flag定义为true
		if (connection.executeUpdate(sql) == 1) {
			flag = true;
		}

		// 如果链接没有关闭则关闭链接
		if (connection != null) {
			connection.close();
		}

		// 返回flag
		return flag;
	}

	/**
	 * 得到分页数据
	 * 
	 * @param pageNow
	 *            当前页
	 * @param pageSize
	 *            每页的大小
	 * @return 一个TestType的List
	 */
	@Override
	public List<TestType> findPageRecords(int pageNow, int pageSize) {

		// 定义一个List<TestType>的集合
		List<TestType> testTypes = new ArrayList<TestType>();

		// 定义查询语句
		String sql = "select * from testtype limit " + (pageNow * pageSize)
				+ "," + pageSize;
		System.out.println(sql);
		// 得到链接并且执行SQL语句
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			System.out.println(rs.toString());
			while (rs.next()) {
				TestType testType = new TestType(rs.getString("testtypeId"),
						rs.getString("testtypeName"));
				testTypes.add(testType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		// 返回数据
		return testTypes;
	}

	/**
	 * 根据数据库记录得到总页数
	 */
	@Override
	public int getTotalRecords() {

		// 定义sql语句
		String sql = "select count(*) from testtype";

		// 得到链接并执行sql
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		try {
			if (rs.next()) {
				return rs.getInt(1);
			} else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 如果链接没有关闭则关闭链接
			if (connection != null) {
				connection.close();
			}
		}
		return 0;
	}

	/**
	 * 根据试题名称进行查询
	 * 
	 * @param testTypeName
	 *            试题名称
	 */
	@Override
	public TestType find(String testTypeName) {

		// 定义更新sql语句
		String sql = "select * from testtype where testTypeName='"
				+ testTypeName + "'";

		System.out.println(sql);
		// 定义一个List<Examinee>的集合
		TestType testType = null;

		// 得到链接并且执行SQL语句
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				
				String testtypeId = rs.getString("testTypeId");
				String testTypeNam = rs.getString("testTypeName");
				testType = new TestType(testtypeId,testTypeNam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 如果连接不等于空
			if (connection != null) {
				connection.close();
			}
		}

		// 返回查找出来的对象
		return testType;
	}

	/**
	 * 根据id查对象
	 * 
	 * @param id
	 *            传入的id
	 */
	@Override
	public Object findById(String id) {

		// 定义一个sql
		String sql = "select * from testtype where testtypeId='" + id + "'";

		// 定义一个List<Examinee>的集合
		TestType testType = null;

		// 得到链接并且执行SQL语句
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				testType = new TestType(rs.getString("testtypeId"),
						rs.getString("testtypeName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 如果链接不为空则关闭链接
			if (connection != null) {
				connection.close();
			}
		}
		// 返回对象
		return testType;
	}

	/**
	 * 查询所有的试题类型
	 * 
	 * @return 返回一个TestType的集合
	 */
	@Override
	public List<TestType> findAllTestType() {
		List<TestType> testTypes = new ArrayList<TestType>();

		String sql = "select * from testtype";
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			System.out.println(rs.toString());
			while (rs.next()) {
				TestType testType = new TestType(rs.getString("testTypeId"),
						rs.getString("testTypeName"));
				testTypes.add(testType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		// 返回数据
		return testTypes;
	}
}
