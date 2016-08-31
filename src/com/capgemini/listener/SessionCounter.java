package com.capgemini.listener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;  
import javax.servlet.http.HttpSessionEvent;  

import com.capgemini.dao.ExamineeDao;
import com.capgemini.domain.ExaminationPaper;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.Test;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.DaoFactory;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.service.OnlineTestService;
import com.capgemini.util.Config;

/**
 * Session監聽器
 * @author chao538
 *
 */
public class SessionCounter implements HttpSessionListener {  
	/**得到ExamineeDao*/
	private ExamineeDao examineeDao = DaoFactory.getInstance().getExamineeDaoImpl();
	
	/**得到onlineTestService*/
	private OnlineTestService onlineTestService = ServiceFactory.getInstance().getOnlineTestServiceImpl();
	
	/* Session创建事件 */  
	public void sessionCreated(HttpSessionEvent se) {  
	}  
	/* Session失效事件 */  
	public void sessionDestroyed(HttpSessionEvent se) {  
		System.out.println("++++++++++++++++++++++++");
		//得到Session
		HttpSession session = se.getSession();
		//當session清空時更改考生的狀態給未通過,並且給成績裡面置0
		Examinee examinee = (Examinee) session.getAttribute("examinee");
		if(examinee != null){
			System.out.println("______________________________");
			//設置考生的狀態為未通過
			examinee.setExamineeState(Config.NOPASSINGEXAMING);
			try {
				//更改考生的狀態
				examineeDao.update(examinee);
			} catch (ExceptionMessege e) {
				e.printStackTrace();
			}
			ExaminationPaper examinationPaper = (ExaminationPaper) session.getAttribute("examinationPaper");
			if(examinationPaper != null){
				System.out.println("----------------------------");
				List<Test> tests = examinationPaper.getTestList();
				Set<String> testIds = new HashSet<String>();
				for (Test test : tests) {
					String testId = test.getTestId();
					testIds.add(testId);
				}
				onlineTestService.setGrade(examinee, 0, testIds);
			}
		}
	}  
}  