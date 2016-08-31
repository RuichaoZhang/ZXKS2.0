package com.capgemini.formbean;

import com.capgemini.domain.Examinee;

public class ExamineeUpdateFormBean extends Examinee{
	private String examineeId;
	
	public ExamineeUpdateFormBean(String examineeId, String examineeName,
			String examineePassword, String examineeTelephone,
			String examineeState, String examineeSex, String examineeSchool,
			String examineeEmail, String ppositionId) {
		super();
		this.examineeId = examineeId;
		this.examineeName = examineeName;
		this.examineePassword = examineePassword;
		this.examineeTelephone = examineeTelephone;
		this.examineeState = examineeState;
		this.examineeSex = examineeSex;
		this.examineeSchool = examineeSchool;
		this.examineeEmail = examineeEmail;
		this.ppositionId = ppositionId;
	}
	public String getExamineeId() {
		return examineeId;
	}
	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}
	private String examineeName;
	private String examineePassword;
	private String examineeTelephone;
	private String examineeState;
	private String examineeSex;
	private String examineeSchool;
	private String examineeEmail;
	private String ppositionId;
	public ExamineeUpdateFormBean() {
		super();
	}
	public ExamineeUpdateFormBean(String examineeName,
			String examineePassword, String examineeTelephone,
			String examineeState, String examineeSex, String examineeSchool,
			String examineeEmail, String ppositionId) {
		super();
		this.examineeName = examineeName;
		this.examineePassword = examineePassword;
		this.examineeTelephone = examineeTelephone;
		this.examineeState = examineeState;
		this.examineeSex = examineeSex;
		this.examineeSchool = examineeSchool;
		this.examineeEmail = examineeEmail;
		this.ppositionId = ppositionId;
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
	public String getPpositionId() {
		return ppositionId;
	}
	public void setPpositionId(String ppositionId) {
		this.ppositionId = ppositionId;
	}
	
	
}