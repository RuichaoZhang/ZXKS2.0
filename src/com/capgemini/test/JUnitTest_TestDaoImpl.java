package com.capgemini.test;

import java.util.List;

import org.junit.Test;

import com.capgemini.dao.PpositionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.domain.Pposition;
import com.capgemini.domain.TestItem;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.factory.DaoFactory;

/**
 * 关于生成试卷的方法的测试
 * @author chao538
 *
 */
public class JUnitTest_TestDaoImpl {
	
	@Test
	public void test() {
		TestDao testDao = DaoFactory.getInstance().getTestDaoImpl();
		PpositionDao ppositionDao = DaoFactory.getInstance().getPpositionDaoImpl();
		Pposition pposition = (Pposition) ppositionDao.findById("123456789");
		TestRule testRule = pposition.getTestrule();
		List<TestRuleItem> items = testRule.getTestRuleItemList();
		List<com.capgemini.domain.Test> tests = testDao.generateTest(items);
		for (com.capgemini.domain.Test test : tests) {

			//打印试题
			System.out.println("aa");
			System.out.println(test);
			List<TestItem> testItems = test.getTestItemList();
			
			//打印试题条目
			System.out.println("BBBB");
			System.out.println(testItems);
		}
	}
}
