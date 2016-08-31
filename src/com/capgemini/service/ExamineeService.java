package com.capgemini.service;


import java.util.List;

import com.capgemini.domain.Examinee;
import com.capgemini.domain.Pposition;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.util.Page;

/**
 * 定义名为ExamineeService的接口
 * @author chao538
 * 张瑞超修改于2015-12-4,添加批量导入的方法
 */
public interface ExamineeService {
	
	/**
	 * 通过考生姓名和考生的密码得到考生对象
	 * @param examineeName Servlet传入的考生姓名
	 * @param examineePassword Servlet传入的考生密码
	 * @return 返回一个Examinee对象
	 */
	public Examinee getExaminee(String examineeTelephone, String examineePassword);
	
	/**
	 * 全查方法,查询所有已通过考试的名单
	 * @return 返回一个List<Examinee>
	 */
	public List<Examinee> getExamineeAll();
	
	/**
	 * 通过examineeId来删除
	 * @param examineeId 从Servlet中传入的examineeId
	 * @return 返回删除是否成功,返回true则删除成功,返回false则删除失败
	 */
	public boolean deleteExaminee(String examineeId);
	
	/**
	 * 新增一个考生
	 * @param examinee 从Servlet传入一个examinee对象
	 * @return 返回新增是否成功,返回true则新增成功,返回false则新增失败
	 */
	public boolean addExaminee(Examinee examinee);
	

	
	/**
	 * 根据条件查询
	 * @param examineeName 考生姓名
	 * @param examineeSex 考生性别
	 * @param ppsitionName 职位名称
	 * @return
	 */
	public List<Examinee> findExaminee(String examineeName, String examineeSex ,String ppsitionName);
	
	/**
	 * 根据用户页码返回封装了分页有关数据的对象
	 * @param pageNum
	 * @return
	 */
	Page findPageRecords(String pageNum);
	public Page findByLike(String examineeName, String examineeSex,
			 String ppositionName, String pageNum);
	public Examinee findById(String examineeId);
	/**
	 * 更新考生信息
	 * @param examinee 从Servlet传入一个examinee对象
	 * @return 返回更新是否成功,返回true则更新成功,返回false则更新失败
	 * @throws ExceptionMessege 
	 */
	public boolean updateExaminee(String examineeId, String examineeName,
			String examineePassword, String examineeTelephone,
			String examineeState, String examineeSex, String examineeSchool,
			String examineeEmail, Pposition pposition) throws ExceptionMessege;
	
	/**
	 * 考生的批量导入方法
	 * @param tests
	 */
	public void saveAllExaminee(List<Examinee> examinees);
	/**
	 * 获取考生的状态信息
	 * @return
	 */
	public List<String> findExamineeState();
	
	public boolean updateDate(Object obj);
}
