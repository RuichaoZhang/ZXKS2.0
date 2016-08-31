package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.dao.TestTypeDao;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.TestTypeService;
import com.capgemini.util.Page;

/**
 * TestTypeService的实现类
 * @author chao538
 *
 */
public class TestTypeServiceImpl implements TestTypeService{
	//通过DaoFactory得到dao
	TestTypeDao dao = DaoFactory.getInstance().getTestTypeDaoImpl();
	
	/**
	 * 新增试题类型
	 */
	@Override
	public boolean addTestType(String testTypeName) {
		TestType testType = new TestType();
		System.out.println("我是Service");
		testType.setTestTypeName(testTypeName);
		System.out.println(testType.getTestTypeName());
		return dao.save(testType);
	}
	
	/**
	 * 删除试题类型
	 */
	@Override
	public boolean deleteTestType(String testTypeId) {
		return dao.delete(testTypeId);
	}
	
	/**
	 * 通过试题类型名称查找试题类型
	 */
	
	@Override
	public TestType findTestType(String testTypeName) {
		return dao.find(testTypeName);
	}
	
	/**
	 *分页的方法 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Page findPageRecords(String pageNum) {
		
		//默认值
		int num = Integer.parseInt(pageNum);
		
		//得到试题类型Dao
		TestTypeDao dao = DaoFactory.getInstance().getTestTypeDaoImpl();
		
		System.out.println("我是------------Service");
		System.out.println(num);
		//构造Page对象,因为需要当前页码和总记录条数,所以通过DAO查出记录条数
		Page page = new Page(num, dao.getTotalRecords());
		
		//但是Page对象中的分页记录还没有,所有又通过Dao查询分页记录,Dao查询分页记录需要每页开始记录的索引和每页显示的条数,但这两个参数在Page对象中已经计算完毕
		//查询出记录,并设置到Page对象中
		
		int a = page.getStartIndex();
		int b = page.getPageSize();
		System.out.println(a);
		System.out.println(b);
		
		System.out.println("我是------------Service");
		List records = dao.findPageRecords(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);
		
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TestType> getTestTypeAll() {
		
		List<TestType> testTypes = (List<TestType>) dao.findAll();
		
		return testTypes;
	}
	
	/**
	 * 修改试题类型的方法
	 * @throws ExceptionMessege 
	 */
	@Override
	public boolean updateTestType(String testTypeId, String testTypeName) throws ExceptionMessege {
		TestType testType = new TestType(testTypeId, testTypeName);
		return dao.update(testType);
	}

	/**
	 * 通過試題類型Id來查詢
	 */
	@Override
	public TestType findByTestTypeId(String testTypeId) {
		return (TestType) dao.findById(testTypeId);
	}

	/**
	 * 查询全部试题类型
	 * @return 返回所有的试题类型
	 */
	@Override
	public List<TestType> findAllTestType() {
		ArrayList<TestType> list = new ArrayList<TestType>();
		list = (ArrayList<TestType>) dao.findAllTestType();
		return list;
	}
}