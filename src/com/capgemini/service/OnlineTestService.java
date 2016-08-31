package com.capgemini.service;

import java.util.Map;
import java.util.Set;

import com.capgemini.domain.ExaminationPaper;
import com.capgemini.domain.Examinee;

/**
 * 在线考试的服务层 
 * @author chao538
 *
 */
public interface OnlineTestService {
	
	/**
	 * 得到试卷
	 * @return
	 */
	public ExaminationPaper getExaminationPaper(Examinee examinee);
	
	/**
	 * 得到成绩
	 * 
	 */
	public int getGrade(Map<String, String> map);
	
	/**
	 * 给考生设置成绩
	 * @return
	 */
	public boolean setGrade(Examinee examinee, int grade, Set<String> testIds);

	/**
	 * 根据考生Id查出成绩
	 * @param examineeId
	 * @return
	 */
	public int findGrade(String examineeId);
	
}
