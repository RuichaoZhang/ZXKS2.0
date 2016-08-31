package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.Examinee;
/**
 * 考生管理的Dao
 * @author 陈敬刚
 * 张瑞超修改于2015-12-4,添加了批量导入的方法
 */
public interface ExamineeDao extends BaseDao{
	public Object find(String item1, String item2, String item3);
	//定义登录时查询的方法
	public Examinee findByExamineeTelephoneAndExamineePassword(String examineeTelephone, String examineePassword);
	
	/**
	 * 查询记录总条数
	 */
	public List<Examinee> findByLike(String examineeName, String examineeSex,
			String ppositionName, int startIndex, int pageSize);
	public int getTotalRecords(String examineeName, String examineeSex,String ppositionName);
	
	/**
	 * 查询分页数据
	 * @param startIndex 每页开始的记录的索引编号
	 * @param pageSize 每页显示的记录条数
	 * @return
	 */
	public List<Examinee> findPageRecords(int startIndex, int pageSize);
	
	/**
	 * 考生的批量导入
	 * @param tests
	 */
	public void insertAllExaminee(List<Examinee> examinees);
	
	/**
	 * 获取考生的状态信息
	 * @return
	 */
	public List<String> findExamineeState();
	
	/**
	 * 在线考试的时候会调用该修改方法 
	 * @param object
	 * @return
	 */
	public boolean updateDate(Object object);
}
