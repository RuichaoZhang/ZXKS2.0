package com.capgemini.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.TestTypeService;
import com.capgemini.util.Page;

/**
 * 试题类型管理模块
 * 
 * @author chao538 since 2015-11-24
 */
@SuppressWarnings("serial")
public class TestTypeManagerServlet extends HttpServlet {

	/** 根据ServiceFactory得到TestTypeServiceImpl */
	private TestTypeService serviceImpl = ServiceFactory.getInstance()
			.getTestTypeServiceImpl();

	/** 定义一个Message对象 */
	Message message = null;

	/**
	 * 在doGet里面处理该模块
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		// 得到客户端传过来的操作数的内容
		String operate = request.getParameter("operate");

		// 打印操作数
		System.out.println(operate);

		// 通过判断判断操作数的内容来判断去执行具体的增删该查的代码
		if (operate.equals("add_testType")) {

			// 得到客户端传来的参数试题类型名称
			String testTypeName = new String(request.getParameter(
					"testTypeName").getBytes("ISO-8859-1"), "utf-8");

			System.out.println(testTypeName);

			System.out.println(testTypeName);
			// 判断客户端传来的是否为空
			if (testTypeName != null && !testTypeName.equals("")) {
				if (serviceImpl.findTestType(testTypeName) == null) {

					// serviceImpl调用新增方法新增试题类型
					if (serviceImpl.addTestType(testTypeName)) {

						Page page = serviceImpl.findPageRecords("1");
						request.getSession().setAttribute("page", page);

						// 新增成功则提示新增成功
						message = new Message("add_testType", "新增成功");
						// 将message对象发送到下一个页面
						request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("find_testType.jsp");
						requestDispatcher.forward(request, response);
					} else {

						// 失败则提示新增失败
						message = new Message("add_testType", "新增失败");
						// 将message对象发送到下一个页面
						request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("add_testtype.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {
					// 新增失敗则提示新增失敗
					message = new Message("add_testType", "試題類型已存在,請重新輸入");
					// 将message对象发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("add_testtype.jsp");
					requestDispatcher.forward(request, response);
				}
			} else {
				// 如果输入数据为空,则将新建为空的message对象
				message = new Message("add_testType", "新增是试题类型不能为空");
				// 将message对象发送到下一个页面
				request.setAttribute("message", message);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("add_testtype.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		// 如果为更新操作
		else if (operate.equals("update_testType")) {

			// 获取浏览器传来的表单数据
			String testTypeId = request.getParameter("testTypeId");
			String testTypeName = new String(request.getParameter(
					"testTypeName").getBytes("ISO-8859-1"), "utf-8");
			TestType testType = new TestType(testTypeId, testTypeName);
			request.setAttribute("testType", testType);
			try {

				// 调用serviceImpl执行更新
				if (testTypeName != null && !testTypeName.equals("")) {

					// 如果傳入的參數長度小于16
					if (testTypeName.length() < 16) {
						if (serviceImpl.findTestType(testTypeName) == null) {

							// 如果服務層執行成功
							if (serviceImpl.updateTestType(testTypeId,
									testTypeName)) {
								Page page = serviceImpl.findPageRecords("1");
								request.getSession().setAttribute("page", page);

								// 将消息封装
								message = new Message("update_testType",
										"修改试题类型成功");

								// 将message对象发送到下一个页面
								request.setAttribute("message", message);
								RequestDispatcher requestDispatcher = request
										.getRequestDispatcher("find_testType.jsp");
								requestDispatcher.forward(request, response);
							}

							// 如果參數長度大於16
						} else {
							// 将消息封装
							message = new Message("update_testType",
									"该试题类型已存在,请重新输入");
							// 将message对象发送到下一个页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request
									.getRequestDispatcher("update_testType.jsp");
							requestDispatcher.forward(request, response);
						}

						// 如果輸入參數為空
					} else {

						// 将消息封装
						message = new Message("update_testType",
								"该试题类型已存在,请重新输入");

						// 将message对象发送到下一个页面
						request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("update_testType.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {

					// 将消息封装
					message = new Message("update_testType", "修改试题类型时输入不能为空");

					// 将message对象发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("update_testType.jsp");
					requestDispatcher.forward(request, response);
				}

				// 捕捉到異常並處理
			} catch (ExceptionMessege e) {

				// 失败则提示修改失败
				message = new Message("update_testType", "修改失败");
				// 将message对象发送到下一个页面
				request.setAttribute("message", message);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("update_testType.jsp");
				requestDispatcher.forward(request, response);
				// 打印堆栈信息
				e.printStackTrace();
			}
		}

		// 如果操作为删除操作
		else if (operate.equals("delete_testType")) {
			// 接受客户端的数据
			String testTypeId = request.getParameter("testTypeId");
			// 如果客户端传来的数据不为空,则serviceImpl调用删除方法执行删除
			if (testTypeId != null && !testTypeId.equals("")) {
				// 定义一个变量判断删除是否成功
				System.out.println("-----------------");
				System.out.println(testTypeId);
				System.out.println("-----------------");
				boolean flag = serviceImpl.deleteTestType(testTypeId);

				// 如果删除成功
				if (flag) {
					Page page = serviceImpl.findPageRecords("1");
					request.getSession().setAttribute("page", page);
					// 删除成功则提示删除成功
					message = new Message("delete_testType", "删除成功");
				} else {

					Page page = serviceImpl.findPageRecords("0");
					request.setAttribute("page", page);
					// 失败则提示删除失败
					message = new Message("delete_testType", "删除失败");
				}
			} else {

				// 失败则提示删除失败
				message = new Message("delete_testType", "数据为空,删除失败");
			}

			// 将message对象发送到下一个页面
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("find_testType.jsp");
			requestDispatcher.forward(request, response);
		} else if (operate.equals("findAll_testType")) {

			// 分页
			String pageNum = request.getParameter("pageNum");
			System.out.println(pageNum);
			Page page = serviceImpl.findPageRecords(pageNum);
			request.getSession().setAttribute("page", page);
			System.out.println("我是Servlet");
			System.out.println(pageNum);

			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("find_testType.jsp");
			requestDispatcher.forward(request, response);
		}

		// 如果为查找操作(根据testName查找)
		else if (operate.equals("find_testType")) {

			// 得到客户端来的数据
			String testTypeName = new String(request.getParameter(
					"testTypeName").getBytes("ISO-8859-1"), "utf-8");

			// 如果客户端传来的数据不为空则执行
			if (testTypeName != null && !testTypeName.equals("")) {

				// 定义TestType对象来接受serviceImpl查询出来的对象
				TestType testType = serviceImpl.findTestType(testTypeName);

				// 如果查出来的数据不为空,曾将其放入request并跳至全查页面
				if (testType != null) {

					// 成功是定义message并且将其放入request
					message = new Message("find_testType", "查询成功");
					request.setAttribute("testType", testType);
				}
			} else {

				// 失败时定义message
				message = new Message("find_testType", "查询失败,请输入试题类型名称");
			}

			// 将message放入request并且完成页面跳转
			request.setAttribute("message", message);
			RequestDispatcher rd = request
					.getRequestDispatcher("find_testType.jsp");
			rd.forward(request, response);
		}

		// 如果操作为根据Id查找
		else if (operate.equals("findById_testType")) {

			// 定义试题类型Id
			String testTypeId = request.getParameter("testTypeId");

			// 调用服务层方法查询
			TestType testType = serviceImpl.findByTestTypeId(testTypeId);

			// 将查询到的数据放入request,并发送
			request.setAttribute("testType", testType);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/update_testType.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}