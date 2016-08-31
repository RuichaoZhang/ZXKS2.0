package com.capgemini.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.GradeDao;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.Grade;
import com.capgemini.domain.Pposition;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.util.Config;
import com.capgemini.util.DBConnection;

/**
 * 成绩管理的Dao的实现类
 * 
 * @author Administrator
 * @since  2015-12-3
 */
public class GradeDaoImpl implements GradeDao{

	//未用删除
	@Override
	public boolean delete(String id) {
		return false;
	}

	//未用更新
	@Override
	public boolean update(Object obj) throws ExceptionMessege {
		return false;
	}

	//未用保存
	@Override
	public boolean save(Object obj) {
		return false;
	}

	//未用父类查询
	@Override
	public Object find(String item1, String item2, String item3, String item4) {
		return null;
	}
	
	/**
	 * 按考生Id查询成绩
	 */
	@Override
	public Object findById(String id) {
		String sql = "select * from grade where examineeId=+'"+id+"'";
		System.out.println("我是GradeDAO************************");
		System.out.println(id);
		System.out.println(sql);
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sql);
		int score = 0;
		try {
			if(rs.next()){
				score= rs.getInt("grade.gradeScore");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}

	/**
	 * 得到总记录数
	 * @param  查询的条件examineeName, examineeSex,examineeSchool, ppositionName,examineeState	 
	 * @return 查询到数据库中的总记录
	 */
	@Override
	public int getTotalRecords(String examineeName, String examineeSex,
			String examineeSchool, String ppositionName, String examineeState) {
		int count = 0;
		
		/**定义查询语句*/
		StringBuilder sb = new StringBuilder("select count(*) from examinee,pposition,grade where grade.examineeId=examinee.examineeId and "
				+ "grade.ppositionId=pposition.ppositionId ");
		
		if(!(examineeName == null || "".equals(examineeName))){
			sb.append(" and examineeName like '%"+examineeName+"%'");
		}
		if(!(examineeSex == null || "".equals(examineeSex))){
			sb.append(" and examineeSex = '"+examineeSex+"'");
		}
		if(!(examineeSchool == null || "".equals(examineeSchool))){
			sb.append(" and examineeSchool like '%"+examineeSchool+"%'");
		}
		if(!(ppositionName == null || "".equals(ppositionName))){
			sb.append(" and ppositionName = '"+ppositionName+"'");
		}
		if(!(examineeState == null || "".equals(examineeState))){
			sb.append(" and examineeState = '"+examineeState+"'");
		}
		
		System.out.println(sb.toString());
		
		//得到链接并执行sql
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.executeQuery(sb.toString());
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
	}
	/**
	 * 一览查询及获取分页方法
	 * 
	 * @param   传入分页信息
	 * @return  返回查询成绩集合及分页信息
	 */
	@Override
	public List<Grade> findPageRecords(int startIndex, int pageSize) {
		
		
		System.out.println("我的dao。---------");
		System.out.println(startIndex);
		
		/**定义一个List<Grade>的集合*/ 
		List<Grade> grades = new ArrayList<Grade>();
		
		/**定义查询语句*/
		String sql = "select * from examinee,pposition,grade where examinee.examineeState='" + Config.PASSINGEXAMING + "' and "
				+ "grade.examineeId=examinee.examineeId and grade.ppositionId=pposition.ppositionId  limit " + startIndex*pageSize + "," + pageSize + "";
		
		System.out.println(sql);
		
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
	
			    Grade grade = new Grade(
			    		examinee,
			            pposition,
			    		rs.getInt("grade.gradeScore"),
			    		rs.getInt("grade.gradeFullmark")
			    		);
			    grades.add(grade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return grades;
		
	}
	
	/**
	 * 模糊查询方法
	 * 
	 * @param   传入查询的条件examineeName, examineeSex, examineeSchool, ppositionName, examineeState, startIndex, pageSize
	 * @return  返回按条件查询出的成绩集合
	 */
	@Override
	public List<Grade> findByLike(String examineeName, String examineeSex,
			String examineeSchool, String ppositionName, String examineeState,int startIndex, int pageSize) {
		
		/**定义一个List<Grade>的集合*/ 
		List<Grade> grades = new ArrayList<Grade>();
		
		/**定义查询语句*/
		StringBuilder sb = new StringBuilder("select * from examinee,pposition,grade where grade.examineeId=examinee.examineeId and "
				+ "grade.ppositionId=pposition.ppositionId ");
		
		if(!(examineeName == null || "".equals(examineeName))){
			sb.append(" and examineeName like '%"+examineeName+"%'");
		}
		if(!(examineeSex == null || "".equals(examineeSex))){
			sb.append(" and examineeSex = '"+examineeSex+"'");
		}
		if(!(examineeSchool == null || "".equals(examineeSchool))){
			sb.append(" and examineeSchool like '%"+examineeSchool+"%'");
		}
		if(!(ppositionName == null || "".equals(ppositionName))){
			sb.append(" and ppositionName = '"+ppositionName+"'");
		}
		if(!(examineeState == null || "".equals(examineeState))){
			sb.append(" and examineeState = '"+examineeState+"'");
		}
		
		sb.append(" limit " + startIndex*pageSize + "," + pageSize + "");
		String sql = sb.toString(); 	
		System.out.println(sql);
		
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {

				Pposition pposition = new Pposition();
				pposition.setPpositionId(rs.getString("pposition.ppositionId"));
				pposition.setPpositionName(rs
						.getString("pposition.ppositionName"));

				Examinee examinee = new Examinee();
				examinee.setExamineeId(rs.getString("examinee.examineeId"));
				examinee.setExamineeName(rs.getString("examinee.examineeName"));
				examinee.setExamineePassword(rs
						.getString("examinee.examineePassword"));
				examinee.setExamineeTelephone(rs
						.getString("examinee.examineeTelephone"));
				examinee.setExamineeState(rs
						.getString("examinee.examineeState"));
				examinee.setExamineeSex(rs.getString("examinee.examineeSex"));
				examinee.setExamineeSchool(rs
						.getString("examinee.examineeSchool"));
				examinee.setExamineeEmail(rs
						.getString("examinee.examineeEmail"));

				Grade grade = new Grade(examinee, pposition,
						rs.getInt("grade.gradeScore"),
						rs.getInt("grade.gradeFullmark"));
				grades.add(grade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return grades;
	}

	/**
	 * 查询职位信息方法
	 * 
	 * @param 无
	 * @return 返回grade集合，包含职位信息
	 */
	@Override
	public List<Grade> findPposition() {
		//定义Pposition对象
		Pposition ppositon = null;
		//定义Grade对象
		Grade grade = null;
		//定义List<Grade>对象
		List<Grade> grades = new ArrayList<Grade>();
		// 定义sql
		String sql = "select ppositionId,ppositionName from pposition";
		//建立数据库连接并执行sql
		DBConnection connection = new DBConnection();
		//定义结果集
		ResultSet rs = connection.executeQuery(sql);
		try {
			while(rs.next()){
				//得到职位Id
				String ppositionId = rs.getString("ppositionId");
			    //得到职位名称
				String ppositionName = rs.getString("ppositionName");
				//生成职位对象
				ppositon = new Pposition();
				//将参数放进职位对象
				ppositon.setPpositionId(ppositionId);
				ppositon.setPpositionName(ppositionName);
				//生成成绩对象
				grade = new Grade();
				//将职位对象放进成绩对象
				grade.setPposition(ppositon);
				//将成绩对象对象放进List对象
				grades.add(grade);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		connection.close();
		return grades;
	}

	//未用
	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
