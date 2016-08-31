package com.capgemini.formbean;

public class LoginFormBean {
	private String userName;
	private String password;
	
	public LoginFormBean() {
		super();
	}

	public LoginFormBean(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
