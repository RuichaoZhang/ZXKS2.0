package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.capgemini.dao.ExamineeDao;
import com.capgemini.dao.GradeDao;
import com.capgemini.dao.OnlineTestDao;
import com.capgemini.dao.TestDao;
import com.capgemini.dao.TestItemDao;
import com.capgemini.domain.ExaminationPaper;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.Test;
import com.capgemini.domain.TestItem;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.OnlineTestService;
import com.capgemini.util.Config;

public class OnlineTestServiceImpl implements OnlineTestService{
	
	ExamineeDao examineeDao = DaoFactory.getInstance().getExamineeDaoImpl();
	
	TestDao testDao = DaoFactory.getInstance().getTestDaoImpl();
	
	TestItemDao testItemDao = DaoFactory.getInstance().getTestItemDaoImpl();
	OnlineTestDao onlineTestDao = DaoFactory.getInstance().getOnlineTestDaoImpl();
	GradeDao gradeDao = DaoFactory.getInstance().getGradeDaoImpl();
	/**
	 * 得到试题的dao
	 */
	TestDao dao = DaoFactory.getInstance().getTestDaoImpl(); 
	
	@Override
	public ExaminationPaper getExaminationPaper(Examinee examinee) {
		
		//得到试题类型的dao
		TestDao testDao = DaoFactory.getInstance().getTestDaoImpl();
		
		//定义总的试题的集合(即返回的试卷)
		List<Test> testList = new ArrayList<Test>();
		
		//根据考生的Id得到考生对象,里面有职位,职位里面有试卷
		
		//从职位里面得到试卷规则
		TestRule testRule = examinee.getPposition().getTestrule();
		
		//从试卷里面得到试题规则的条目
		List<TestRuleItem> testRuleItems = testRule.getTestRuleItemList();
		
		//调用testDao的生成试卷的方法生成试题的List
		testList = testDao.generateTest(testRuleItems);
		
		//得到时间
		int time = testRule.getTestRuleTime();
	
		//定义考试试卷的实体并给其赋值
		ExaminationPaper examinationPaper = new ExaminationPaper();
		examinationPaper.setTime(time);
		examinationPaper.setTestList(testList);
		examinationPaper.setTotalTest(testList.size());
		
		//返回数据给Servlet
		return examinationPaper;
	}
	
	/**
	 * 根据试题的id的Set集合得到总分,再设置成绩
	 * 
	 */
	@Override
	public boolean setGrade(Examinee examinee, int grade, Set<String> testIds){
		
		boolean flag = false;
		
		int score = grade;
		
		int fullMark = 0;
		
		//得到试卷的总分
		for (String testId : testIds) {
			Test test = testDao.findByTestId(testId);
			fullMark += test.getTestScore();
		}
		
		System.out.println("-----------------------------我是总分------------------------------");
		System.out.println(fullMark);
		System.out.println("-----------------------------我是总分------------------------------");
		System.out.println("-----------------------------我是线------------------------------");
		int a = fullMark/3;
		System.out.println(a);
		System.out.println(2/3);
		System.out.println("-----------------------------我是线------------------------------");
		int line = a * 2;
		System.out.println("-----------------------------我是线------------------------------");
		System.out.println(line);
		System.out.println("-----------------------------我是线------------------------------");
		//如果考生考试成绩大于总分的2/3,则给考生状态设置为已通过,如果小于,则设置为未通过
		try {
			if(grade > line){
				examinee.setExamineeState(Config.PASSINGEXAMING);
				examineeDao.update(examinee);
			}else{
				examinee.setExamineeState(Config.NOPASSINGEXAMING);
				examineeDao.update(examinee);
				}
		} catch (ExceptionMessege e) {
			e.printStackTrace();
		}
		String ppositionId = examinee.getPposition().getPpositionId();
		
		String examineeId = examinee.getExamineeId();
		
		onlineTestDao.setGrade(examineeId, ppositionId, score, fullMark);
		
		return flag;
	}

	@Override
	public int getGrade(Map<String, String> map) {
		int grade = 0;
		Set<String> set = map.keySet();
		for (String testId : set) {
			if(testId != null){
				Test test = (Test) testDao.findByTestId(testId);
				int score = test.getTestScore();
				if(map.get(testId) != null){
					TestItem testItem = (TestItem) testItemDao.findById(map.get(testId));
					if("1".equals(testItem.getTestItemState())){
						grade= grade + score;
					}
				}
			}
		}
		return grade;
	}

	/**
	 * 根据考生的Id查出成绩
	 */
	@Override
	public int findGrade(String examineeId) {
		return (Integer) gradeDao.findById(examineeId);
	}
}