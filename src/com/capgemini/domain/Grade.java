package com.capgemini.domain;

public class Grade {
	
	private Examinee examinee;
	
	private Pposition pposition;
		
	private int gradeScore;
	
	private int gradeFullmark;
	

	public Grade() {
		super();
	}

	public Grade( Examinee examinee, Pposition pposition,int gradeScore,int gradeFullmark) {
		super();
		this.examinee = examinee;
		this.pposition = pposition;
		this.gradeScore = gradeScore;
		this.gradeFullmark = gradeFullmark;
	}

	public Examinee getExaminee() {
		return examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}

	public Pposition getPposition() {
		return pposition;
	}

	public void setPposition(Pposition pposition) {
		this.pposition = pposition;
	}

	public int getGradeScore() {
		return gradeScore;
	}

	public void setGradeScore(int gradeScore) {
		this.gradeScore = gradeScore;
	}

	public int getGradeFullmark() {
		return gradeFullmark;
	}

	public void setGradeFullmark(int gradeFullmark) {
		this.gradeFullmark = gradeFullmark;
	}
	
	
}
