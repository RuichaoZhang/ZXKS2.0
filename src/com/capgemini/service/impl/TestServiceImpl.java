package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.dao.TestDao;
import com.capgemini.dao.TestItemDao;
import com.capgemini.dao.TestTypeDao;
import com.capgemini.domain.Test;
import com.capgemini.domain.TestItem;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.TestService;
import com.capgemini.util.Page;

public class TestServiceImpl implements TestService {
	
	TestDao testDao = DaoFactory.getInstance().getTestDaoImpl();
	TestItemDao testItemDao = DaoFactory.getInstance().getTestItemDaoImpl();
	TestTypeDao testTypeDao = DaoFactory.getInstance().getTestTypeDaoImpl();

	/**
	 * 试题管理service层的实现类
	 * @author wanghuan 
	 * @since 2015-11-25
	 */
	public TestServiceImpl() {
		
	}
	

	/**
	 * 删除试题
	 * @param 根据试题的ID删除试题
	 * @return 返回为true时删除成功，返回false时删除失败	
	 */
	@Override
	public boolean deleteTest(String testId) {
		//为其添加事物处理
		boolean flag = false;
		if(testItemDao.delete(testId) && testDao.delete(testId)){
			flag = true;
		}
		
		return flag;
	}
	/**
	 * 试题的添加
	 * @param 为其传入一个试题的对象
	 * @return 返回为true时添加成功，返回false时添加失败
	 */

	@Override
	public boolean addTest(Test test) {
		boolean flag = false;
		if(testDao.save(test) && testItemDao.save(test.getTestItemList())){
			flag = true;
		}
		
		return flag;
	}

	/**
	 * 试题修改
	 * @param 为其传入一个试题对象
	 * @return 返回为true时修改成功，返回false时修改失败
	 * 
	 */

	@Override
	public boolean updateTest(Test test) {
		boolean flag = true;

		try {
			if (testDao.update(test)
					&& testItemDao.update(test.getTestItemList())) {
				flag = true;
			}
		} catch (ExceptionMessege e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 通过试题类型对象
	 * @param 为其传入一个试题对象
	 * @return 返回试题对象的集合
	 * 
	 */
	@Override
	public List<Test> findByTestTypeNameTest(List<TestType> lists) {

		ArrayList<Test> list = new ArrayList<Test>();
		Iterator<TestType> it= lists.iterator();
		while(it.hasNext()){
			TestType testType = it.next();
			List<Test> tests = testDao.findByTestType(testType);
			Iterator<Test> itt= tests.iterator();
			while(itt.hasNext()){
				Test test = itt.next();
				List<TestItem> testItems = testItemDao.findByTestId(test.getTestId());
				test.setTestItemList(testItems);	
				list.add(test);
			}
		}
		
		return list;
	}
	
	/**
	 * 条件查询试题(分页查询）
	 * @param 为其传入查询条件的参数
	 * @return 返回试题对象的集合
	 * 
	 */
	@Override
	public Page findByLikeTest(String testSubject, String testTypeName,
			String testItemTrue, int pageNum) {
		
		Page page = new Page(pageNum, testDao.getTotalRecords(testSubject,testTypeDao.find(testTypeName),testItemTrue));
		ArrayList<Test> list = new ArrayList<Test>();
		
		//如果此处获取到的TestTypeName 是空值，那么我么将获取到了testType对象是空值，所以在dao层会报错
		
		//当testTypeName为null时，其返回的是null
		List<Test> tests = testDao.findByLikeTest(testSubject, testTypeDao.find(testTypeName),
				testItemTrue, page.getStartIndex(), page.getPageSize());
		Iterator<Test> it = tests.iterator();
		
		while (it.hasNext()) {
			Test test = it.next();
			List<TestItem> testItems = testItemDao.findByTestId(test.getTestId());
			
			//循环遍历取出来的试题选项testItem，将正确的答案存到
			Iterator<TestItem> itt = testItems.iterator();
			while(itt.hasNext()){
				TestItem testItem = itt.next();
				if(testItem.getTestItemState().equals("1")){
					test.setTestItemTrue(testItem.getTestItemContent());
				}
			}
			test.setTestItemList(testItems);
			//以上是将所有的试题封装好了
			list.add(test);
		}
		page.setRecords(list);
		return page;
	}
	
	

	/**
	 * 根据试题的编号查询试题
	 * @param testId
	 * @return 返回一个试题的对象
	 */

	@Override
	public Test findByTestId(String testId) {
		ArrayList<TestItem> testItems = (ArrayList<TestItem>) testItemDao.findByTestId(testId);
		Test test = new Test();		
	    test = testDao.findByTestId(testId);	
	    
	    Iterator<TestItem> itt = testItems.iterator();
		while(itt.hasNext()){
			TestItem testItem = itt.next();
			if(testItem.getTestItemState().equals("1")){
				test.setTestItemTrue(testItem.getTestItemContent());
			}
		}
		test.setTestItemList(testItems);
		return test;
	}


	/**
	 * 查询全部试题
	 * @return 返回试题的集合
	 */
	@Override
	public Page findAllTest(int pageNum) {
		ArrayList<Test> list = new ArrayList<Test>();

		Page page = new Page(pageNum, testDao.getTotalRecords());
		
		//从此读出来的试题对象中没有添加试题选项的值，还有正确答案的值
		ArrayList<Test> tests = (ArrayList<Test>) testDao.findAllTest(page.getStartIndex(), page.getPageSize());
		Iterator<Test> it = tests.iterator();
		while (it.hasNext()) {
			Test test = it.next();
			List<TestItem> testItems = testItemDao.findByTestId(test.getTestId());
			
			//循环遍历取出来的试题选项testItem，将正确的答案存到
			Iterator<TestItem> itt = testItems.iterator();
			while(itt.hasNext()){
				TestItem testItem = itt.next();
				if(testItem.getTestItemState().equals("0")){
					test.setTestItemTrue(testItem.getTestItemContent());
				}
			}
			test.setTestItemList(testItems);
			//以上是将所有的试题封装好了
			
			
			list.add(test);
		}
		page.setRecords(list);
		return page;
	}
	/**
	 * 该方法用于完成试题的批量导入
	 * @param 试题的列表
	 */
	@Override
	public void saveAllTest(List<Test> tests) {
		testDao.insertAllTest(tests);
	}
	/**
	 * 根据试题题目查询试题
	 * @param testSubject
	 * @return 
	 */
	@Override
	public Test findByTestSubject(String testSubject) {
		Test test = null;
		test = testDao.findByTestSubject(testSubject);
		return test;
	}

	/**
	 * 根据试题类型ID查询该试题的个数
	 * @param testTypeId
	 * @return
	 */
	@Override
	public int findByTestTypeName(String testTypeName) {
		return testDao.findByTestTypeName(testTypeName);
	}
}
