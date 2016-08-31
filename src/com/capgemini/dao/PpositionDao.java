package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.Pposition;

/**
 * 职位管理Dao层
 * @author BianBian 2015/11/27
 *
 */
public interface PpositionDao extends BaseDao{
	
	/**
	 * 根据职位名称查询职位
	 * @param ppositionName 职位名称
	 * @param startIndex    每页开始的记录的索引编号
	 * @param pageSize      每页显示的记录数
	 * @return              List<Pposition>对象
	 */
	public List<Pposition> findByPpositionName(String ppositionName,int startIndex, int pageSize);
	
	/**
	 * 查询记录总条数
	 * @param ppositionName 职位名称
	 * @return              List<Pposition>对象
	 */
	public int getTotalRecords(String ppositionName);
	
	/**
	 * 查询分页数据
	 * @param startIndex  每页开始的记录的索引编号
	 * @param pageSize    每页显示的记录条数
	 * @return
	 */
	public List<Pposition> findPageRecords(int startIndex, int pageSize);
	
	/**
	 * 查询试卷信息
	 * @return
	 */
	public List<Pposition> findTestRule();
	
	/**
	 * 查寻职位表的信息
	 * @return
	 */
	public List<Pposition> findPposition();
	
	/**
	 * 通过职位名称查找
	 * @return
	 */
	public List<Pposition> findToPposition(String ppositionName);
	
	/**
	 * 查询职位名称
	 * @return
	 */
	public List<Pposition> findPpositionName();
	
	/**
	 * 判断职位是否存在
	 * @return
	 */
	public boolean judgePposition(String ppositionName);
	
	/**
	 * 根据职位名称查询
	 * @param pposition
	 * @return
	 */
	public Pposition findByPposition(String ppositionName);
}
