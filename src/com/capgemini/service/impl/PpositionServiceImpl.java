package com.capgemini.service.impl;

import java.util.List;

import com.capgemini.dao.PpositionDao;
import com.capgemini.domain.Pposition;
import com.capgemini.domain.TestRule;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.service.PpsitionService;
import com.capgemini.util.Page;

/**
 * 职位管理Service层的实现
 * @author BianBian 2015/11/26
 *
 */
public class PpositionServiceImpl implements PpsitionService{
	//得到Pposition的Dao实现类
	PpositionDao dao = DaoFactory.getInstance().getPpositionDaoImpl();

	/**
	 * 增加职位
	 */
	@Override
	public boolean addPposition(String ppositionName, String testruleId) {
		Pposition pposition = new Pposition();
		pposition.setPpositionName(ppositionName);
		TestRule testRule = new TestRule();
		testRule.setTestRuleId(testruleId);
		pposition.setTestrule(testRule);
		return dao.save(pposition);
	}

	/**
	 * 删除职位
	 */
	@Override
	public boolean deletePposition(String ppositionId) {
		
		return dao.delete(ppositionId);
	}

	/**
	 * 修改职位
	 * @throws ExceptionMessege 
	 */
	@Override
	public boolean updatePposition(String ppositionId, String ppositionName,
			String testruleId) throws ExceptionMessege {
		TestRule testRule = new TestRule();
		testRule.setTestRuleId(testruleId);
		Pposition pposition = new Pposition(ppositionId,ppositionName,testRule);
		return dao.update(pposition);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page findPageRecords(String pageNum) {
		
		int num = Integer.parseInt(pageNum);
		//创建Page对象
		Page page = new Page(num, dao.getTotalRecords(null));
		//得到分页查询数据
		List<Pposition> records = dao.findPageRecords(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);
		return page;
	}

	/**
	 * 通过条件查询
	 */
	@Override
	public Page findPposition(String ppositionName,String pageNum) {
		int num = Integer.parseInt(pageNum);
		Page page = new Page(num, dao.getTotalRecords(ppositionName));
		List<Pposition> records = dao.findByPpositionName(ppositionName,page.getStartIndex(),page.getPageSize());
		page.setRecords(records);
		return page;

	}

	/**
	 * 通过ID查询
	 */
	@Override
	public Pposition findByPpositoinId(String ppositionId) {
		
		return (Pposition) dao.findById(ppositionId);
	}
	
	/**
	 * 查询试卷
	 */
	public List<Pposition> findTestRule()
	{
		return dao.findTestRule();
	}

	/**
	 * 查询职位表中的所有信息
	 */
	@Override
	public List<Pposition> findPposition() {
		
		return dao.findPposition();
	}

	/**
	 * 通过职位名称查询
	 */
	@Override
	public List<Pposition> findToPposition(String ppositionName) {
		
		return dao.findToPposition(ppositionName);
	}

	/**
	 * 查询职位名称
	 */
	@Override
	public List<Pposition> findPpositionName() {
		
		return dao.findPpositionName();
	}

	/**
	 * 对输入的职位名称进行判断
	 */
	@Override
	public boolean judegPposition(String ppositionName) {
		
		return dao.judgePposition(ppositionName);
	}
	
	/**
	 * 通过职位名称查询
	 */
	public Pposition findByPposition(String ppositionName)
	{
		return dao.findByPposition(ppositionName);
	}

}
