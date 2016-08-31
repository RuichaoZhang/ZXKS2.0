package com.capgemini.util;

import java.sql.ResultSet;

public class DBUtil {
	
	public static boolean exquteQuery(String sql){
		DBConnection connection = new DBConnection();
		boolean flag = false;
		ResultSet resultSet = null;
		try {
			resultSet = connection.executeQuery(sql);
			flag = resultSet.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return flag;
	}
	
	public static int exquteUpdate(String sql){
		DBConnection connection = new DBConnection();
		int count = 0;
		try {
			count = connection.executeUpdate(sql);

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return count;
	}
}
