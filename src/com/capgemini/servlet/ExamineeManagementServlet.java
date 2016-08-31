package com.capgemini.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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

import com.capgemini.dao.impl.ExamineeDaoImpl;
import com.capgemini.domain.Examinee;
import com.capgemini.domain.Pposition;
import com.capgemini.exception.ExceptionMessege;
import com.capgemini.factory.ServiceFactory;
import com.capgemini.message.Message;
import com.capgemini.service.ExamineeService;
import com.capgemini.service.PpsitionService;
import com.capgemini.util.DBUtil;
import com.capgemini.util.GetUUID;
import com.capgemini.util.Page;
import com.capgemini.util.SendMail;

/**
 * 考生管理模块的Servlet,用于试题管理模块
 * @author chao538
 * 张瑞超在2015-12-17添加新增考生时发送邮件通知的功能
 */
@SuppressWarnings("serial")
public class ExamineeManagementServlet extends HttpServlet {
	/**根据ServiceFactory得到ExamineeServiceImpl*/
	private ExamineeService serviceImpl = ServiceFactory.getInstance().getExamineeServiceImpl();

	/**根据ServiceFactory得到PpositionServiceImpl*/
	private PpsitionService ppositionServiceImpl = ServiceFactory.getInstance().getPpositionServiceImpl();

	/**定义一个Message对象*/
	Message message = null;
	/**
	 * doGet方法,调用时在内部调用doPost方法
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		//通过ServiceFactory得到examineeServiceImpl
		ExamineeService examineeServiceImpl = ServiceFactory.getInstance().getExamineeServiceImpl();
		
		//设置ContentType为"text/html"
		response.setContentType("text/html");
		
		//将数据库查出来的职位的信息放入session
		List<Pposition> ppositions = ppositionServiceImpl.findPposition();
		request.setAttribute("ppositions", ppositions);
		
		
		//得到客户端传过来的操作数的内容
		String operate = request.getParameter("operate");
		//打印操作数
		System.out.println(operate);
		//通过判断判断操作数的内容来判断去执行具体的增删该查的代码
		if (operate.equals("add_examinee")) {
			request.setAttribute("ppositions", ppositions);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("add_examinee.jsp");
			requestDispatcher.forward(request, response);
		} 	
		else if (operate.equals("add_examinee_over")){
			String examineeId = GetUUID.getUUID();
			String examineeName=new String(request.getParameter("examineeName").getBytes("ISO-8859-1"),"utf-8");
			String examineeSex=new String(request.getParameter("examineeSex").getBytes("ISO-8859-1"),"utf-8");
			String examineeSchool=new String(request.getParameter("examineeSchool").getBytes("ISO-8859-1"),"utf-8");
			Pposition pposition = new Pposition();
			System.out.println(request.getParameter("ppositionName")+"----------------------------------");
			pposition.setPpositionId(request.getParameter("ppositionName"));
            String examineeTelephone = request.getParameter("examineeTelephone");
			String examineeEmail = request.getParameter("examineeEmail");
			
			String messages = this.checkCF(examineeTelephone);
			Message messagess = new Message("add_examinee", messages);
			System.out.println("messages = " + messages);
//			System.out.println(message.toString());
			if(!"null".equals(messages) && messages != null && !"".equals(messages)){
				request.setAttribute("examineeName", examineeName);
				request.setAttribute("examineeSex", examineeSex);
				request.setAttribute("examineeSchool", examineeSchool);
			
				request.setAttribute("examineeTelephone", examineeTelephone);
				request.setAttribute("examineeEmail", examineeEmail);
				request.setAttribute("message", messagess);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("add_examinee.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
			Examinee examinee = new Examinee(examineeId, examineeName, null, examineeTelephone, null, examineeSex, examineeSchool, examineeEmail, pposition);
			//TODO
			boolean flag = examineeServiceImpl.addExaminee(examinee);
			if(flag){
				Message message = new Message("add_examinee", "新增成功!");
				try {
					SendMail.mail(examineeEmail, "考试通知", examineeName+"先生，恭喜您通过了我们凯捷公司的简历筛选，请在浏览器中输入 http://192.168.0.10:8080/ZXKS_2.0 后，登陆考试系统进行第一轮在线笔试,账号为您个人的手机号,密码为123");
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				request.setAttribute("message", message);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ExamineeManagementServlet?operate=findAll_examinee&pageNum=1");
				requestDispatcher.forward(request, response);
			}else{
				Message message = new Message("add_examinee", "新增失败!");
				request.setAttribute("message", message);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ExamineeManagementServlet?operate=findAll_examinee&pageNum=1");
				requestDispatcher.forward(request, response);	
			}
			
		}else if (operate.equals("update_examinee")){
			
			// 获取浏览器传来的表单数据
			String examineeId = request.getParameter("examineeId");

			Examinee examinee = examineeServiceImpl.findById(examineeId);
			request.setAttribute("examinee", examinee);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("update_examinee.jsp");
			requestDispatcher.forward(request, response);
		} else if (operate.equals("update_examinee_over")){
			String examineeId = request.getParameter("examineeId");
			String examineeName=new String(request.getParameter("examineeName").getBytes("ISO-8859-1"),"utf-8");
			String examineeSex=new String(request.getParameter("examineeSex").getBytes("ISO-8859-1"),"utf-8");
			String examineeSchool=new String(request.getParameter("examineeSchool").getBytes("ISO-8859-1"),"utf-8");
			
			Pposition pposition = new Pposition();
			pposition.setPpositionId(request.getParameter("ppositionName"));
            String examineeTelephone = request.getParameter("examineeTelephone");
			String examineeEmail = request.getParameter("examineeEmail");
			
			request.setAttribute("examineeSex", examineeSex);
			request.setAttribute("examineeSchool", examineeSchool);
			
			try {
				boolean flag = examineeServiceImpl.updateExaminee(examineeId, examineeName, null, examineeTelephone, null, examineeSex, examineeSchool, examineeEmail, pposition);
				if(flag){
					message = new Message("update_examinee", "修改成功! ");	
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ExamineeManagementServlet?operate=findAll_examinee&pageNum=1");
					requestDispatcher.forward(request, response);
				}else{
					message = new Message("update_examinee", "修改失败");	
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("update_examinee.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (ExceptionMessege e) {
				e.printStackTrace();
			}
		}
		else if (operate.equals("delete_examinee")){
			String examineeId = request.getParameter("examineeId");
			boolean flag = examineeServiceImpl.deleteExaminee(examineeId);
			if(flag){
				//如果删除失败，提示考生删除失败
				Message message = new Message("delete_examinee", "删除成功");
				request.setAttribute("message", message);
			}else{
				Message message = new Message("delete_examinee", "考生正在考试,不能删除考生信息");
				request.setAttribute("message", message);
			}
				
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ExamineeManagementServlet?operate=findAll_examinee&pageNum=1");
			requestDispatcher.forward(request, response);
			
		} else if (operate.equals("findAll_examinee")){
			//分页
			String pageNum = request.getParameter("pageNum");
			System.out.println(pageNum);
			Page page = serviceImpl.findPageRecords(pageNum);
			request.setAttribute("page", page);
			
			//System.out.println(page != null);
			//如果查出来的数据不为空,曾将其放入request并跳至全查页面
			if(page != null){
				
				//成功是定义message并且将其放入request
				request.setAttribute("page", page);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("find_examinee.jsp");
			dispatcher.forward(request, response);
			
		} else if (operate.equals("findByLike_examinee")){
			//分页
			String pageNum = request.getParameter("pageNum");
			String examineeName=new String(request.getParameter("examineeName").getBytes("ISO-8859-1"),"utf-8");
			String examineeSex=new String(request.getParameter("examineeSex").getBytes("ISO-8859-1"),"utf-8");
			String ppositionName=new String(request.getParameter("ppositionName").getBytes("ISO-8859-1"),"utf-8");
			
			
			
			System.out.println("Num:" + pageNum);
			System.out.println("examineeName:" + examineeName);
			System.out.println("examineeSex:" + examineeSex);
			System.out.println("ppositionName:" + ppositionName);
			
			Page page = serviceImpl.findByLike(examineeName,examineeSex,ppositionName,pageNum);
			request.setAttribute("page", page);
			
		
			//成功是定义message并且将其放入request
			request.setAttribute("examineeName", examineeName);
			request.setAttribute("examineeSex", examineeSex);
			request.setAttribute("ppositionName", ppositionName);
			request.setAttribute("page", page);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("findByLike_examinee.jsp");
			dispatcher.forward(request, response);
		}else if(operate.equals("getExamineeTelCount")){
			String examineeTelephone = request.getParameter("examineeTelephone");
			ExamineeDaoImpl daoImpl = new ExamineeDaoImpl();
			int count = (Integer)daoImpl.findByExamineeTel(examineeTelephone);
			PrintWriter out = response.getWriter();
			out.print(count);
		}
	}
	/**
	 * doPost方法
	 */
	@SuppressWarnings("unchecked")
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
		List<Examinee> examinees = new ArrayList<Examinee>();
		
		// 先拿到流
		HSSFWorkbook book = new HSSFWorkbook(in);
		
		// 通过流拿到sheet
		HSSFSheet sheet = book.getSheetAt(0);
		int i = 1;
		// 初始化行
		HSSFRow row = null;
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			if(sheet.getRow(rowNum).getCell(0) == null){
				break;
			}
			i++;
		}
		int a = 0;
		for (int j = 2; j <= i; j++) {
			row = sheet.getRow(j-1);
			Examinee examinee = new Examinee();
			Pposition pposition = new Pposition();

			a++;
			if(row.getCell(2)!= null){
			examinee.setExamineeName(row.getCell(0).toString());
			examinee.setExamineeSex(row.getCell(1).toString());
			System.out.println("====+++++======");
			System.out.println(a);
			System.out.println(row.getCell(0));
			System.out.println("-------------------------");
			examinee.setExamineeSchool(row.getCell(2).toString());
			examinee.setExamineeTelephone(row.getCell(4).toString());
			examinee.setExamineeEmail(row.getCell(5).toString());
			pposition.setPpositionName(row.getCell(3).toString());
			examinee.setPpositionId(pposition);
			examinees.add(examinee);
			}
		}
		serviceImpl.saveAllExaminee(examinees);
		String pageNum = request.getParameter("pageNum");
		String examineeName = request.getParameter("examineeName");
		String examineeSex = request.getParameter("examineeSex");
		String ppositionName = request.getParameter("ppositionName");
		System.out.println("Num:" + pageNum);
		System.out.println("examineeName:" + examineeName);
		System.out.println("examineeSex:" + examineeSex);
		System.out.println("ppositionName:" + ppositionName);
		if(pageNum==null){
			pageNum="1";
		}
		Page page = serviceImpl.findByLike(examineeName,examineeSex,ppositionName,pageNum);
		request.setAttribute("page", page);
		
		//System.out.println(page != null);
		//如果查出来的数据不为空,曾将其放入request并跳至全查页面
		if(page != null){
			
			//成功是定义message并且将其放入request
			request.setAttribute("page", page);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("find_examinee.jsp");
		dispatcher.forward(request, response);
	}
	public void searchInit(HttpServletRequest request,
			HttpServletResponse response, String content, String url)
			throws ServletException, IOException {
		message = new Message("save_all_examinee", content);
		request.setAttribute("message", message);
		Page page = serviceImpl.findByLike(null, null, null, "1");
		request.setAttribute("page", page);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	
	}
	public String checkCF(String examineeTel){
		StringBuffer buffer = new StringBuffer();
		String sqlByExamineeTel = "select * from  examinee ex where ex.examineeTelephone = '"
				+ examineeTel + "'";
		System.out.println("sql = " + sqlByExamineeTel);
		boolean rsByexamineeTel = DBUtil.exquteQuery(sqlByExamineeTel);
		
		if(rsByexamineeTel){
			buffer.append("手机号码已存在请重新输入;/n");
		}
		return String.valueOf(buffer);
	}

}
