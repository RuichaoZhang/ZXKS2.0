package com.capgemini.domain;

/**
 * Hr类
 * @author chao538
 *
 */
public class Hr {
	
	/**
	 * HrId
	 */
	private String hrId;
	
	/**
	 * Hr姓名
	 */
	private String hrName;
	
	/**
	 * Hr密码
	 */
	private String hrPassword;
	
	public Hr() {
		super();
	}
	public Hr(String hrId, String hrName, String hrPassword) {
		super();
		this.hrId = hrId;
		this.hrName = hrName;
		this.hrPassword = hrPassword;
	}
	public String getHrId() {
		return hrId;
	}
	public void setHrId(String hrId) {
		this.hrId = hrId;
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