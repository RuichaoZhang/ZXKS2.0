package com.capgemini.formbean;

public class HrLoginFormBean {
	private String hrName;
	private String hrPassword;
	
	public HrLoginFormBean() {
		super();
	}
	public HrLoginFormBean(String hrName, String hrPassword) {
		super();
		this.hrName = hrName;
		this.hrPassword = hrPassword;
	}
	public String getHrName() {
		return hrName;
	}
	public void setHrName(String hrName) {
		this.hrName = hrName;
	}
	public String getHrPassword() {
		return hrPassword;
	}
	public void setHrPassword(String hrPassword) {
		this.hrPassword = hrPassword;
	}
	
}
