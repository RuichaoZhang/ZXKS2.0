package com.capgemini.dao;

import com.capgemini.domain.Hr;

public interface HrDao{
	public Hr findByHrNameAndHrPassword(String hrName, String hrPassword);
}
