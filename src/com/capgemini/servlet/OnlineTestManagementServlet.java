package com.capgemini.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.domain.ExaminationPaper;
import com.capgemini.domain.Examinee;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.ExamineeService;
import com.capgemini.service.OnlineTestService;
import com.capgemini.util.Config;

/**
 * 在线考试模块
 * @author chao538
 */
@SuppressWarnings("serial")
public class OnlineTestManagementServlet extends HttpServlet {
	/**通过Service工厂得到在线考试的实现类*/
	private OnlineTestService onlineTestService = ServiceFactory.getInstance().getOnlineTestServiceImpl();
	
	/**通过Service工厂得到考生的实现类*/
	private ExamineeService examineeService = ServiceFactory.getInstance().getExamineeServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//从Session得到考生的信息
		HttpSession session = request.getSession();
		Examinee examineeSession = (Examinee) request.getSession().getAttribute("examinee");
		String sessionVerificationCode = (String) session.getAttribute("verificationCode");
		String requestVerificationCode = request.getParameter("verificationCode");
		if(sessionVerificationCode.equals(requestVerificationCode)){
			String examineeId = examineeSession.getExamineeId();
			Examinee examinee = examineeService.findById(examineeId);
			String examineeState = examinee.getExamineeState();
			if(examineeState.equals(Config.NOEXAMING)){
				String operate = request.getParameter("operate");
				//如果操作数为getExaminationPaper,则生成试卷并发送给考生
				if ("getExaminationPaper".equals(operate)) {
					
					//改变考生的状态为考试中
					examinee.setExamineeState(Config.EXAMING);
					try {
						examineeService.updateExaminee(
								examineeId, 
								examinee.getExamineeName(),
								examinee.getExamineePassword(),
								examinee.getExamineeTelephone(),
								examinee.getExamineeState(), 
								examinee.getExamineeSex(), 
								examinee.getExamineeSchool(),
								examinee.getExamineeEmail(),
								examinee.getPposition()
								);
					} catch (ExceptionMessege e) {
						e.printStackTrace();
					}
					//定义考生对象
	
					//调用服务层的方法得到页面需要显示
					ExaminationPaper examinationPaper = onlineTestService.getExaminationPaper(examineeSession);
					
					//将页面显示的对象发送到页面
					request.getSession().setAttribute("examinationPaper", examinationPaper);
					System.out.println("我要发东西了");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("main_test.jsp");
					requestDispatcher.forward(request, response);
				}
			}else{
				//如果考生之前提交过.则只给他显示成绩
				int grade = onlineTestService.findGrade(examineeId);
				request.setAttribute("grade", grade);
				RequestDispatcher rd = request.getRequestDispatcher("showGrade.jsp");
				rd.forward(request, response);
			}
		}else{
			
			//邮箱验证码的正确与否
			Message message = new Message("verificationCode", "请输入正确的验证码！");
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("inform.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("*****************");
		//通过Session得到考生的信息
		Examinee examineeSession = (Examinee) request.getSession().getAttribute("examinee");

		String examineeId = examineeSession.getExamineeId();
		Examinee examinee = examineeService.findById(examineeId);
		String examineeState = examinee.getExamineeState();
		
		//如果考生的状态是EXAMING,则进行考试成绩生成以及维护数据库
		if(examineeState.equals(Config.EXAMING)){
			int totalTest = Integer.parseInt(request.getParameter("totalTest")) ;
			
			Map<String, String> map = new HashMap<String, String>();
			
			//将所有客户端发过来的testItemId存入List
			for (Integer i = 0; i < totalTest; i++) {
				StringBuffer testItemId = new StringBuffer("testItemId");
				StringBuffer testId = new StringBuffer("testId");
				String testItemIdParameter = request.getParameter((testItemId.append(i.toString()).append("0")).toString());
	 			String testIdParameter = request.getParameter((testId.append(i.toString()).toString()));
				map.put(testIdParameter, testItemIdParameter);
			}
			
			int grade = onlineTestService.getGrade(map);
			Set<String> testIds = map.keySet();
			//传递考生得到考生Id和职位Id,将试题的ID集合发过去用于得到分数计算试卷的总分
			onlineTestService.setGrade(examinee, grade, testIds);
			System.out.println("------------------------------------------------");
			System.out.println(grade);
			request.getSession().setAttribute("grade", grade);
			response.sendRedirect("showGrade.jsp");
		}else{
			//如果考生之前提交过.则只给他显示成绩
			int grade = onlineTestService.findGrade(examineeId);
			request.setAttribute("grade", grade);
			RequestDispatcher rd = request.getRequestDispatcher("showGrade.jsp");
			rd.forward(request, response);
		}
	}
}