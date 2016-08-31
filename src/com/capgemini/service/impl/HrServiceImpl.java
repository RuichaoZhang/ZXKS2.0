package com.capgemini.service.impl;

import com.capgemini.domain.Hr;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.HrService;

public class HrServiceImpl implements HrService{
	
	@Override
	public Hr getHr(String hrName, String hrPassword) {
		return DaoFactory.getInstance().getHrDaoImpl().findByHrNameAndHrPassword(hrName, hrPassword);
	}
}
