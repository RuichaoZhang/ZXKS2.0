package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.Pposition;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.util.Page;

/**
 * 职位管理的Service层
 * @author bianbian 2015/11/26
 *
 */
public interface PpsitionService {
	
	/**
	 * 新增一个职位
	 * @param ppositionName 得到职位名称参数
	 * @param testruleId  得到试卷类型参数
	 * @return 返回boolean类型 返回true时成功，返回false时失败
	 */
	public boolean addPposition(String ppositionName,String testruleId);
	
	/**
	 * 删除一个职位
	 * @param ppositionId 得到职位Id参数
	 * @return 返回boolean类型 返回true时成功，返回false时失败
	 */
	public boolean deletePposition(String ppositionId);
	
	/**
	 * 修改职位
	 * @param ppositionId 得到职位Id参数
	 * @param ppositionName 得到职位名称参数
	 * @param testruleId 得到试卷类型名称参数
	 * @return 返回boolean类型 返回true时成功，返回false时失败
	 * @throws ExceptionMessege 
	 */
	public boolean updatePposition(String ppositionId,String ppositionName,String testruleId) throws ExceptionMessege;
	
	/**
	 * 查询职位(分页查询)
	 * @param pageNum 得到当前页的页码
	 * @return 将分页查询的信息封装进Page对象，返回Page对象
	 */
	Page findPageRecords(String pageNum);
	
	/**
	 * 查询职位(条件查询)
	 * @param ppositionName 得到职位名称参数
	 * @return  返回Page对象
	 */
	public Page findPposition(String ppositionName,String pageNum);
	
	/**
	 * 查询职位(按ID查询)
	 * @param ppositionId 得到职位Id参数
	 * @return 返回Pposition对象
	 */
	public Pposition findByPpositoinId(String ppositionId);
	
	/**
	 * 查询试卷表
	 * @return 返回一个List<Pposition>对象
	 */
	public List<Pposition> findTestRule();
	
	/**
	 * 查询职位表
	 * @return 返回一个List<Pposition>对象
	 */
	public List<Pposition> findPposition();
	
	/**
	 * 通过职位名称查询
	 * @param ppositionName 职位名称
	 * @return 返回一个List<Pposition>对象
	 */
	public List<Pposition> findToPposition(String ppositionName);
	
	/**
	 * 查询职位名称
	 * @return 返回一个List<Pposition>对象
	 */
	public List<Pposition> findPpositionName();
	
	/**
	 * 判断职位是否存在
	 * @param ppositionName 职位名称
	 * @param testRuleId    试卷Id
	 * @return 返回boolean类型 返回true时成功，返回false时失败
	 */
	public boolean judegPposition(String ppositionName);
	
	/**
	 * 通过职位名称查询
	 * @param ppositionName 职位名称
	 * @return
	 */
	public Pposition findByPposition(String ppositionName);
	
}