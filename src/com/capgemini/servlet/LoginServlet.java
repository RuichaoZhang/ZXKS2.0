package com.capgemini.servlet;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.domain.Examinee;
import com.capgemini.domain.Hr;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.ExamineeService;
import com.capgemini.service.GradeService;
import com.capgemini.service.HrService;
import com.capgemini.service.LoginService;
import com.capgemini.util.Config;
import com.capgemini.util.MD5Util;
import com.capgemini.util.Page;
import com.capgemini.util.ProduceVerificationCode;
import com.capgemini.util.SendMail;

/**
 * 登录
 * @author chao538
 * @since 2015-11-24
 * 张瑞超在2015-12-16添加发送邮件验证的方法
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4204065219399965289L;
	
	/**得到登录服务对象*/
	private LoginService loginService = ServiceFactory.getInstance().getLoginServiceImpl();
	
	/**得到考生服务层对象*/
	private ExamineeService examineeService = ServiceFactory.getInstance().getExamineeServiceImpl();
	
	/**得到Hr服务层对象*/
	private HrService hrService = ServiceFactory.getInstance().getHrServiceImpl();

	/**得到Grade服务层对象*/
	private GradeService gradeService = ServiceFactory.getInstance().getGradeServiceImpl();

	/**得到Hr对象*/
	private Hr hr = null;
	
	/**在doGet中处理注销*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("點擊了註銷");
		
		//判断该操作是不是注销,从而清空Session并且跳到登录界面
		if("logout".equals(request.getParameter("operate"))){
			String user = request.getParameter("user");
			loginService.logout(request, user);
			
			//注销后跳转到登录界面
			RequestDispatcher rd = request
				.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}
	
	/**在doPost中处理登录*/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//得到session
		HttpSession session = request.getSession();
		//调试信息
		System.out.println("点击登录了");
		
		//判断type是不是hr从而条状到hr的登录界面
		if ("hr".equalsIgnoreCase(
				request.getParameter("type"))) {
			
			//得到浏览器发送过来的数据
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			//打印表单数据
			System.out.println(userName+password);
			
			//根据LoginService来判断Hr是否登录成功
			if (loginService.hrLogin(userName.trim(), password).equals(Config.SUCCESS)) {
				
				//得到封装了hr信息的hr对象
				hr = hrService.getHr(userName, password);
				
				//将登录成功后的查出来的hr对象放入request
				request.getSession().setAttribute("hr", hr);
				Page page = gradeService.findPageRecords("1");
				request.setAttribute("page", page);
				
				//登陆成功后跳到hr的菜单页面
				RequestDispatcher rd = request
						.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}else{
				
				//定义一个Message,将错误信息封装
				Message message = new Message("login", "Hr用户名密码错误");
				request.setAttribute("message", message);
				
				//跳转到原网页
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}else if("examinee".equalsIgnoreCase(
				request.getParameter("type"))){
			
			//得到浏览器发来的用户名和密码
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			//打印用户名和密码
			System.out.println(userName+ password);
			
			//判断考生是否登录成功
			if (loginService.examineeLogin(userName, MD5Util.encode(password)).equals(Config.SUCCESS)) {
				Examinee examinee = examineeService.getExaminee(userName, MD5Util.encode(password));
				if(examinee != null){
					//打印考生登录信息
					System.out.println("我是考生登录");
					System.out.println(examinee);
					//如果考生登录失败给的提示:您现在不可以登录
					try{
						//生成验证码
						String verificationCode = ProduceVerificationCode.getVerificationCode();
						session.setAttribute("verificationCode", verificationCode);
						String content = "您好这是您此次登录的验证码:" + verificationCode + "请您按照界面指示输入正确的验证码以便您完成本次测试.  ";
						//将生成的验证码发送给考生
						SendMail.mail(examinee.getExamineeEmail(), "登陆验证", content);
						request.getSession().setAttribute("examinee", examinee);
						RequestDispatcher rd = request
								.getRequestDispatcher("inform.jsp");
						rd.forward(request, response);
					}
					catch(NullPointerException e){
						//
						Message message = new Message("login", "您的考生状态或者账号密码有误,这可能是您第二次登录此系统,如果您是第一次登录,请联系考场管理员");
						request.setAttribute("message", message);
						
						//跳转到原网页
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					} catch (MessagingException e) {
						e.printStackTrace();
						request.getSession().setAttribute("examinee", examinee);
						RequestDispatcher rd = request
								.getRequestDispatcher("inform.jsp");
						rd.forward(request, response);
					}
				}else{
					Message message = new Message("login", "您的考生状态或者账号密码有误,这可能是您第二次登录此系统,如果您是第一次登录,请联系考场管理员");
					request.setAttribute("message", message);
					
					//跳转到原网页
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			}else{
				Message message = new Message("login", "您的考生状态或者账号密码有误,这可能是您第二次登录此系统,如果您是第一次登录,请联系考场管理员");
				request.setAttribute("message", message);
				
				//跳转到原网页
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
	}
}
