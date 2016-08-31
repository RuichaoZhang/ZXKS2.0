package com.capgemini.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.domain.Pposition;
import com.capgemini.domain.TestRule;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.PpsitionService;
import com.capgemini.util.Page;

/**
 * 职位管理的servlet层
 * @author BianBian 2015/11/26
 *
 */
@SuppressWarnings("serial")
public class PositionManagementServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//得到PpositionService的实体类对象
		PpsitionService serviceImpl = ServiceFactory.getInstance().getPpositionServiceImpl();
		//定义message对象
		Message message = null;
		//得到操作数operate
		String operate = request.getParameter("operate");
		//根据得到的操作数operate来判定做什么操作
		
		//增加职位
		if(operate.equals("add_pposition"))
		{
			
			List<Pposition> ppositions = serviceImpl.findPpositionName();
			request.setAttribute("ppositions", ppositions);
			//得到从客户端传过来的参数
			String ppositionName=new String(request.getParameter("ppositionName").getBytes("ISO-8859-1"),"utf-8");
			
			String testruleId = request.getParameter("testruleId");
			request.setAttribute("ppositionName",ppositionName );
			//判断输入的参数是否为空
			if(ppositionName != null && !ppositionName.equals("") && testruleId != null)
			{
				//判断新增的数据是否超过指定字数
				if(ppositionName.length() < 16)
				{
					//判断新增的数据是否已存在于数据库
					if(serviceImpl.judegPposition(ppositionName))
					{
						if(serviceImpl.addPposition(ppositionName, testruleId))
						{
							Page page = serviceImpl.findPageRecords("1");
							request.setAttribute("page", page);
							//新增成功
							message = new Message("add_pposition","新增职位成功");
							//将message放入request并且完成页面跳转
							request.setAttribute("message", message);
							RequestDispatcher rd = request.getRequestDispatcher("find_Pposition.jsp");
							rd.forward(request, response);
						}
						else 
						{
							//新增失败
							message = new Message("add_pposition","新增职位失败");
							//将message放入request并且完成页面跳转
							request.setAttribute("message", message);
							RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=find_testRule");
							rd.forward(request, response);
						}
					}
					else {
						
						//新增失败
						message = new Message("add_pposition","职位已存在，请重新输入");
						//将message放入request并且完成页面跳转
						request.setAttribute("message", message);
						RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=find_testRule");
						rd.forward(request, response);
						
						}
				}
				else 
				{
					//新增失败
					message = new Message("add_pposition","新增职位的输入超过指定字数限定，请重新输入");
					//将message放入request并且完成页面跳转
					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=find_testRule");
					rd.forward(request, response);
				}
			}
			else {
				//新增失败
				message = new Message("add_pposition","新增职位输入不能为空");
				//将message放入request并且完成页面跳转
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=find_testRule");
				rd.forward(request, response);
			}
				
			
			
		}
		
		//修改职位
		else if(operate.equals("update_pposition"))
		{
			//从客户端获得参数
			String ppositionId = request.getParameter("ppositionId");
			String ppositionName=new String(request.getParameter("ppositionName").getBytes("ISO-8859-1"),"utf-8");
			String testruleId = request.getParameter("testruleId");
			
			try {
				//判断从客户端传过来的参数是否为空
				if(ppositionName != null && !ppositionName.equals("") && testruleId != null)
				{
					//判断修改时输入的参数长度是否超过16
					if(ppositionName.length() < 16)
					{
						System.out.println(ppositionName);
						Pposition pposition = serviceImpl.findByPposition(ppositionName);
						if(pposition == null || pposition.getPpositionId().equals(ppositionId))
						{
								//判断操作是否成功
							if(serviceImpl.updatePposition(ppositionId, ppositionName, testruleId))
							{
								Page page = serviceImpl.findPageRecords("1");
								request.setAttribute("page", page);
								//修改成功
								message = new Message("update_pposition","修改职位成功");
								//将message放入request并且完成页面跳转
								request.setAttribute("message", message);
								RequestDispatcher rd = request.getRequestDispatcher("find_Pposition.jsp");
								rd.forward(request, response);
							}
							else 
							{
								//修改失败
								message = new Message("update_pposition","修改职位失败");
								//将message放入request并且完成页面跳转
								request.setAttribute("message", message);
								RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=jump_updatePposition");
								rd.forward(request, response);
							}
						}
					else 
					{
						//修改失败
						message = new Message("update_pposition","修改职位重复");
						//将message放入request并且完成页面跳转
						request.setAttribute("message", message);
						RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=jump_updatePposition");
						rd.forward(request, response);
					}
							
					}
					else {
						//修改失败
						message = new Message("update_pposition","修改职位时输入字符长度不能超过16");
						//将message放入request并且完成页面跳转
						//request.setAttribute("message", message);
						RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=jump_updatePposition");
						rd.forward(request, response);
					}
					
					
				}
				else 
				{
					//修改失败
					message = new Message("update_pposition","修改职位时输入参数不能为空");
					//将message放入request并且完成页面跳转
					//request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=jump_updatePposition");
					rd.forward(request, response);
				}
			} catch (ExceptionMessege e) {
				//修改失败
				message = new Message("update_pposition","修改职位失败");
				//将message放入request并且完成页面跳转
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("/PositionManagementServlet?operate=jump_updatePposition");
				rd.forward(request, response);
				
				e.printStackTrace();
			}
			
		}
		
		//删除职位
		else if(operate.equals("delete_pposition"))
		{
			//接收从客户端传过来的数据
			String ppositionId = request.getParameter("ppositionId");
			//判断从客户端传过来的数据是否为空
			if(ppositionId != null && !ppositionId.equals(""))
			{
				boolean flag = serviceImpl.deletePposition(ppositionId);
				if(flag)
				{
					Page page = serviceImpl.findPageRecords("1");
					request.setAttribute("page",page );
					//删除成功
					message = new Message("delete_pposition","删除职位成功");
				}
				else 
				{
					//删除失败
					message = new Message("delete_pposition","删除职位失败");
				}
			}
			else 
			{
				//删除失败
				message = new Message("delete_pposition","数据为空，删除职位失败");
			}
			request.setAttribute("message",message);
			Page page = serviceImpl.findPageRecords("1");
			request.setAttribute("page", page);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("find_Pposition.jsp");
			requestDispatcher.forward(request, response);
		}
		//对职位进行全部查询
		else if(operate.equals("findAll_pposition"))
		{
			List<Pposition> ppositions = serviceImpl.findPpositionName();
			request.setAttribute("ppositions", ppositions);
			//从客户端获得参数
			String pageNum = request.getParameter("pageNum");
			System.out.println("servlet-----------------");
			System.out.println(pageNum);
			Page page = serviceImpl.findPageRecords(pageNum);
			request.setAttribute("page", page);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("find_Pposition.jsp");
			requestDispatcher.forward(request, response);
		}
		
		//通过职位名称对职位进行查询
		else if(operate.equals("find_pposition"))
		{
			
			//获得从客户端的参数
			String pageNum = request.getParameter("pageNum");
			String ppositionName=new String(request.getParameter("ppositionName").getBytes("ISO-8859-1"),"utf-8");
			request.setAttribute("ppositionName", ppositionName);
			Page page = serviceImpl.findPposition(ppositionName,pageNum);
			request.setAttribute("page",page);
				if(page != null)
				{
					//查询成功
					message = new Message("find_pposition","查询职位成功");
					//request.setAttribute("page",page );
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("findByPpositionName.jsp");
					requestDispatcher.forward(request, response);
				}
				else 
				{
					//查询失败
					message = new Message("find_pposition","查询职位失败");
					//将message传递到下一页界面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("find_Pposition.jsp");
					requestDispatcher.forward(request, response);
				}
				
		}
		
		//通过职位Id对职位进行查询
		else if(operate.equals("findById_pposition"))
		{
            List<Pposition> ppositions =  serviceImpl.findTestRule();
			
			request.setAttribute("ppositions", ppositions);
			//从客户端获得参数
			
			String ppositionId = request.getParameter("ppositionId");
		
			//定义Pposition对象来接收通过职位名称查出来的对象
			Pposition pposition = serviceImpl.findByPpositoinId(ppositionId);
			
			request.setAttribute("pposition", pposition);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("update_Pposition.jsp");
			requestDispatcher.forward(request, response);
		}
		
		//查询试卷
		else if(operate.equals("find_testRule")) 
		{
			List<Pposition> ppositions =  serviceImpl.findTestRule();
			
			request.setAttribute("ppositions", ppositions);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("add_Pposition.jsp");
			requestDispatcher.forward(request, response);
		}
		
		//判断职位
		else if(operate.equals("jump_updatePposition"))
		{
			List<Pposition> ppositions =  serviceImpl.findTestRule();
			request.setAttribute("ppositions", ppositions);
			String ppositionId = request.getParameter("ppositionId");
			String ppositionName=new String(request.getParameter("ppositionName").getBytes("ISO-8859-1"),"utf-8");
			String testRuleId = request.getParameter("testruleId");
			TestRule testRule = new TestRule();
			testRule.setTestRuleId(testRuleId);
			Pposition pposition = new Pposition();
			pposition.setPpositionId(ppositionId);
			pposition.setPpositionName(ppositionName);
			pposition.setTestrule(testRule);
			request.setAttribute("pposition", pposition);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("update_Pposition.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	}

}
