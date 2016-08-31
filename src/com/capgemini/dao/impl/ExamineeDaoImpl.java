package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.dao.ExamineeDao;
import com.capgemini.dao.PpositionDao;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.Pposition;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.util.Config;
import com.capgemini.util.DBConnection;
import com.capgemini.util.GetUUID;

/**
 * 考生Dao的实现类
 * @author chao538
 *
 */
public class ExamineeDaoImpl implements ExamineeDao{
	
	PpositionDao ppositionDao = DaoFactory.getInstance().getPpositionDaoImpl();
	
	/**
	 * 定义一个名为ENTER的常量,在底下拼SQL时需要用到
	 */
	final static String  ENTER = "\n";
	
	/**
	 *@param examineeName 考生姓名
	 *@param examineePassword 考生密码
	 *@return 一个Examinee对象
	 */
	@Override
	public Examinee findByExamineeTelephoneAndExamineePassword(String examineeTelephone, String examineePassword) {
		
		/**声明一个Examinee对象*/  
		Examinee examinee = null;
		
		
		DBConnection connection = new DBConnection();

		/**定义SQL查询语句*/ 
		String sql = "select * from examinee where" +
				" examineeTelephone = '" + examineeTelephone + "' and examineePassword='"+ examineePassword +"'" +
				" and examineeState='"+ Config.NOEXAMING +"'";
		System.out.println("我是Dao");
		System.out.println(sql);
		/**声明一个Examinee对象*/  
		try {
			ResultSet rs = connection.executeQuery(sql);
			System.out.println("我是rs");
			System.out.println("**********************************");
			if(rs.next()) {
				Pposition pposition = new Pposition();
				pposition = (Pposition) ppositionDao.findById(rs.getString("ppositionId"));
				examinee = new Examinee(rs.getString("examineeId"),
						rs.getString("examineeName"),
						rs.getString("examineePassword"),
						rs.getString("examineeTelephone"),
						rs.getString("examineeState"),
						rs.getString("examineeSex"),
						rs.getString("examineeSchool"),
						rs.getString("examineeEmail"), pposition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return examinee;
	}
	
	/**
	 * 查询所有的考生
	 */
	@Override
	public List<?> findAll() {
		
		/**定义一个List<Examinee>的集合*/ 
		List<Examinee> examinees = new ArrayList<Examinee>();
		
		/**定义查询语句*/
		String sql = "select * from examinee,pposition where examineeState='已通过' and examinee.ppositionId=pposition.ppositionId ";

		/**得到链接并且执行SQL语句*/ 
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				
				Pposition pposition = new Pposition();
				pposition.setPpositionId(rs.getString("pposition.ppositionId"));
				pposition.setPpositionName(rs.getString("pposition.ppositionName"));
				
				Examinee examinee = new Examinee();
				examinee.setExamineeId(rs.getString("examinee.examineeId"));
				examinee.setExamineeName(rs.getString("examinee.examineeName")); 
				examinee.setExamineePassword(rs.getString("examinee.examineePassword")); 
				examinee.setExamineeTelephone(rs.getString("examinee.examineeTelephone")); 
				examinee.setExamineeState(rs.getString("examinee.examineeState"));
				examinee.setExamineeSex(rs.getString("examinee.examineeSex"));
				examinee.setExamineeSchool(rs.getString("examinee.examineeSchool"));
				examinee.setExamineeEmail(rs.getString("examinee.examineeEmail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return examinees;
	}

	/**
	 * 定义保存方法
	 * @param 传入一个Object类型的参数,可以在方法内部进行强转
	 */
	@Override
	public boolean save(Object obj) {
		boolean flag = false; 
		
		/**将传入的Object类型的参数强转成Examinee类型的*/
		Examinee examinee = (Examinee)obj;
		
		/** 手工设置考生Id通过GetUUID来设置*/ 
		examinee.setExamineeId(GetUUID.getUUID().toString());
		
		/** 定义查询SQL语句 */  
		String sql = 
			"insert into examinee" +
			"	(examineeId, examineeName, examineeTelephone, examineeState, examineeSex, examineeSchool, examineeEmail, examineePassword, ppositionId) " +
			"values" +
			"	('" + examinee.getExamineeId() + "', " +
					"'" + examinee.getExamineeName() + "', " +
					"'" + examinee.getExamineeTelephone() + "', " + 
					"'" + Config.NOEXAMING + "', " +
					"'" + examinee.getExamineeSex() + "', " +
					"'" + examinee.getExamineeSchool() + "', " + 
					"'" + examinee.getExamineeEmail() + "', " +
					"'" + Config.PASSWORD + "', " +
					"'" + examinee.getPposition().getPpositionId() + "')";
		
		System.out.println(sql);
		DBConnection connection = new DBConnection();
		int num = connection.executeUpdate(sql);
		if(num == 1){
			flag = true;
		}
		connection.close();
		return flag;
	}
	
	/**
	 * 修改方法
	 * @param 传入Object类型的参数在方法内部强转成方法需要的类型
	 * @return 返回boolean类型,成功返回true,失败返回false
	 * @throws ExceptionMessege 
	 */
	@Override
	public boolean updateDate(Object obj) {

		// 定义一个flag的变量
		boolean flag = false;

		// 将传入的参数强转成 Examinee
		Examinee examinee = (Examinee) obj;

		// 得到链接并且执行SQL语句
		String sql = "update examinee " + "set " + "examineeName='"
				+ examinee.getExamineeName() + "' ," 
				+"	examineeSex='"+ examinee.getExamineeSex() + "' ," 
				+"	examineeSchool='"+ examinee.getExamineeSchool() + "', " 
				+"	ppositionId='"+ examinee.getPposition().getPpositionId()+ "' ,"
				+"	examineeState='"+ examinee.getExamineeState() + "' ,"
				+"	examineeTelephone='"+ examinee.getExamineeTelephone() + "', " 
				+"	examineeEmail='"+ examinee.getExamineeEmail() + "' " 
				+"	examtime='"+ examinee.getExamTime() + "' " 
				+ " where  "
				+ "	examineeId = '" + examinee.getExamineeId() + "'";
		
		System.out.println(sql);
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
	 * 修改方法
	 * @param 传入Object类型的参数在方法内部强转成方法需要的类型
	 * @return 返回boolean类型,成功返回true,失败返回false
	 * @throws ExceptionMessege 
	 */
	@Override
	public boolean update(Object obj) {

		// 定义一个flag的变量
		boolean flag = false;

		// 将传入的参数强转成 Examinee
		Examinee examinee = (Examinee) obj;

		// 得到链接并且执行SQL语句
		String sql = "update examinee " + "set " + "examineeName='"
				+ examinee.getExamineeName() + "' ," 
				+"	examineeSex='"+ examinee.getExamineeSex() + "' ," 
				+"	examineeSchool='"+ examinee.getExamineeSchool() + "', " 
				+"	ppositionId='"+ examinee.getPposition().getPpositionId()+ "' ,"
				+"	examineeState='"+ examinee.getExamineeState() + "' ,"
				+"	examineeTelephone='"+ examinee.getExamineeTelephone() + "', " 
				+"	examineeEmail='"+ examinee.getExamineeEmail() + "' " 
				+ " where  "
				+ "	examineeId = '" + examinee.getExamineeId() + "'";
		
		System.out.println(sql);
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
	 * 模糊查询方法
	 * @param 传入一
	 */
	@Override
	public Object find(String item1, String item2, String item3) {
		
		/** 定义一个集合 */
		List<Examinee> examinees = new ArrayList<Examinee>();
		
		/** 定义一个可变长的SQL*/
		StringBuffer sbSql = new StringBuffer("select * from examinee,pposition where 1=1" +
				" and examineeState='已通过'" +
				" and examinee.ppositionId=pposition.ppositionId " + ENTER);
		
		/** 判断传进来的参数的是否为空从而决定sql如何拼*/
		if(item1 != "" && item1 != null){
			sbSql.append("and examineeName like '%" + item1 + "%'" + ENTER);
		}
		if(item2 != "" && item2 != null){
			sbSql.append("and examineeSex like '%" + item2 + "%'" + ENTER);
		}
		if(item3 != "" && item3 != null){
			sbSql.append("and ppositionId like '%" + item3 + "%'" + ENTER);
		}
		
		/**将sbsql转换成String*/
		String sql = sbSql.toString();
		
		System.out.println(sql);
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				Pposition pposition = new Pposition();
				pposition.setPpositionId(rs.getString("ppositionId"));
				pposition.setPpositionName(rs.getString("ppositionName"));
				
				Examinee examinee1 = new Examinee(
						
						rs.getString("examineId"),
						rs.getString("examineeName"), 
						rs.getString("examineePassword"), 
						rs.getString("examineeTelephone"), 
						rs.getString("examineeState"),
						rs.getString("examineeSex"),
						rs.getString("examineeSchool"),
						rs.getString("examineeEmail"),
						pposition);
				examinees.add(examinee1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return examinees;
	}
	
	/**
	 * 定义删除方法
	 * @param id 传入id来删除
	 */
	@Override
	public boolean delete(String id) {
		boolean flag = false;
		/**定义删除的sql*/
		String sql = "delete from examinee where examineeId='" + id + "' and examineeState not in ('"+ Config.EXAMING +"')";
		System.out.println(sql);
		
		/** 得到连接并且执行sql */
		DBConnection connection = new DBConnection();
		if (connection.executeUpdate(sql) == 1) {
			flag=true;
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
	/**
	 * 根据开始的页码得到
	 */
	@Override
	public List<Examinee> findPageRecords(int startIndex, int pageSize) {
		System.out.println("我的dao。---------");
		
		/** 定义一个List<Examinee>的集合 */ 
		List<Examinee> examinees = new ArrayList<Examinee>();
		
		/** 定义查询语句 */
		String sql = "SELECT * FROM examinee ,pposition WHERE  examinee.ppositionId=pposition.ppositionId  limit " + startIndex*pageSize + "," + pageSize + "";
		
		System.out.println(sql);
		/**  得到链接并且执行SQL语句 */ 
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				Pposition pposition = new Pposition();
				
				pposition.setPpositionId(rs.getString("ppositionId"));
				pposition.setPpositionName(rs.getString("ppositionName"));
				
				Examinee examinee = new Examinee();
				examinee.setExamineeId(rs.getString("examineeId"));
				examinee.setExamineeName(rs.getString("examineeName")); 
				examinee.setExamineePassword(rs.getString("examineePassword")); 
				examinee.setExamineeTelephone(rs.getString("examineeTelephone")); 
				examinee.setPpositionId(pposition);
				examinee.setExamineeState(rs.getString("examineeState"));
				examinee.setExamineeSex(rs.getString("examineeSex"));
				examinee.setExamineeSchool(rs.getString("examineeSchool"));
				examinee.setExamineeEmail(rs.getString("examineeEmail"));
				
				examinees.add(examinee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return examinees;
	}

	@Override
	public int getTotalRecords(String examineeName, String examineeSex,String ppositionName) {
         int count = 0;
		
		/**定义查询语句*/
     	StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM examinee,pposition WHERE examinee.ppositionId = pposition.ppositionId");
		
		if(!(examineeName == null || "".equals(examineeName))){
			sql.append(" and examineeName like '%"+examineeName+"%'");
		}
		if(!(examineeSex == null || "".equals(examineeSex))){
			sql.append(" and examineeSex = '"+examineeSex+"'");
		}
		if(!(ppositionName == null || "".equals(ppositionName))){
			sql.append(" and ppositionName = '"+ppositionName+"'");
		}

		
		System.out.println(sql.toString());
		
		//得到链接并执行sql
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql.toString());
		try {
			if (rs.next()) {
				count = rs.getInt(1); 
				return count==0?1:count;
			}else
				return count==0?1:count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			//如果链接没有关闭则关闭链接
			if(connection != null){
			connection.close();
			}
		}
		return count==0?1:count;
		
		
	/*	String sql = "select count(*) from examinee";
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		
		try {
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return 0;
		*/
	}

	@Override
	public Object findById(String id) {
		// 定义一个sql
		String sql = "select * from examinee,pposition where examinee.ppositionId = pposition.ppositionId and examineeId='"
				+ id + "'";

		// 定义一个List<Examinee>的集合
		Examinee examinee = null;

		System.out.println(sql);
		// 得到链接并且执行SQL语句
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				Pposition pposition = new Pposition();
				pposition.setPpositionId(rs.getString("pposition.ppositionId"));
				pposition.setPpositionName(rs
						.getString("pposition.ppositionName"));
				examinee = new Examinee(rs.getString("examinee.examineeId"),
						rs.getString("examinee.examineeName"),
						rs.getString("examinee.examineePassword"),
						rs.getString("examinee.examineeTelephone"),
						rs.getString("examinee.examineeState"),
						rs.getString("examinee.examineeSex"),
						rs.getString("examinee.examineeSchool"),
						rs.getString("examinee.examineeEmail"), pposition);
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
		return examinee;
	}

	@Override
	public List<Examinee> findByLike(String examineeName, String examineeSex,
			String ppositionName, int startIndex, int pageSize) {
		/** 定义一个List<Grade>的集合 */
		List<Examinee> examinees = new ArrayList<Examinee>();

		/** 定义查询语句 */
		StringBuilder sb = new StringBuilder(
				"SELECT * FROM examinee,pposition WHERE examinee.ppositionId = pposition.ppositionId");

		if(!(examineeName == null || "".equals(examineeName))){
			sb.append(" and examineeName like '%"+examineeName+"%'");
		}
		if(!(examineeSex == null || "".equals(examineeSex))){
			sb.append(" and examineeSex = '"+examineeSex+"'");
		}
		if(!(ppositionName == null || "".equals(ppositionName))){
			sb.append(" and ppositionName = '"+ppositionName+"'");
		}
		
		
		sb.append(" limit " + startIndex*pageSize + "," + pageSize + "");
		String sql = sb.toString(); 	
		System.out.println(sql);
		
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {

				Pposition pposition = new Pposition();
				pposition.setPpositionId(rs.getString("ppositionId"));
				pposition.setPpositionName(rs.getString("ppositionName"));
				
				Examinee examinee = new Examinee();
				examinee.setExamineeId(rs.getString("examineeId"));
				examinee.setExamineeName(rs.getString("examineeName")); 
				examinee.setExamineePassword(rs.getString("examineePassword")); 
				examinee.setExamineeTelephone(rs.getString("examineeTelephone")); 
				examinee.setPpositionId(pposition);
				examinee.setExamineeState(rs.getString("examineeState"));
				examinee.setExamineeSex(rs.getString("examineeSex"));
				examinee.setExamineeSchool(rs.getString("examineeSchool"));
				examinee.setExamineeEmail(rs.getString("examineeEmail"));
				
				examinees.add(examinee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return examinees;
	}
	
	/**
	 * 批量插入试题
	 */
	@Override
	public void insertAllExaminee(List<Examinee> examinees) {
		
		List<String> examineeSqls = new ArrayList<String>();
		System.out.println("----------------------------------" + new Date().toString());
		System.out.println("我是DAO开始的时候" + new Date().toString());
		//遍历试题集合得到插入试题表的sql
		for (Examinee examinee: examinees) {
			String examineeId = GetUUID.getUUID();
			String ppositionName = examinee.getPposition().getPpositionName();
			PpositionDao ppositionDao= DaoFactory.getInstance().getPpositionDaoImpl();
			Pposition pposition = ppositionDao.findByPposition(ppositionName);
			String examineeSql = new String("insert into examinee" +
					"	(examineeId, examineeName, examineeTelephone, examineeState, examineeSex, examineeSchool, examineeEmail, examineePassword, ppositionId) " +
					"values" +
					"	   ( '" + examineeId + "', " +
							"'" + examinee.getExamineeName() + "', " +
							"'" + examinee.getExamineeTelephone() + "', " + 
							"'" + Config.NOEXAMING + "', " +
							"'" + examinee.getExamineeSex() + "', " +
							"'" + examinee.getExamineeSchool() + "', " + 
							"'" + examinee.getExamineeEmail() + "', " +
							"'" + Config.PASSWORD + "', " +
							"'" + pposition.getPpositionId() + "')");
			examineeSqls.add(examineeSql);
		}
		for (String string : examineeSqls) {
			System.out.println(string + ";");
		}
		//得到链接
		DBConnection connection = new DBConnection();
		//执行sql
		connection.executeBatch(examineeSqls);
		if(connection!=null){
			connection.close();
		}
		System.out.println("----------------------------------" + new Date().toString());
		System.out.println("我是DAO结束的时候" + new Date().toString());
	}

	/**
	 * 获取考生的状态信息
	 * @return
	 */
	@Override
	public List<String> findExamineeState() {
		List<String> list = new ArrayList<String>();
		String sql = "select DISTINCT examineeState from examinee";
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("examineeState"));
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
	
	


	public Object findByExamineeTel(String examineeTel) {
		// 定义一个sql
		String sql = "select count(1) from examinee where examineeTelephone= '"
				+ examineeTel + "'";

		// 定义一个List<Examinee>的集合
		int count  = 0;

		System.out.println(sql);
		// 得到链接并且执行SQL语句
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

}
