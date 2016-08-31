package com.capgemini.service.impl;

import java.util.List;

import com.capgemini.dao.ExamineeDao;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.Pposition;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.ExamineeService;
import com.capgemini.util.Page;

public class ExamineeServiceImpl implements ExamineeService{
	//通过DaoFactory得到dao
   ExamineeDao dao = DaoFactory.getInstance().getExamineeDaoImpl();
	
	@Override
	public Examinee getExaminee(String examineeTelephone, String examineePassword) {
		return DaoFactory.getInstance().getExamineeDaoImpl().findByExamineeTelephoneAndExamineePassword(examineeTelephone, examineePassword);	
	}

	@Override
	public boolean addExaminee(Examinee examinee) {
		return DaoFactory.getInstance().getExamineeDaoImpl().save(examinee);
	}

	@Override
	public boolean deleteExaminee(String examineeId) {
		return DaoFactory.getInstance().getExamineeDaoImpl().delete(examineeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examinee> findExaminee(String examineeName, String examineeSex, String ppsition) {
		return (List<Examinee>) DaoFactory.getInstance().getExamineeDaoImpl().find(examineeName, examineeSex, ppsition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examinee> getExamineeAll() {
		return (List<Examinee>) DaoFactory.getInstance().getExamineeDaoImpl().findAll();
	}

	@Override
	public Page findPageRecords(String pageNum) {
		
		//默认值
				int num = Integer.parseInt(pageNum);
				
				//得到GradeDao
				//GradeDao dao = DaoFactory.getInstance().getGradeDaoImpl();
				
				//构造Page对象,因为需要当前页码和总记录条数,所以通过DAO查出记录条数
				Page page = new Page(num, dao.getTotalRecords(null,null,null));
				
				int a = dao.getTotalRecords(null,null,null);
				System.out.println("我是service-------------");
				System.out.println(a);
				
				//但是Page对象中的分页记录还没有,所有又通过Dao查询分页记录,Dao查询分页记录需要每页开始记录的索引和每页显示的条数,但这两个参数在Page对象中已经计算完毕
				//查询出记录,并设置到Page对象中
				List<Examinee> records = dao.findPageRecords(page.getStartIndex(), page.getPageSize());
				page.setRecords(records);
				return page;
	}

	@Override
	public Page findByLike(String examineeName, String examineeSex,
			String ppositionName, String pageNum) {
		//int num = 1;
		ExamineeDao dao = DaoFactory.getInstance().getExamineeDaoImpl();
		
		int num = Integer.parseInt(pageNum);
		System.out.println("我是Service。---------");
		System.out.println(num);
		/*if(pageNum!=null){
			num = Integer.parseInt(pageNum);
		}
		System.out.println(num);*/
		
		//构造Page对象,因为需要当前页码和总记录条数,所以通过DAO查出记录条数
		Page page = new Page(num, dao.getTotalRecords(examineeName, examineeSex, ppositionName));
		int a = dao.getTotalRecords(examineeName, examineeSex, ppositionName);
	
		System.out.println(a);
		
		//但是Page对象中的分页记录还没有,所有又通过Dao查询分页记录,Dao查询分页记录需要每页开始记录的索引和每页显示的条数,但这两个参数在Page对象中已经计算完毕
		//查询出记录,并设置到Page对象中
		List<Examinee> records = dao.findByLike(examineeName,
				examineeSex,ppositionName,
				page.getStartIndex(), page.getPageSize());
		
		page.setRecords(records);
		return page;
	}


	

	@Override
	public Examinee findById(String examineeId) {
		
		return (Examinee) dao.findById(examineeId);
	}

	@Override
	public boolean updateExaminee(String examineeId, String examineeName,
			String examineePassword, String examineeTelephone,
			String examineeState, String examineeSex, String examineeSchool,
			String examineeEmail, Pposition pposition) throws ExceptionMessege {
		
		Examinee examinee = new Examinee(examineeId, examineeName, examineePassword, examineeTelephone, examineeState, examineeSex, examineeSchool, examineeEmail, pposition);
		return dao.update(examinee);
	}

	@Override
	public void saveAllExaminee(List<Examinee> examinees) {
		dao.insertAllExaminee(examinees);
	}
	/**
	 * 获取考生的状态信息
	 * @return
	 */
	@Override
	public List<String> findExamineeState() {
		return dao.findExamineeState();
	}
	
	/**
	 * 在考试的时候改变
	 */
	@Override
	public boolean updateDate(Object obj) {
		return dao.updateDate((Examinee)obj);
	}
}
