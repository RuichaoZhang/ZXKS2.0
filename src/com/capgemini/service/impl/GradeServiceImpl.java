package com.capgemini.service.impl;

import java.util.List;
import com.capgemini.dao.GradeDao;
import com.capgemini.domain.Grade;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.GradeService;
import com.capgemini.util.Config;
import com.capgemini.util.Page;

/**
 * TestTypeService的实现类
 * @author Administrator 
 * 
 */
public class GradeServiceImpl implements GradeService{

	//通过DaoFactory得到dao
    GradeDao dao = DaoFactory.getInstance().getGradeDaoImpl();
    

    /**
	 * 根据用户页码返回封装了分页有关数据的对象
	 * 
	 * @param   pageNum
	 * @return  返回查询成绩集合及分页信息
	 */
	@Override
	public Page findPageRecords(String pageNum) {
		
		//默认值
		int num = Integer.parseInt(pageNum);
		
		//得到GradeDao
		//GradeDao dao = DaoFactory.getInstance().getGradeDaoImpl();
		
		//构造Page对象,因为需要当前页码和总记录条数,所以通过DAO查出记录条数
		int totalRecords = dao.getTotalRecords(null,null,null,null,Config.NOPASSINGEXAMING);
		
		Page page = new Page(num, totalRecords);
		
	
		System.out.println("我是service-------------");
		
		//但是Page对象中的分页记录还没有,所有又通过Dao查询分页记录,Dao查询分页记录需要每页开始记录的索引和每页显示的条数,但这两个参数在Page对象中已经计算完毕
		//查询出记录,并设置到Page对象中
		List<Grade> records = dao.findPageRecords(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);
		return page;
	}

	/**
	 * 模糊查询，根据用户页码返回封装了分页有关数据的对象
	 * 
	 * @param   传入查询的条件examineeName, examineeSex, examineeSchool, ppositionName, examineeState, pageNum
	 * @return  返回按条件查询出的成绩集合及分页信息
	 */
	@Override
	public Page findByLike(String examineeName, String examineeSex,
			String examineeSchool, String ppositionName, String examineeState, String pageNum) {
				//得到GradeDao
				GradeDao dao = DaoFactory.getInstance().getGradeDaoImpl();
				
				int num = Integer.parseInt(pageNum);
				System.out.println("我是Service。---------");
				System.out.println(num);
				//构造Page对象,因为需要当前页码和总记录条数,所以通过DAO查出记录条数
				Page page = new Page(num, dao.getTotalRecords(examineeName, examineeSex, examineeSchool, ppositionName,  examineeState));
				int a = dao.getTotalRecords(examineeName, examineeSex, examineeSchool, ppositionName,  examineeState);
				System.out.println("我是service.-------------");
				System.out.println(a);
				
				//但是Page对象中的分页记录还没有,所有又通过Dao查询分页记录,Dao查询分页记录需要每页开始记录的索引和每页显示的条数,但这两个参数在Page对象中已经计算完毕
				//查询出记录,并设置到Page对象中
				List<Grade> records = dao.findByLike(examineeName,
						examineeSex,examineeSchool,ppositionName,examineeState,
						page.getStartIndex(), page.getPageSize());
				page.setRecords(records);
				return page;
	}
	
	/**
	 * 查询职位
	 * 
	 * @param  无
	 * @return 返回grade集合，包含职位信息
	 */
	public List<Grade> findPposition()
	{
		return dao.findPposition();
	}

}
