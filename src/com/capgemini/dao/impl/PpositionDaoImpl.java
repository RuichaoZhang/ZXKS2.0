package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.capgemini.dao.PpositionDao;
import com.capgemini.domain.Pposition;
import com.capgemini.domain.TestRule;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.DBConnection;
import com.capgemini.util.GetUUID;

/**
 * 职位管理Dao实现层
 * @author BianBian 2015/11/27
 *
 */
public  class PpositionDaoImpl implements PpositionDao{

	/**
	 * 删除职位
	 */
	@Override
	public boolean delete(String id) {
		//定义一个boolean类型的flag对象
		boolean flag = false;
		String sql1 = "select * from examinee where examineeState='1' AND ppositionId='"+id+"'";
		//得到连接并且执行sql
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql1);
		try {
			if(rs.next())
			{
				return flag;
			}
			else {
				//定义sql
				String sql = "delete from pposition where ppositionId='" + id + "'";
				
				//对执行结果进行判断
				if (connection.executeUpdate(sql) == 1) {
					flag=true;
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connection != null) {
			connection.close();
		}
		return flag;
	
	}

	@Override
	public Object find(String item1, String item2, String item3, String item4) {
		return null;
	}

	@Override
	public List<?> findAll() {
		// 定义一个List<Examinee>的集合 
		List<Pposition> ppositions = new ArrayList<Pposition>();
		//定义一个Pposition对象
		Pposition pposition = null;
		// 定义查询语句
		String sql = "select * from pposition";
		// 得到链接并且执行SQL语句 
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				//取出试卷Id
				String testruleId = rs.getString("testRuleId");
				//通过试卷Id找到试卷
				TestRule testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(testruleId);
				//生成职位对象
				pposition  = new Pposition(rs.getString("ppositionId"),rs.getString("ppositionName"),testRule);
				//将职位对象放进List对象
				ppositions.add(pposition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (connection != null) {
				connection.close();
			}
		}
		return ppositions;
	}

	/**
	 * 保存职位
	 */
	@Override
	public boolean save(Object obj) {
		//定义一个boolean类型的flag对象
		boolean flag = false;
		//将传过来的参数强转为Pposition类型
		Pposition  pposition  = (Pposition)obj;
		//通过getUUID来设置职位ID
		pposition.setPpositionId(GetUUID.getUUID().toString());
		//定义sql
		String sql = "insert into pposition "+
		"(ppositionId,ppositionName,testRuleId)"+
			"values('"+pposition.getPpositionId()+"','"+pposition.getPpositionName()
			+"','"+pposition.getTestrule().getTestRuleId()+"')";
		//连接数据库并执行sql
		DBConnection connection = new DBConnection();
		//对执行结果进行判断
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
	 * 修改职位
	 */
	@Override
	public boolean update(Object obj) {
//		//定义一个flag
//		boolean flag = false;
//		//将传过来的obj强转为Pposition
//		Pposition pposition = (Pposition) obj;
//		//定义sql
//		String sql = "update pposition set "+
//		"ppositionName='"+pposition.getPpositionName()+"',testRuleId='"+
//		pposition.getTestrule().getTestRuleId()+"' where ppositionId='"+
//		pposition.getPpositionId()+"'";
//		//连接数据库并执行sql
//		DBConnection connection = new DBConnection();
//		//对执行结果进行判断
//		if(connection.executeUpdate(sql) == 1)
//		{
//			flag = true;
//		}
//		if(connection != null)
//		{
//			connection.close();
//		}
//		return flag;
		//定义一个flag
				boolean flag = false;
				//将传过来的obj强转为Pposition
				Pposition pposition = (Pposition) obj;
				
				String sql1 = "select * from examinee where examineeState='1' AND ppositionId='"+
				pposition.getPpositionId()+"'";
				//连接数据库并执行sql
				DBConnection connection = new DBConnection();
				ResultSet rs = connection.executeQuery(sql1);
				try {
					if(rs.next())
					{
						return flag;
					}
					else {
						//定义sql
						String sql = "update pposition set "+
						"ppositionName='"+pposition.getPpositionName()+"',testRuleId='"+
						pposition.getTestrule().getTestRuleId()+"' where ppositionId='"+
						pposition.getPpositionId()+"'";
						//对执行结果进行判断
						if(connection.executeUpdate(sql) == 1)
						{
							flag = true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(connection != null)
				{
					connection.close();
				}
				return flag;

	}

	/**
	 * 分页查询
	 */
	@Override
	public List<Pposition> findPageRecords(int startIndex, int pageSize) {
		// 定义一个List<Examinee>的集合 
		List<Pposition> ppositions = new ArrayList<Pposition>();
		//定义一个Pposition对象
		Pposition pposition = null;
		// 定义查询语句
		String sql = "select * from pposition limit " + startIndex*pageSize + "," + pageSize + "";
		// 得到链接并且执行SQL语句 
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				//取出试卷Id
				String testruleId = rs.getString("testRuleId");
				//通过试卷Id找到试卷
				TestRule testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(testruleId);
				//生成职位对象
				pposition  = new Pposition(rs.getString("ppositionId"),rs.getString("ppositionName"),testRule);
				//将职位对象放进List对象
				ppositions.add(pposition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (connection != null) {
				connection.close();
			}
		}
		return ppositions;
	}

	/**
	 * 得到全部记录数
	 */
	@Override
	public int getTotalRecords(String ppositionName) {
		//定义一个int类型的count
		int count = 0;
		//定义一个可变长的sb
		StringBuilder sb = new StringBuilder("select count(*) from pposition where 1=1");
		//判断是否有条件
		if(!(ppositionName == null || "".equals(ppositionName)))
		{
			sb.append(" and ppositionName='"+ppositionName+"'");
		}
		//定义sql
		String sql = sb.toString();
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
	 * 通过职位Id查询
	 */
	@Override
	public Object findById(String testId) {
		//定义一个Pposition对象
		Pposition pposition  = null;
		//定义sql
		 String sql = "select * from pposition where ppositionId='"+testId+"'";
		 //连接数据库并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) 
			{
				//得到试卷Id
				String testruleId = rs.getString("testRuleId");
				//通过试卷Id得到试卷对象
				TestRule testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(testruleId);
				//得到职位对象
				pposition  = new Pposition(rs.getString("ppositionId"),rs.getString("ppositionName"),testRule);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if (connection != null) {
				connection.close();
			}
		}
		return pposition;
		}

	/**
	 * 通过职位名称查询
	 */
	@Override
	public List<Pposition> findByPpositionName(String ppositionName,int startIndex, int pageSize) {
			//定义sql
		String sql = "select * from pposition,testrule "
				+ "where pposition.testRuleId = testrule.testRuleId "
				+ "and ppositionName='" + ppositionName + "' limit " + startIndex*pageSize + "," + pageSize + "";
			//定义List<Pposition>对象
			List<Pposition> ppositions = new ArrayList<Pposition>();
			//定义Pposition对象
			Pposition pposition = null;
			//建立数据库连接，并执行sql
			DBConnection connection = new DBConnection();
			//定义结果集
			ResultSet rs = connection.executeQuery(sql);
			try {
				while(rs.next())
				{
					//得到试卷Id
					String testruleId = rs.getString("testRuleId");
					//通过试卷Id得到试卷对象
					TestRule testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(testruleId);
					//得到职位对象
					pposition  = new Pposition(rs.getString("ppositionId"),rs.getString("ppositionName"),testRule);
					//将职位对象放进List对象
					ppositions.add(pposition);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if (connection != null) {
					connection.close();
				}
			}
			
			return ppositions;
	}

	/**
	 * 查询试卷
	 */
	@Override
	public List<Pposition> findTestRule() {
		//定义TestRule对象
		TestRule testRule = null;
		//定义Pposition对象
		Pposition pposition = null;
		//定义List<Pposition>对象
		List<Pposition> ppositions = new ArrayList<Pposition>();
		// 定义sql
		String sql = "select testRuleId,testRuleName from testrule";
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next())
			{
				//得到试卷Id
				String testruleId = rs.getString("testRuleId");
				//得到试卷名称
				String testruleName = rs.getString("testRuleName");
				//生成试卷对象
				testRule = new TestRule();
				//将参数放进试卷对象
				testRule.setTestRuleId(testruleId);
				testRule.setTestRuleName(testruleName);
				//生成职位对象
				pposition = new Pposition();
				//将试卷对象放进职位对象
				pposition.setTestrule(testRule);
				//将职位对象放进List对象
				ppositions.add(pposition);
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
		return ppositions;
	}

	/**
	 * 查询职位表信息
	 */
	@Override
	public List<Pposition> findPposition() {
		//定义sql
		String sql = "SELECT * FROM pposition";
		//定义Pposition对象
		Pposition pposition = null;
		//定义List<Pposition>
		List<Pposition> ppositions = new ArrayList<Pposition>();
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next())
			{
				//得到职位Id
				String ppositionId = rs.getString("ppositionId");
				//得到职位名称
				String ppositionName = rs.getString("ppositionName");
				//生成职位对象
				pposition = new Pposition();
				//将参数放进职位对象
				pposition.setPpositionId(ppositionId);
				pposition.setPpositionName(ppositionName);
				//将职位对象放进List对象
				ppositions.add(pposition);
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
		
		return ppositions;
	}

	/**
	 * 通过职位名称查询
	 */
	@Override
	public List<Pposition> findToPposition(String ppositionName) {
		// 定义sql
		String sql = "select * from pposition,testrule "
				+ "where pposition.testRuleId = testrule.testRuleId "
				+ "and ppositionName='" + ppositionName +"";
		//定义List<Pposition>对象
		List<Pposition> ppositions = new ArrayList<Pposition>();
		//定义试卷对象
		TestRule testRule = null;
		// 定义一个Pposition对象
		Pposition pposition = null;
		// 得到链接并且执行SQL语句 
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		if(rs != null)
			{
				try{
					if(rs.next()!= false){
						//得到试卷对象
						testRule = (TestRule) DaoFactory.getInstance().getTestRuleDaoImpl().findById(rs.getString("testRuleId"));
						//得到职位对象
						pposition = new Pposition(rs.getString("ppositionId"), rs.getString("ppositionName"), testRule);
						//将职位对象放进List对象
						ppositions.add(pposition);
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally{
						
						if (connection != null) {
							connection.close();
						}
					}
					return ppositions;
				}else {
					return ppositions;
				}
		}
	/**
	 * 查询职位名称
	 */
	public List<Pposition> findPpositionName(){
		//定义sql
		String sql = "select ppositionName from pposition";
		//定义List<Pposition>对象
		List<Pposition> ppositions = new ArrayList<Pposition>();
		//定义Pposition对象
		Pposition pposition = null;
		//建立数据库连接，并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next())
			{
				//得到职位名称
				String ppositionName = rs.getString("ppositionName");
				//生成职位对象
				pposition = new Pposition();
				//将参数放进职位对象
				pposition.setPpositionName(ppositionName);
				//将职位对象放进List对象
				ppositions.add(pposition);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection != null) {
				connection.close();
			}
		}
		return ppositions;
	}

	/**
	 * 判断职位
	 */
	@Override
	public boolean judgePposition(String ppositionName) {
		//定义一个boolean类型的flag
		boolean flag = false;
		//定义sql
		String sql = "select * from pposition where ppositionName='"+ppositionName+"'"; 
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
//		System.out.println("rs.next()---------");
//		try {
//			System.out.println(rs.next());
//			System.out.println(rs.next());
//			System.out.println(rs.next());
//			System.out.println(rs.next());
//			System.out.println(rs.next());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		try {
			if(rs.next() == false)
			{
				flag = true;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection != null) {
				connection.close();
			}
		}
		System.out.println("flag-----------");
		System.out.println(flag);
		return flag;
	}

	@Override
	public Pposition findByPposition(String ppositionName) {
		String sql = "select * from pposition where ppositionName='" + ppositionName + "'";
		DBConnection dbConnection = new DBConnection();
		Pposition pposition = null;
		ResultSet rs = dbConnection.executeQuery(sql);
		try {
			if(rs.next()){
				pposition = new Pposition();
				pposition.setPpositionId(rs.getString("ppositionId"));
				pposition.setPpositionName(rs.getString("ppositionName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(dbConnection != null){
				dbConnection.close();
			}
		}
		return pposition;
	}
}
