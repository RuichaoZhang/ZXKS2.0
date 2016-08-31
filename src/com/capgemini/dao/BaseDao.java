package com.capgemini.dao;

import java.util.List;

import com.capgemini.exception.ExceptionMessege;
/**
 * 基礎的Dao其他Dao可以繼承該接口,如果無共通方法,可在自己的Dao中添加
 * @author chao538
 * @since 2015-11-07
 */

public interface BaseDao {
	
	/**
	 * 刪除方法
	 * @param id 位該對象在數據庫中所對應的主鍵
	 * @author chao538
	 * @since 2015-11-07
	 */
	
	public boolean delete(String id);
	
	/**
	 * 修改方法
	 * @param obj 直接傳入對象進行修改
	 * @author chao538
	 * @throws ExceptionMessege 
	 * @since 2015-11-07
	 */
	
	public boolean update(Object obj) throws ExceptionMessege;
	
	/**
	 * 保存方法
	 * @param obj 傳入對象將其保存至數據庫中
	 * @author chao538
	 * @since 2015-11-07
	 */
	
	public boolean save(Object obj);
	
	
	/**
	 * 查詢方法
	 * @param id 通過id查詢出一個完整的對象
	 * @author chao538
	 * @since 2015-11-07
	 */
	
	public Object find(String item1, String item2, String item3, String item4);
	
	/**
	 * 查詢方法
	 * @param id 通過id查詢出一個完整的對象
	 * @author chao538
	 * @since 2015-11-07
	 */
	
	public Object findById(String id);
	
	/**
	 * 查詢所有
	 * 查詢該表中的所有的信息
	 * @author chao538
	 * @since 2015-11-07
	 */
	
	public List<?> findAll();

}
