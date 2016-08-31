package com.capgemini.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.dao.HrDao;
import com.capgemini.domain.Hr;
import com.capgemini.util.DBConnection;

public class HrDaoImpl implements HrDao{

	@Override
	public Hr findByHrNameAndHrPassword(String hrName, String hrPassword) {
		Hr hr = null;
		String sql = "select hrId, hrName, hrPassword from hr where hrName='"+hrName+"' and hrPassword= '" + hrPassword + "'";
		System.out.println(sql);
		DBConnection connection = new DBConnection();
		try {
			ResultSet rs = connection.executeQuery(sql);
			if(rs.next()){
				hr = new Hr(
						rs.getString("hrId"),
						rs.getString("hrName"), 
						rs.getString("hrPassword")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.close();
		}
		return hr;
	}
}
