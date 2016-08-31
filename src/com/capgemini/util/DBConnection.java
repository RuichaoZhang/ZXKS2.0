package com.capgemini.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class DBConnection {
	
	Connection conn = DBCPUtil.getConnection();
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	/**
	 * 得到链接,以便事务的控制
	 * @return
	 */
	public Connection getConnction(){
		 return conn;
	}
	// 执行增、删、改SQL语句--------------------
	public int executeUpdate(String sql){
		int a = 0;
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			a = stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	//批处理
	//批处理语句不同时,使用Statement,相同时用PrepareStatement
	public void executeBatch(List<String> sqls){
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < sqls.size(); i++) {
				stm.addBatch(sqls.get(i));
			}
			stm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 重载执行增、删、改SQL语句--------------------
	public int executeUpdate(String sql, Object[] obj){
		int a = 0;
		try {
			pstm = conn.prepareStatement(sql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					pstm.setObject(i + 1, obj[i]);
				}
			}
			a = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	// 执行查询SQL语句----------------------------
	public ResultSet executeQuery(String sql){
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 重载执行查询SQL语句----------------------------
	public ResultSet executeQuery(String sql, Object[] obj){
		try {
			pstm = conn.prepareStatement(sql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					pstm.setObject(i + 1, obj[i]);
				}
			}
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	// 关闭ResultSet
	public  void close() {
		DBCPUtil.release(rs, stm, conn);
	}
	
	public static void main(String[] args) {
		ResultSet rs = new DBConnection().executeQuery("select * from examinee");
		try {
			while(rs.next())
			{
				System.out.println(111);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
