package com.capgemini.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capgemini.domain.TestRule;
import com.capgemini.domain.TestRuleItem;
import com.capgemini.domain.TestType;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.TestRuleItemService;
import com.capgemini.service.TestRuleService;
import com.capgemini.service.TestService;
import com.capgemini.service.TestTypeService;
import com.capgemini.util.GetUUID;
import com.capgemini.util.Page;

/**
 * 试卷管理的Servlet层
 * @author bianbian 2015/11/29
 *
 */
@SuppressWarnings("serial")
public class TestRuleManagementServlet extends HttpServlet {
	
	/** 通过Service工厂获得试卷Service层的实现类*/
	TestRuleService testRuleServiceImpl = ServiceFactory.getInstance().getTestRuleServiceImpl();
	
	/** 通过Service工厂获得试卷Service层的实现类*/
	TestService testServiceImpl = ServiceFactory.getInstance().getTestServiceImpl();
	
	/** 通过Service工厂获得试卷类型Service层的实现类*/
	TestRuleItemService testRuleItemServiceImpl = ServiceFactory.getInstance().getTestRuleItemServiceImpl();
	
	/** 通过Service工厂获得试题类型Service层的实现类*/
	TestTypeService testTypeServiceImpl = ServiceFactory.getInstance().getTestTypeServiceImpl();
	/**
	 * 定义一个Message对象
	 */

	Message message = null;

	/**
	 * 在doGet里面处理该模块
	 */
	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 设置字符编码
		 */
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		/**
		 * 获得操作数
		 */
		String operate = request.getParameter("operate");

		// 通过操作数的内容去判断进行增删改查工作
		if (operate.equals("add_testRule")) {
			// 得到客户端传过来的参数
			
			String testRuleNameP = request.getParameter("testRuleName");
			String testRuleName = null;
			String testRuleItemName = null;
			if(testRuleNameP != null){
				testRuleName = new String(request.getParameter("testRuleName").getBytes("ISO-8859-1"),"utf-8");
				testRuleItemName=new String(request.getParameter("stlx").getBytes("ISO-8859-1"),"utf-8");
				
			}
			String testRuleTime = request.getParameter("testRuleTime");
			//从客户端传来的是用","隔开的字符串
			
			String testRuleItemNum = request.getParameter("stsl");
			
			//将得到的参数，存放到request域当中
			request.setAttribute("testRuleName", testRuleName);
			request.setAttribute("testRuleTime", testRuleTime);
			
			// 获取试题类型，将其显示在下拉框中
			ArrayList<TestType> testTypes = (ArrayList<TestType>) testTypeServiceImpl
					.findAllTestType();
			request.setAttribute("testTypes", testTypes);
			
			if (testRuleName != null || testRuleTime != null 
					|| testRuleItemName !=null || testRuleItemNum!=null) {
				
				String testRuleItemNames[] = testRuleItemName.split(",");
				String testRuleItemNums[] = testRuleItemNum.split(",");
				int num1 = testRuleItemNames.length;
				int num2 = testRuleItemNums.length;
				boolean flag = false;
				boolean flag3 = true;
				if(num1 == num2 && num2 == 0 && num1 == 0){
					flag3 = false;
					
				}
				if(num1 == 0){
					testRuleItemNames = new String[num2];
				}
				if(num2 == 0){
					testRuleItemNums = new String[num1];
				}
				if(num1 >num2){
					String test[] = testRuleItemNums;
					testRuleItemNums = new String[num1];
					for(int i = 0 ; i<num2; i++){
						testRuleItemNums[i] = test[i];						
					}
					for(int i = num2 ; i<num1; i++){
						testRuleItemNums[i] = "";						
					}
				}else if(num1 <num2){
					String test[] = testRuleItemNames;
					testRuleItemNames = new String[num2];
					for(int i = 0 ; i<num1; i++){
						testRuleItemNames[i] = test[i];						
					}
					for(int i = num1 ; i<num2; i++){
						testRuleItemNames[i] = "";						
					}				
				}
				
				
				int count = 0;
				for(int i =0 ; i<testRuleItemNames.length ; i++){
					if(!testRuleItemNames[i].equals("") && !testRuleItemNums[i].equals("")){
						count++;
					}
				}
				if(count == testRuleItemNames.length){
					flag = true;
				}
				String testRuleTime1 = testRuleTime.trim();
		        if (!testRuleName.equals("") && !testRuleTime1.equals("") && flag && flag3){
					ArrayList<String> testRuleItems = new ArrayList<String>();
					for (int i = 0; i < testRuleItemNames.length; i++) {
							testRuleItems.add(testRuleItemNames[i] + "_"
									+ testRuleItemNums[i]);
					}
					// 从获取的字符串中将试题类型和试题类型的个数获取到
					String testRuleId = GetUUID.getUUID().toString();
					TestRule testRule = new TestRule(testRuleId, testRuleName,
							Integer.parseInt(testRuleTime), null);
					ArrayList<TestRuleItem> testRuleItemList = new ArrayList<TestRuleItem>();
					TestRuleItem testRuleItem;
					Iterator<String> it = testRuleItems.iterator();
					while (it.hasNext()) {
						String ss = it.next();
						String[] testRuleItemA = ss.split("_");
						testRuleItem = new TestRuleItem(GetUUID.getUUID()
								.toString(), Integer.parseInt(testRuleItemA[1]),
								testTypeServiceImpl.findTestType(testRuleItemA[0]),
								testRule);
						testRuleItemList.add(testRuleItem);

					}

					testRule.setTestRuleItemList(testRuleItemList);
					int testRuleItemInt = Integer.parseInt(testRuleTime);
					
					
					// 判断要新增的试卷是否已经存在于数据库中
					if (testRuleServiceImpl.findByTestRuleName(testRuleName) == null) {
						boolean flag1 = false;
						boolean flag2 = false;
						flag1 = testRuleServiceImpl.addTestRule(testRuleId,
								testRuleName, testRuleItemInt);
						flag2 = testRuleItemServiceImpl
								.addTestRuleItem(testRuleItemList);
						// 判断试卷表是否成功
						if (flag1 && flag2) {
							// 判断试卷类型表是否新增成功
							// 提示新增成功
							message = new Message("add_testRule", "新增成功！");
							// 将message发送到下一个页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request
									.getRequestDispatcher("/TestRuleManagementServlet?operate=findAll_testRule&pageNum=1");
							requestDispatcher.forward(request, response);
						} else {
							// 提示新增失败
							message = new Message("add_testRule", "新增试卷表失败！");
							// 将message发送到下一个页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request
									.getRequestDispatcher("/add_testRule.jsp");
							requestDispatcher.forward(request, response);
						}
					} else {
						// 提示新增失败
						message = new Message("add_testRule", "试卷已存在，请重新输入！");
						// 将message发送到下一个页面
						request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/add_testRule.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {
					// 提示新增失败
					message = new Message("add_testRule", "新增试卷不能为空");
					// 将message发送到下一个页面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("/add_testRule.jsp");
					requestDispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/add_testRule.jsp");
				requestDispatcher.forward(request, response);
			}

		} else if (operate.equals("delete_testRule"))
		{
			//得到从客户端传过来的参数
			String testRuleId = request.getParameter("testRuleId");
			//如果从客户端传过来的数据不为空，则TestRuleServiceImpl调用删除方法进行删除操作
			if(testRuleId != null && !testRuleId.equals(""))
			{
				//定义一个变量判断是否删除成功
				boolean flag2 = testRuleItemServiceImpl.deleteTestRuleItem(testRuleId);
				boolean flag1 = testRuleServiceImpl.deleteTestRule(testRuleId);
				
				
				if(flag1 && flag2)
				{
					Page page = testRuleServiceImpl.findPageRecords("1");
					request.setAttribute("page", page);
					//提示删除成功
					message = new Message("delete_testRule","删除成功");
				}
				else 
				{
					Page page = testRuleServiceImpl.findPageRecords("1");
					request.setAttribute("page", page);
					//提示删除失败
					message = new Message("delete_testRule","删除成功");
				}
				
			}
			else 
			{
				Page page = testRuleServiceImpl.findPageRecords("1");
				request.setAttribute("page", page);
				message = new Message("delete_testRule","数据为空，删除失败");
			}
			//将message对象发送到下一张页面
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("find_testRule.jsp");
			requestDispatcher.forward(request, response);
		}
		else if(operate.equals("update_testRule"))
		{
			//从客户端得到参数
			String testRuleName = null;
			String testRuleItemName = null;
			String testRuleId = request.getParameter("testRuleId");
			String testRuleNameP = request.getParameter("testRuleName");
			String testRuleItemNameP = request.getParameter("stlx");
			if(testRuleNameP != null){
				
				testRuleName = new String(request.getParameter("testRuleName").getBytes("ISO-8859-1"),"utf-8");
				System.out.println("testRuleName" + testRuleName);
				
			}
			
			int testRuleTime = Integer.parseInt(request.getParameter("testRuleTime"));
			//根据试卷Id得到对应试卷类型的个数
			int Num = testRuleItemServiceImpl.findTestRuleItemNum(testRuleId);
			String testRuleItem[] = new String[5];
			for(Integer i = 0;i < Num;i++)
			{
				StringBuffer testRuleItemIdBuffer = new StringBuffer("testRuleItemId");
				StringBuffer testTypeIdBuffer = new StringBuffer("testTypeId");
				StringBuffer testRuleItemNumBuffer = new StringBuffer("testRuleItemNum");
				
				String testRuleItemId = request.getParameter((testRuleItemIdBuffer.append(i.toString()).toString()));
				String testTypeId = request.getParameter((testTypeIdBuffer.append(i.toString()).toString()));
				String testRuleItemNum = request.getParameter((testRuleItemNumBuffer.append(i.toString()).toString()));
				
				String testRuleItemString = testRuleItemId+"_"+testTypeId+"_"+testRuleItemNum;
				testRuleItem[i] = testRuleItemString;
			}
			
		try{
			//判断输入的参数是否为空
			if(testRuleName != null && !testRuleName.equals("") && testRuleTime > 0)
			{
				TestRule testRule = testRuleServiceImpl.findByTestRuleName(testRuleName);
				//判断输入的参数是否重复
				if(testRule == null || testRule.getTestRuleId().equals(testRuleId))
				{
					//判断输入参数长度是否小于16
					if(testRuleName.length() < 16)
					{
						boolean flag1 = testRuleServiceImpl.updateTestRule(testRuleId, testRuleName, testRuleTime);
						
						boolean flag2 = testRuleItemServiceImpl.updateTestRuleItem(testRuleItem,Num);
						if(flag1 & flag2)
						{
							Page page = testRuleServiceImpl.findPageRecords("1");
							request.setAttribute("page", page);
							//修改成功
							message = new Message("update_testRule","修改试卷成功");
							//将message对象发送到下一张页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("find_testRule.jsp");
							requestDispatcher.forward(request, response);
						}
						else 
						{
							//修改失败
							message = new Message("update_testRule","修改试卷失败");
							//将message对象发送到下一张页面
							request.setAttribute("message", message);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TestRuleManagementServlet?operate=jump_updateTestRule");
							requestDispatcher.forward(request, response);
						}
					}
					else 
					{
						//修改失败
						message = new Message("update_testRule","修改时输入试卷名称长度不能超过16");
						//将message对象发送到下一张页面
						//request.setAttribute("message", message);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TestRuleManagementServlet?operate=jump_updateTestRule");
						requestDispatcher.forward(request, response);
					}
				}
				
				else {
					//修改失败
					message = new Message("update_testRule","修改的试卷重复");
					//将message对象发送到下一张页面
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TestRuleManagementServlet?operate=jump_updateTestRule");
					requestDispatcher.forward(request, response);
				}
				
			}
			else 
			{
				//修改失败
				message = new Message("update_testRule","修改时输入试卷时输入不能为空");
				//将message对象发送到下一张页面
				//request.setAttribute("message", message);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TestRuleManagementServlet?operate=jump_updateTestRule");
				requestDispatcher.forward(request, response);
			}
		}catch(ExceptionMessege e)
		{
			//失败则提示修改失败
			message = new Message("update_testRule", "修改失败");
			//将message对象发送到下一个页面
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TestRuleManagementServlet?operate=jump_updateTestRule");
			requestDispatcher.forward(request, response);
			//打印堆栈信息
			e.printStackTrace();
		}
	}
		//如果操作为通过名称查询	
		else if(operate.equals("find_testRule"))
		{
			//从数据库中取出所有的试卷名称并发给前台
			List<TestRule> testRules = testRuleServiceImpl.findTestRuleName();
			request.setAttribute("testRules", testRules);
			//从客户端获得参数
			String testRuleName = request.getParameter("testRuleName");
			//如果客户端传过来的参数不为空则执行
//			if(testRuleName != null && !testRuleName.equals(""))
//			{
				//定义TestRule对象来接受testRuleServiceImpl查询出来的对象
				TestRule testRule = testRuleServiceImpl.findByTestRuleName(testRuleName);
				if(testRule != null)
				{
					message = new Message("find_testRule","查询试卷成功");
					request.setAttribute("testRule", testRule);
				}
				
//			}
//			else 
//			{
//				message = new Message("find_testRule","查询试卷失败");
//			}
			//将message放入request并且完成页面跳转
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("find_testRule.jsp");
			rd.forward(request, response);
		}
		
		//如果操作是根据试卷Id查询
		else if(operate.equals("findById_testRule"))
		{
			//得到试卷Id参数
			String testRuleId = request.getParameter("testRuleId");
			
			//定义TestRule对象来接受testRuleServiceImpl查询出来的对象
			TestRule testRule = testRuleServiceImpl.findByTestRuleId(testRuleId);
			
			message = new Message("findById_testRule","查询试卷成功");
			
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("");
			rd.forward(request, response);
		}
		else if(operate.equals("jump_updateTestRule"))
		{
			//从数据库中取出所有的试卷名称发送到前台
			List<TestRule> testRules = testRuleServiceImpl.findTestRuleName();
			request.setAttribute("testRules", testRules);
			//从数据库中取出所有试题类型名称发送到前台
			List<TestType> testTypes = testTypeServiceImpl.findAllTestType();
			request.setAttribute("testTypes", testTypes);
			//得到从前天传过来的试卷Id
			String testRuleId = request.getParameter("testRuleId");
			//根据试卷Id得到试卷对象
			TestRule testRule = testRuleServiceImpl.findByTestRuleId(testRuleId);
			//将试卷对象发送到前台
			request.setAttribute("testRule", testRule);
			RequestDispatcher rd = request.getRequestDispatcher("update_testRule.jsp");
			rd.forward(request, response);
		}
		else if(operate.equals("findAll_testRule"))
		{
			//从前台得到当前页码
			String pageNum = request.getParameter("pageNum");
			//得到Page对象
			Page page = testRuleServiceImpl.findPageRecords(pageNum);
			//将Page对象发送到前台
			request.setAttribute("page", page);
			@SuppressWarnings("unchecked")
			List<TestRule> testRules = page.getRecords();
			for (TestRule testRule : testRules) {
				List<TestRuleItem> testRuleItems = testRule.getTestRuleItemList();
				for ( TestRuleItem testRuleItem : testRuleItems) {
					System.out.println(testRule.getTestRuleName() + testRuleItem.getTestType().getTestTypeName() + testRuleItem.getTestRuleItemNum());
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("find_testRule.jsp");
			rd.forward(request, response);
			
		}else if(operate.equals("getTestNum")){
			String testTypeName = 
			new String(request.getParameter("testTypeName").getBytes("ISO-8859-1"),"utf-8");
			System.out.println("----------+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=---------");
			System.out.println(testTypeName);
			int testNum = getTestNum(testTypeName);
			System.out.println("----------+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=---------");
			System.out.println(testNum);
			PrintWriter out = response.getWriter();
			out.print(testNum);
		}
	}
	
	public int getTestNum(String testTypeName){
		return testServiceImpl.findByTestTypeName(testTypeName);
	}
}
