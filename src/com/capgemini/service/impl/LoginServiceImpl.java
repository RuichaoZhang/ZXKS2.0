package com.capgemini.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.capgemini.dao.HrDao;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.LoginService;
import com.capgemini.util.Config;

public class LoginServiceImpl implements LoginService{
	HrDao hrDao = DaoFactory.getInstance().getHrDaoImpl();
	@Override
	public String hrLogin(String hrName, String hrPassword) {
		if(hrDao.findByHrNameAndHrPassword(hrName, hrPassword) == null) {
			return Config.FAIL;
		}
		return Config.SUCCESS;
	}

	@Override
	public String logout(HttpServletRequest request, String user) {
		HttpSession session = request.getSession();
		session.removeAttribute(user);
		session.invalidate();
		return null;
	}

	@Override
	public String examineeLogin(String examineeTelephone,
			String examineePassword) {
		String flag = Config.FAIL;
		System.out.println("我是Service");
		System.out.println(examineeTelephone + examineePassword);
		if(DaoFactory.getInstance().getExamineeDaoImpl().findByExamineeTelephoneAndExamineePassword(examineeTelephone, examineePassword) != null);{
			flag = Config.SUCCESS;
		}
		return flag;
	}

}
