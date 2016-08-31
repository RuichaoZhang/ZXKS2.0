package com.capgemini.factory;

import com.capgemini.service.ExamineeService;
import com.capgemini.service.GradeService;
import com.capgemini.service.HrService;
import com.capgemini.service.LoginService;
import com.capgemini.service.PpsitionService;
import com.capgemini.service.TestItemService;
import com.capgemini.service.TestRuleItemService;
import com.capgemini.service.TestRuleService;
import com.capgemini.service.TestService;
import com.capgemini.service.TestTypeService;
import com.capgemini.service.impl.ExamineeServiceImpl;
import com.capgemini.service.impl.GradeServiceImpl;
import com.capgemini.service.impl.HrServiceImpl;
import com.capgemini.service.impl.LoginServiceImpl;
import com.capgemini.service.impl.OnlineTestServiceImpl;
import com.capgemini.service.impl.PpositionServiceImpl;
import com.capgemini.service.impl.TestItemServiceImpl;
import com.capgemini.service.impl.TestRuleItemServiceImpl;
import com.capgemini.service.impl.TestRuleServiceImpl;
import com.capgemini.service.impl.TestServiceImpl;
import com.capgemini.service.impl.TestTypeServiceImpl;
/**
 * 定义一个生成Service的工厂类,并将其定义成单例的,每有一个Service的实现类就必在这个类里面注册一下
 * @author chao538
 *
 */
public class ServiceFactory {
	
	/**
	 * 得到ServiceFactory的实例
	 */
	private static ServiceFactory instance = new ServiceFactory();
	
	/**
	 * 将构造方法私有
	 */
	private ServiceFactory(){
		
	}
	
	/**
	 * @return 调用时返回ServiceFactory的实例
	 */
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	/**
	 * 声明为LoginService,返回值为其实现类
	 * @return 调用时返回LoginServiceImpl
	 */
	public LoginService getLoginServiceImpl(){
		return new LoginServiceImpl();
	}
	
	/**
	 * 声明为HrService,返回值为其实现类
	 * @return 调用时返回HrServiceImpl
	 */
	public HrService getHrServiceImpl(){
		return new HrServiceImpl();
	}
	
	/**
	 * 声明为ExamineeService,返回值为其实现类
	 * @return 调用时返回ExamineeServiceImpl
	 */
	public ExamineeService getExamineeServiceImpl(){
		return new ExamineeServiceImpl();
	}
	/**
	 * 声明为TestTypeService,返回值为其实现类
	 * @return 调用时返回TestTypeServiceImpl
	 */
	public TestTypeService getTestTypeServiceImpl(){
		return new TestTypeServiceImpl();
	}
	/**
	 * 声明为PpositionService，返回值为其实现类
	 * @return 调用时返回PpositionServiceImpl
	 */
	public PpsitionService getPpositionServiceImpl()
	{
		return new PpositionServiceImpl();
	}
        /**
	 * 声明为GradeService,返回值为其实现类
	 * @return 调用时返回GradeServiceImpl
	 */
	public GradeService getGradeServiceImpl(){
		return new GradeServiceImpl();
	}
        /**
	 * 声明为TestService,返回值为其实现类
	 * @return 调用时返回TestServiceImpl
	 */
	public TestService getTestServiceImpl(){
		return new TestServiceImpl();
	}
	
	/**
	 * 声明为TestItemService,返回值为其实现类
	 * @return 调用时返回TestItemServiceImpl
	 */
	public TestItemService getTestItemServiceImpl(){
		return new TestItemServiceImpl();
	}
	/**
	 * 声明为TestRuleService,返回值为其实现类
	 * @return 调用时返回TestRuleServiceImpl
	 */
	public TestRuleService getTestRuleServiceImpl()
	{
		return new TestRuleServiceImpl();
	}
	/**
	 * 声明为TestRuleItemService,返回值为其实现类
	 * @return 调用时返回TestRuleItemServiceImpl
	 */
	public TestRuleItemService getTestRuleItemServiceImpl()
	{
		return new TestRuleItemServiceImpl();
	}
	/**
	 * 声明为TestRuleItemService,返回值为其实现类
	 * @return 调用时返回TestRuleItemServiceImpl
	 */
	public OnlineTestServiceImpl getOnlineTestServiceImpl()
	{
		return new OnlineTestServiceImpl();
	}
}