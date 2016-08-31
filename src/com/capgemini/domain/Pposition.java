package com.capgemini.domain;

public class Pposition {
	private String ppositionId;
	private String ppositionName;
	private TestRule testrule;
	public Pposition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pposition(String ppositionId, String ppositionName, TestRule testrule) {
		super();
		this.ppositionId = ppositionId;
		this.ppositionName = ppositionName;
		this.testrule = testrule;
	}
	public String getPpositionId() {
		return ppositionId;
	}
	public void setPpositionId(String ppositionId) {
		this.ppositionId = ppositionId;
	}
	public String getPpositionName() {
		return ppositionName;
	}
	public void setPpositionName(String ppositionName) {
		this.ppositionName = ppositionName;
	}
	public TestRule getTestrule() {
		return testrule;
	}
	public void setTestrule(TestRule testrule) {
		this.testrule = testrule;
	}
	@Override
	public String toString() {
		return "Pposition [ppositionId=" + ppositionId + ", ppositionName="
				+ ppositionName + ", testrule=" + testrule + "]";
	}
	
	

}
