package com.capgemini.dao;

import java.util.Map;

public interface OnlineTestDao {
	
	public int getGrade(Map<String, String> map);
	
	public boolean setGrade(String examineeId, String ppositionId, int score, int fullMark);
}
