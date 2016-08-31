package com.capgemini.domain;

/**
 * 考生类
 * @author chao538
 *
 */
public class Examinee {
	
	/**
	 * 考生编号
	 */
	private String examineeId;
	
	/**
	 * 考生姓名
	 */
	private String examineeName;
	
	/**
	 * 考生密码
	 */
	private String examineePassword;
	
	/**
	 *考生电话号码 
	 */
	private String examineeTelephone;
	
	/**
	 * 考生状态
	 * 可以填四个值: "未考试 ", "正在考试", "未通过", "已通过"
	 */
	private String examineeState;
	
	/**
	 * 考生性别
	 * 可以填四个值:"男", "女"
	 */
	private String examineeSex;
	
	/**
	 * 考生学校
	 */
	private String examineeSchool;
	
	/**
	 * 考生邮箱
	 */
	private String examineeEmail;
	
	/**
	 * 考生邮箱
	 */
	private String examTime;
	
	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	/**
	 * 职位Id
	 */
	private Pposition pposition;
	
	public Examinee() {
		super();
	}
	
	/**
	 * 
	 * @param examineeId
	 * @param examineeName
	 * @param examineePassword
	 * @param examineeTelephone
	 * @param examineeState
	 * @param examineeSex
	 * @param examineeSchool
	 * @param examineeEmail
	 * @param ppositionId
	 */
	public Examinee(String examineeId, String examineeName,
			String examineePassword, String examineeTelephone,
			String examineeState, String examineeSex, String examineeSchool,
			String examineeEmail, Pposition pposition) {
		super();
		this.examineeId = examineeId;
		this.examineeName = examineeName;
		this.examineePassword = examineePassword;
		this.examineeTelephone = examineeTelephone;
		this.examineeState = examineeState;
		this.examineeSex = examineeSex;
		this.examineeSchool = examineeSchool;
		this.examineeEmail = examineeEmail;
		this.pposition = pposition;
	}
	public String getExamineeId() {
		return examineeId;
	}
	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}
	public String getExamineeName() {
		return examineeName;
	}
	public void setExamineeName(String examineeName) {
		this.examineeName = examineeName;
	}
	public String getExamineePassword() {
		return examineePassword;
	}
	public void setExamineePassword(String examineePassword) {
		this.examineePassword = examineePassword;
	}
	public String getExamineeTelephone() {
		return examineeTelephone;
	}
	public void setExamineeTelephone(String examineeTelephone) {
		this.examineeTelephone = examineeTelephone;
	}
	public String getExamineeState() {
		return examineeState;
	}
	public void setExamineeState(String examineeState) {
		this.examineeState = examineeState;
	}
	public String getExamineeSex() {
		return examineeSex;
	}
	public void setExamineeSex(String examineeSex) {
		this.examineeSex = examineeSex;
	}
	public String getExamineeSchool() {
		return examineeSchool;
	}
	public void setExamineeSchool(String examineeSchool) {
		this.examineeSchool = examineeSchool;
	}
	public String getExamineeEmail() {
		return examineeEmail;
	}
	public void setExamineeEmail(String examineeEmail) {
		this.examineeEmail = examineeEmail;
	}
	public Pposition getPposition() {
		return pposition;
	}
	public void setPpositionId(Pposition pposition) {
		this.pposition = pposition;
	}
	
	
}