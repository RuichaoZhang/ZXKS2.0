package com.capgemini.factory;

import com.capgemini.dao.ExamineeDao;
import com.capgemini.dao.GradeDao;
import com.capgemini.dao.HrDao;
import com.capgemini.dao.PpositionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.dao.TestItemDao;
import com.capgemini.dao.TestRuleDao;
import com.capgemini.dao.TestRuleItemDao;
import com.capgemini.dao.TestTypeDao;
import com.capgemini.dao.impl.ExamineeDaoImpl;
import com.capgemini.dao.impl.GradeDaoImpl;
import com.capgemini.dao.impl.HrDaoImpl;
import com.capgemini.dao.impl.OnlineTestDaoImpl;
import com.capgemini.dao.impl.PpositionDaoImpl;
import com.capgemini.dao.impl.TestDaoImpl;
import com.capgemini.dao.impl.TestItemDaoImpl;
import com.capgemini.dao.impl.TestRuleDaoImpl;
import com.capgemini.dao.impl.TestRuleItemDaoImpl;
import com.capgemini.dao.impl.TestTypeDaoImpl;

/**
 * 定义一个生成Dao的工厂类,并将其定义成单例的,每有一个Dao的实现类就必在这个类里面注册一下
 * @author chao538
 *
 */
public class DaoFactory {
	
	/**
	 * 得到DaoFactory的实例
	 */
	private static DaoFactory instance = new DaoFactory();
	
	/**
	 * 将构造方法定义为私有
	 */
	private DaoFactory(){
	}
	
	/**
	 * @return 调用时返回DaoFactory对象
	 */
	public static DaoFactory getInstance(){
		return instance;
	}
	
	/**
	 * 声明为HrDao,返回值围为其实现类
	 * @return 调用时返回HrDaoImpl
	 */
	public HrDao getHrDaoImpl(){
		return new HrDaoImpl();
	}
	
	/**
	 * 声明为ExamineeDao,返回值为其实现类
	 * @return 调用时返回ExamineeDaoImpl
	 */
	public ExamineeDao getExamineeDaoImpl(){
		return new ExamineeDaoImpl();
	}

	/**
	 * 声明为TestTypeDao,返回值为其实现类
	 * @return 调用时返回ExamineeDaoImpl
	 */
	public TestTypeDao getTestTypeDaoImpl(){
		return new TestTypeDaoImpl();
	}

	/**
	 * 声明为PpositionDao，返回值为其实现类
	 * @return 调用时返回PpositionDaoImpl
	 */
	public PpositionDao getPpositionDaoImpl() {
		// TODO Auto-generated method stub
		return new PpositionDaoImpl();
	}
	/**
	 * 声明为TestRuleDao，返回值为其实现类
	 * @return 调用时返回TestRuleDaoImpl
	 */
	public TestRuleDao getTestRuleDaoImpl()
	{
		return new TestRuleDaoImpl();
	}
	/**
	 * 声明为TestRuleItemDao，返回值为其实现类
	 * @return 调用时返回TestRuleItemDaoImpl
	 */
	public TestRuleItemDao getTestRuleItemDaoImpl()
	{
		return new TestRuleItemDaoImpl();
	}
	/**
	 * 声明为GradeDao,返回值为其实现类
	 * @return 调用时返回GradeDaoImpl
	 */
	public GradeDao getGradeDaoImpl(){
		return new GradeDaoImpl();
	}
	/**
	 * 声明为TestDao,返回值为其实现类
	 * @return 调用时返回TestDaoImpl
	 */
	public TestDao getTestDaoImpl(){
		return new TestDaoImpl();
	}
	/**
	 * 声明为TestItemDao,返回值为其实现类
	 * @return 调用时返回TestItemDaoImpl
	 */
	public TestItemDao getTestItemDaoImpl(){
		return new TestItemDaoImpl();
	}

	/**
	 * 声明为TestItemDao,返回值为其实现类
	 * @return 调用时返回TestItemDaoImpl
	 */
	public OnlineTestDaoImpl getOnlineTestDaoImpl(){
		return new OnlineTestDaoImpl();
	}

	


}
