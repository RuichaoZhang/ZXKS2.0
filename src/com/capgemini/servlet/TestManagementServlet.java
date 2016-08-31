package com.capgemini.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.capgemini.domain.Test;
import com.capgemini.domain.TestItem;
import com.capgemini.domain.TestType;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.ExamineeService;
import com.capgemini.service.TestService;
import com.capgemini.service.TestTypeService;
import com.capgemini.util.Config;
import com.capgemini.util.GetUUID;
import com.capgemini.util.Page;

/**
 * 试题管理
 * @author wanghuan
 * @since 2015-11-24
 * 张瑞超加入doPost()方法实现批量导入
 */
@SuppressWarnings("serial")
public class TestManagementServlet extends HttpServlet {
	public TestService testService = ServiceFactory.getInstance().getTestServiceImpl();
	public TestTypeService testTypeService = ServiceFactory.getInstance().getTestTypeServiceImpl();
	public ExamineeService examineeService = ServiceFactory.getInstance().getExamineeServiceImpl();
	/**定义一个Message对象*/
	Message message = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String operate = request.getParameter("operate");

		if ("findAll_test".equals(operate)) {
			// 查询处理先不做，先把修改，删除，增加处理好
			String testSubjectP = request.getParameter("testSubject");
			String testSubject = null;
			String testTypeName = null; 
			String testItemTrue = null;
			if(testSubjectP != null){
				testSubject=new String(request.getParameter("testSubject").getBytes("ISO-8859-1"),"utf-8");
				testTypeName=new String(request.getParameter("testTypeName").getBytes("ISO-8859-1"),"utf-8");
				testItemTrue=new String(request.getParameter("testItemTrue").getBytes("ISO-8859-1"),"utf-8");
				
			}
			String pageNum = request.getParameter("pageNum");
			int pageNumInt = Integer.parseInt(pageNum);
			Page page = testService.findByLikeTest(testSubject, testTypeName,
					testItemTrue, pageNumInt);

			request.setAttribute("page", page);

			// 获取全部的试题类型 在下拉框中显示
			ArrayList<TestType> testTypes = (ArrayList<TestType>) testTypeService
					.findAllTestType();
			request.setAttribute("testTypes", testTypes);

			// 将获取到的查询条件的参数，回显到下一页，将其保存到request中
			request.setAttribute("testSubject", testSubject);
			request.setAttribute("testTypeName", testTypeName);
			request.setAttribute("testItemTrue", testItemTrue);

			RequestDispatcher rd = request
					.getRequestDispatcher("/find_test.jsp");
			rd.forward(request, response);
		} else if ("update_test".equals(operate)) {
			// 获取从查询页面中传递过来的试题的编号
			String testId = request.getParameter("testId");

			// 获取修改页面中，修改后的试题的参数
			String testSubject = request.getParameter("testSubject");
			System.out.println("testSubject = " + testSubject);
			String testTypeName = null;
			String testItemTrue = null;
			String testItemA = null;
			String testItemB = null;
			String testItemC = null;
			String testItemD =null;
			String testScore = null;
			
			String testItemIdA = null;
			String testItemIdB = null;
			String testItemIdC = null;
			String testItemIdD = null;
//			String testScore = request.getParameter("testScore");
			if(testId != null && testSubject == null){
				// 获取全部的试题类型 显示在下拉框中显示
				ArrayList<TestType> testTypes = (ArrayList<TestType>) testTypeService
						.findAllTestType();
				request.setAttribute("testTypes", testTypes);
	
				// 根据Id获取数据库中的试题，将其回显到修改页面
				Test test = testService.findByTestId(testId);
				ArrayList<TestItem> testItems = (ArrayList<TestItem>) test
						.getTestItemList();
				Iterator<TestItem> it = testItems.iterator();
				int count = 1;
				while (it.hasNext()) {
					String testItemCount = "testItem" + count;
					String testItemIdCount = "testItemId" + count;
					TestItem testItem = it.next();
					request.setAttribute(testItemCount,
							testItem.getTestItemContent());
					request.setAttribute(testItemIdCount, testItem.getTestItemId());
					count++;
				}
				request.setAttribute("test", test);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/update_test.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
			if(request.getParameter("testSubject") != null && request.getParameter("testTypeName") != null){
				testSubject = new String(request.getParameter("testSubject").getBytes("ISO-8859-1"),"utf-8");
				testTypeName = new String(request.getParameter("testTypeName").getBytes("ISO-8859-1"),"utf-8");
				testItemTrue = new String(request.getParameter("testItemTrue").getBytes("ISO-8859-1"),"utf-8");
	
				testItemA = new String(request.getParameter("testItemA").getBytes("ISO-8859-1"),"utf-8");
				testItemB = new String(request.getParameter("testItemB").getBytes("ISO-8859-1"),"utf-8");
				testItemC = new String(request.getParameter("testItemC").getBytes("ISO-8859-1"),"utf-8");
				testItemD = new String(request.getParameter("testItemD").getBytes("ISO-8859-1"),"utf-8");
				
				testScore = request.getParameter("testScore");
			
				//将获得的参数存放到request域中，如果增加有错误可以进行回显
				request.setAttribute("testSubject", testSubject);
				request.setAttribute("testItemA", testItemA);
				request.setAttribute("testItemB", testItemB);
				request.setAttribute("testItemC", testItemC);
				request.setAttribute("testItemD", testItemD);
				request.setAttribute("testItemTrue", testItemTrue);
				request.setAttribute("testTypeName", testTypeName);
				request.setAttribute("testScore", testScore);
			}
			// 当得到的所有参数都为null的时候，就返回一个空的页面，进行添加操作，
			// 当得到的参数不为null时，就要将参数的值存入到数据库中
			if (testSubject != null || testItemA != null || testItemB != null
					|| testItemC != null || testItemD != null
					|| testItemTrue != null || testTypeName != null
					|| testScore != null) {

				// 如果试题类型从前台中获取到的是“请选择”，则将其参数职位“空”
				if (testTypeName.equals("请选择")) {
					testTypeName = "";
				}

				// 判断是否有空字符串的参数，添加校验，所有的参数必须不为空字符串
				if (!testSubject.equals("") && !testItemA.equals("")
						&& !testItemB.equals("") && !testItemC.equals("")
						&& !testItemD.equals("") && !testItemTrue.equals("")
						&& !testTypeName.equals("") && !testScore.equals("")) {

					// 实现考生正在考试，不能进行试题的修改操作
					List<String> lists = examineeService.findExamineeState();
					Iterator<String> its = lists.iterator();
//					boolean flag = false;
//					while (its.hasNext()) {
//						if (its.next().equals(Config.EXAMING)) {
//							flag = true;
//						}
//					}
					if (true) {
						// 校验传入的字符串中的唯一约束，试题的题目不能相同，需要从数据库中根据
						// 试题名称查询，如果查询出试题，说明试题已存在，如果没有，则将试题插入
						Test testA = testService.findByTestSubject(testSubject);
						if (testA == null || testA.getTestId().equals(testId)) {
							// 如果插入成功，则跳转到一览页面，如果查询失败，则弹出提示信息，并跳转到
							// 增加页面，重新进行增加
							int testScoreint = Integer.parseInt(testScore);
							Test test1 = new Test();
							test1.setTestId(testId);
							test1.setTestSubject(testSubject);
							test1.setTestType(testTypeService
									.findTestType(testTypeName));
							test1.setTestScore(testScoreint);

							ArrayList<TestItem> list = new ArrayList<TestItem>();
							TestItem testItemAa;
							if (testItemA.equals(testItemTrue)) {
								testItemAa = new TestItem(testItemIdA,
										testItemA, "1", test1);
							} else {
								testItemAa = new TestItem(testItemIdA,
										testItemA, "0", test1);
							}
							TestItem testItemBb;
							if (testItemB.equals(testItemTrue)) {
								testItemBb = new TestItem(testItemIdB,
										testItemB, "1", test1);
							} else {
								testItemBb = new TestItem(testItemIdB,
										testItemB, "0", test1);
							}
							TestItem testItemCc;
							if (testItemC.equals(testItemTrue)) {
								testItemCc = new TestItem(testItemIdC,
										testItemC, "1", test1);
							} else {
								testItemCc = new TestItem(testItemIdC,
										testItemC, "0", test1);
							}
							TestItem testItemDd;
							if (testItemD.equals(testItemTrue)) {
								testItemDd = new TestItem(testItemIdD,
										testItemD, "1", test1);
							} else {
								testItemDd = new TestItem(testItemIdD,
										testItemD, "0", test1);
							}
							list.add(testItemDd);
							list.add(testItemCc);
							list.add(testItemBb);
							list.add(testItemAa);
							test1.setTestItemList(list);

							// 将数据添加到数据库中，如果返回
							if (testService.updateTest(test1)) {
								message = new Message("update_test", "修改试题成功");
								// 将message对象发送到下一个页面
								request.setAttribute("message", message);

								// 新增成功没有提示信息，直接跳转到查询试题页面
								response.sendRedirect(request.getContextPath()
										+ "/TestManagementServlet?operate=findAll_test&pageNum=1");
							} else {
								message = new Message("update_test", "修改试题失败");
								// 将message对象发送到下一个页面
								request.setAttribute("message", message);
								RequestDispatcher requestDispatcher = request
										.getRequestDispatcher("/update_test.jsp");
								requestDispatcher.forward(request, response);
							}
						} else {
							message = new Message("update_test", "试题已存在，请重新输入！");
							// 将message对象发送到下一个页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request
									.getRequestDispatcher("/update_test.jsp");
							requestDispatcher.forward(request, response);
						}
					} else {
						message = new Message("update_test", "考生正在考试，不能修改试题！");
						// 将message对象发送到下一个页面
						request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/update_test.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {
					message = new Message("update_test", "新增试题参数不能为空");
					// 将message对象发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("/update_test.jsp");
					requestDispatcher.forward(request, response);
				}
			} 
			// 从数据库中获取试题，根据获取到的试题编号，从数据库中获取到，将其会会回显到页面中
		} else if ("delete_test".equals(operate)) {
			List<String> list = examineeService.findExamineeState();
			Iterator<String> it = list.iterator();
			boolean flag = false;
			while(it.hasNext()){
				if(it.next().equals(Config.EXAMING)){
					flag = true;
				}
			}
			if(!flag){
				// 获取试题的id，根据id将对应的数据库中的试题以及试题选项删除掉
				String testId = request.getParameter("testId");
				if(testService.deleteTest(testId)){
					message = new Message("delete_test", "删除试题成功！");
					// 将message对象发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher rd = request
							.getRequestDispatcher("/TestManagementServlet?operate=findAll_test&pageNum=1");
					rd.forward(request, response);
				}else{
					message = new Message("delete_test", "删除试题失败！");
					// 将message对象发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher rd = request
							.getRequestDispatcher("/TestManagementServlet?operate=findAll_test&pageNum=1");
					rd.forward(request, response);
				}
			}else{
				message = new Message("delete_test", "考生正在考试，不能删除试题！");
				// 将message对象发送到下一个页面
				request.setAttribute("message", message);
				RequestDispatcher rd = request
						.getRequestDispatcher("/TestManagementServlet?operate=findAll_test&pageNum=1");
				rd.forward(request, response);
			}
		} else if ("add_test".equals(operate)) {

			// 获取全部的试题类型 在下拉框中显示
			ArrayList<TestType> testTypes = (ArrayList<TestType>) testTypeService
					.findAllTestType();
			request.setAttribute("testTypes", testTypes);
			
			String testSubject = null;
			String testTypeName = null;
			String testItemTrue = null;
			String testItemA = null;
			String testItemB = null;
			String testItemC = null;
			String testItemD =null;
			String testScore = null;
			// 获取从页面中得到的增加的试题参数
			if(request.getParameter("testSubject") != null){
				
			
				 testSubject = new String(request.getParameter("testSubject").getBytes("ISO-8859-1"),"utf-8");
				testTypeName = new String(request.getParameter("testTypeName").getBytes("ISO-8859-1"),"utf-8");
				testItemTrue = new String(request.getParameter("testItemTrue").getBytes("ISO-8859-1"),"utf-8");
	
				testItemA = new String(request.getParameter("testItemA").getBytes("ISO-8859-1"),"utf-8");
				testItemB = new String(request.getParameter("testItemB").getBytes("ISO-8859-1"),"utf-8");
				testItemC = new String(request.getParameter("testItemC").getBytes("ISO-8859-1"),"utf-8");
				testItemD = new String(request.getParameter("testItemD").getBytes("ISO-8859-1"),"utf-8");
				
				testScore = request.getParameter("testScore");
			
				//将获得的参数存放到request域中，如果增加有错误可以进行回显
				request.setAttribute("testSubject", testSubject);
				request.setAttribute("testItemA", testItemA);
				request.setAttribute("testItemB", testItemB);
				request.setAttribute("testItemC", testItemC);
				request.setAttribute("testItemD", testItemD);
				request.setAttribute("testItemTrue", testItemTrue);
				request.setAttribute("testTypeName", testTypeName);
				request.setAttribute("testScore", testScore);
			}
			

			// 当得到的所有参数都为null的时候，就返回一个空的页面，进行添加操作，
			// 当得到的参数不为null时，就要将参数的值存入到数据库中
			if (testSubject != null || testItemA != null || testItemB != null
					|| testItemC != null || testItemD != null
					|| testItemTrue != null || testTypeName != null
					|| testScore != null) {
				//如果试题类型从前台中获取到的是“请选择”，则将其参数职位“空”
				if(testTypeName.equals("请选择")){
					testTypeName = "";
				}
				
				// 判断是否有空字符串的参数，添加校验，所有的参数必须不为空字符串
				if (!testSubject.equals("") && !testItemA.equals("")
						&& !testItemB.equals("") && !testItemC.equals("")
						&& !testItemD.equals("") && !testItemTrue.equals("")
						&& !testTypeName.equals("") && !testScore.equals("")) {

					// 校验传入的字符串中的唯一约束，试题的题目不能相同，需要从数据库中根据
					// 试题名称查询，如果查询出试题，说明试题已存在，如果没有，则将试题插入
					if (testService.findByTestSubject(testSubject) == null) {
						// 如果插入成功，则跳转到一览页面，如果查询失败，则弹出提示信息，并跳转到
						// 增加页面，重新进行增加
						// 封装试题对象
						int testScoreint = Integer.parseInt(testScore);
						Test test = new Test();
						test.setTestId(GetUUID.getUUID().toString());
						test.setTestSubject(testSubject);
						test.setTestType(testTypeService
								.findTestType(testTypeName));
						test.setTestScore(testScoreint);
						ArrayList<TestItem> list = new ArrayList<TestItem>();
						TestItem testItemAa;
						if (testItemA.equals(testItemTrue)) {
							testItemAa = new TestItem(GetUUID.getUUID()
									.toString(), testItemA, "1", test);
						} else {
							testItemAa = new TestItem(GetUUID.getUUID()
									.toString(), testItemA, "0", test);
						}
						TestItem testItemBb;
						if (testItemB.equals(testItemTrue)) {
							testItemBb = new TestItem(GetUUID.getUUID()
									.toString(), testItemB, "1", test);
						} else {
							testItemBb = new TestItem(GetUUID.getUUID()
									.toString(), testItemB, "0", test);
						}
						TestItem testItemCc;
						if (testItemC.equals(testItemTrue)) {
							testItemCc = new TestItem(GetUUID.getUUID()
									.toString(), testItemC, "1", test);
						} else {
							testItemCc = new TestItem(GetUUID.getUUID()
									.toString(), testItemC, "0", test);
						}
						TestItem testItemDd;
						if (testItemD.equals(testItemTrue)) {
							testItemDd = new TestItem(GetUUID.getUUID()
									.toString(), testItemD, "1", test);
						} else {
							testItemDd = new TestItem(GetUUID.getUUID()
									.toString(), testItemD, "0", test);
						}
						list.add(testItemDd);
						list.add(testItemCc);
						list.add(testItemBb);
						list.add(testItemAa);
						test.setTestItemList(list);

						// 将数据添加到数据库中，如果返回
						if (testService.addTest(test)) {
							
							message = new Message("add_test", "新增试题成功！");
							// 将message对象发送到下一个页面
							request.setAttribute("message", message);
							// 新增成功没有提示信息，直接跳转到查询试题页面
							response.sendRedirect(request.getContextPath()
									+ "/TestManagementServlet?operate=findAll_test&pageNum=1");
						} else {
							message = new Message("add_test", "新增试题失败");
							// 将message对象发送到下一个页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request
									.getRequestDispatcher("/add_test.jsp");
							requestDispatcher.forward(request, response);
						}
					} else {
						message = new Message("add_test", "试题已存在，请重新输入！");
						// 将message对象发送到下一个页面
						request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/add_test.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {
					message = new Message("add_test", "新增试题参数不能为空");
					// 将message对象发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("/add_test.jsp");
					requestDispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher("/add_test.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	/**
	 * 在dopost方法中实现批量导入
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/html");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		InputStream in = null;
		try {
			
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 普通字段
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					System.out.println(fieldName + "=" + fieldValue);
				} else {
					in = item.getInputStream();
				}
			}
		} catch (org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException e) {
			
		} catch (org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException e) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Test> tests = new ArrayList<Test>();
		
		HSSFWorkbook book = new HSSFWorkbook(in);// 先拿到流
		HSSFSheet sheet = book.getSheetAt(0);// 通过流拿到sheet
		HSSFRow row = null;// 初始化行
		//设置试题条目的初始状态为"0"
		try{
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				Test test = new Test();
				if(row.getCell(0) != null){
					test.setTestSubject(row.getCell(0).toString());
					System.out.println("----------------------------------");
					System.out.println(row.getCell(0).toString());
					List<TestItem> testItemList = new ArrayList<TestItem>();
					
					for(int j = 1; j <= 4; j++){
						TestItem testItem = new TestItem();
						System.out.println("++++++++++++++++++++++++++++++");
						System.out.println(row.getCell(j).toString());
						if(row.getCell(j).toString().equals(row.getCell(5).toString())){
							testItem.setTestItemState("1");
						}else {
							testItem.setTestItemState("0");
						}
						testItem.setTestItemContent(row.getCell(j).toString());
						testItemList.add(testItem);
					}
					//设置试题分数
					test.setTestScore(Integer.parseInt(row.getCell(7).toString().substring(0,1)));
					//设置testItemList
					test.setTestItemList(testItemList);
					
					//构建TestType
					TestType testType = new TestType();
					//设置TestTypeName
					testType.setTestTypeName(row.getCell(6).toString());
					test.setTestType(testType);
					tests.add(test);
				}
				//考生定义一个批量插入的方法
			}
		}catch(Exception e){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("find_test.jsp");
			requestDispatcher.forward(request, response);
		}
		testService.saveAllTest(tests);
		String testSubject = request.getParameter("testSubject");
		String testTypeName = request.getParameter("testTypeName");
		String testItemTrue = request.getParameter("testItemTrue");
		String pageNum = request.getParameter("pageNum");
		
		System.out.println(testSubject);
		System.out.println(testTypeName);
		System.out.println(testItemTrue);
		System.out.println(pageNum);

		int pageNumInt = Integer.parseInt("1");
	
		//Page page = testService.findAllTest(pageNumInt);
		Page page = testService.findByLikeTest(testSubject, testTypeName,testItemTrue, pageNumInt);
		
		request.setAttribute("page", page);
	
		// 获取全部的试题类型 在下拉框中显示
        ArrayList<TestType> testTypes = (ArrayList<TestType>) testTypeService.findAllTestType();
        request.setAttribute("testTypes", testTypes);
        
        //将获取到的查询条件的参数，回显到下一页，将其保存到request中
        request.setAttribute("testSubject", testSubject);
        request.setAttribute("testTypeName", testTypeName);
        request.setAttribute("testItemTrue", testItemTrue);
		
		RequestDispatcher rd = request
		.getRequestDispatcher("/find_test.jsp");
	    rd.forward(request, response);
	}
}
