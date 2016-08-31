package com.capgemini.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.domain.Grade;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.GradeService;
import com.capgemini.util.Page;

/**
 * 成绩管理模块
 * 
 * @author Administrator
 * @since 2015-11-25
 */
@SuppressWarnings("serial")
public class GradeManagementServlet extends HttpServlet {

	/** 根据ServiceFactory得到GradeServiceImpl */
	private GradeService serviceImpl = ServiceFactory.getInstance()
			.getGradeServiceImpl();

	/** 定义一个Message对象 */
	Message message = null;

	/**
	 * 在doGet里面处理该模块
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 传值编码
		response.setContentType("text/html;charset=UTF-8");
		// 得到客户端传过来的操作数的内容
		String operate = request.getParameter("operate");

		// 打印操作数
		System.out.println(operate);
		
		// 通过判断判断操作数的内容来判断去执行具体的代码
		// 若为一览查询
		if (operate.equals("findAll_grade")) {

			// 分页
			String pageNum = request.getParameter("pageNum");
			System.out.println(pageNum);
			Page page = serviceImpl.findPageRecords(pageNum);
			request.setAttribute("page", page);

			// 取得职位信息（下拉框列表）
			List<Grade> grades = serviceImpl.findPposition();
			request.setAttribute("grades", grades);

			// System.out.println(page != null);
			// 如果查出来的数据不为空,曾将其放入request并跳至全查页面
			if (page != null) {

				// 成功是定义message并且将其放入request
				message = new Message("findAll_grade", "查询成功");
				request.setAttribute("page", page);
			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("find_grade.jsp");
			dispatcher.forward(request, response);
		}

		// 若为模糊查询
		else if (operate.equals("findByLike_grade")) {

			// 取得查询条件
			
			String pageNum = request.getParameter("pageNum");
			String examineeSchoolP = request.getParameter("examineeSchool");
			String examineeName = null;
			String examineeSex = null;
			String examineeSchool = null;
			String ppositionName = null;
			String examineeState = null;
			if(examineeSchoolP != null){
				examineeName=new String(request.getParameter("examineeName").getBytes("ISO-8859-1"),"utf-8");
				examineeSex=new String(request.getParameter("examineeSex").getBytes("ISO-8859-1"),"utf-8");
				ppositionName=new String(request.getParameter("ppositionName").getBytes("ISO-8859-1"),"utf-8");
				examineeSchool=new String(request.getParameter("examineeSchool").getBytes("ISO-8859-1"),"utf-8");
				examineeState=new String(request.getParameter("examineeState").getBytes("ISO-8859-1"),"utf-8");
			}

			// 输出查询条件
			System.out.println("Num:" + pageNum);
			System.out.println("examineeName:" + examineeName);
			System.out.println("examineeSex:" + examineeSex);
			System.out.println("examineeSchool:" + examineeSchool);
			System.out.println("ppositionName:" + ppositionName);
			System.out.println("examineeState:" + examineeState);

			// 得到职位信息，下拉框列表信息
			List<Grade> grades = serviceImpl.findPposition();
			request.setAttribute("grades", grades);

			// 调用模糊查询方法
			Page page = serviceImpl.findByLike(examineeName, examineeSex,
					examineeSchool, ppositionName, examineeState, pageNum);
			request.setAttribute("page", page);

			// 给JSP发送数据
			request.setAttribute("examineeName", examineeName);
			request.setAttribute("examineeSex", examineeSex);
			request.setAttribute("ppositionName", ppositionName);
			request.setAttribute("examineeSchool", examineeSchool);
			request.setAttribute("examineeState", examineeState);

			// System.out.println(page != null);
			// 如果查出来的数据不为空,曾将其放入request并跳至全查页面
			if (page != null) {

				// 成功是定义message并且将其放入request
				message = new Message("findByLike_grade", "查询成功");
				request.setAttribute("page", page);
			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("findByLike_grade.jsp");
			dispatcher.forward(request, response);
		}
	}

}