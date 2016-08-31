package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.util.Page;


/**
 * 定义名为TestTypeService的接口
 * @author chao538
 *
 */
public interface TestTypeService {
	
	
	/**
	 * 全查方法,查询所有试题类型
	 * @return 返回一个List<TestType>
	 */
	public List<TestType> getTestTypeAll();
	
	/**
	 * 通过TestTypeId来删除
	 * @param TestTypeId 从Servlet中传入的TestTypeId
	 * @return 返回0删除是否成功,返回true则删除成功,返回false则删除失败
	 */
	public boolean deleteTestType(String testTypeId);
	
	/**
	 * 新增一个考生
	 * @param testType 从Servlet传入一个TestType对象
	 * @return 返回新增是否成功,返回true则新增成功,返回false则新增失败
	 */
	public boolean addTestType(String testTypeName);
	
	/**
	 * 更新考生信息
	 * @param testType 从Servlet传入一个试题类型Id和试题类型名称
	 * @return 返回更新是否成功,返回true则更新成功,返回false则更新失败
	 * @throws ExceptionMessege 
	 */
	public boolean updateTestType(String testTypeId, String testTypeName) throws ExceptionMessege;
	
	/**
	 * 根据条件查询
	 * @param testTypeName 试题类型名称
	 * @return
	 */
	public TestType findTestType(String testTypeName);
	
	/**
	 * 根据用户页码返回封装了分页有关数据的对象
	 * @param pageNum
	 * @return
	 */
	public Page findPageRecords(String pageNum);

	/**
	 * 根据试题类型id查
	 * @param testTypeId
	 * @return 返回试题类型对象
	 */
	public TestType findByTestTypeId(String testTypeId);
	/**
	 * 查询全部试题类型
	 * @return 返回所有的试题类型
	 */
	public List<TestType> findAllTestType();
}
