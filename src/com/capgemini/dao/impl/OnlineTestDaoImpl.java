package com.capgemini.dao.impl;

import java.util.Map;

import com.capgemini.dao.OnlineTestDao;
import com.capgemini.util.DBConnection;

public class OnlineTestDaoImpl implements OnlineTestDao{

	@Override
	public int getGrade(Map<String, String> map) {
		
		return 0;
	}

	
	@Override
	public boolean setGrade(String examineeId, String ppositionId, int score,
			int fullMark) {
		boolean flag = false;
		String sql = "insert into grade values('" + examineeId + "','" + ppositionId + "',' "+ score +" ','" + fullMark + "')";
		DBConnection connection = new DBConnection();
		int i = connection.executeUpdate(sql);
		if(i == 1){
			flag = true;
		}
		if(connection != null){
			connection.close();
		}
		return flag;
	}
}