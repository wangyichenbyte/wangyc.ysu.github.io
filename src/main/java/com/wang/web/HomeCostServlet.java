package com.wang.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaBean.Page;
import com.wang.pojo.HomeCost;
import com.wang.service.HomeCostService;
import com.wang.utils.WebUtils;

/**
 * 访问地址url:localhost:8080/homeCost/manager/homeCostServlet
 * Servlet implementation class HomeCostServlet
 */
@WebServlet("/manager/homeCostServlet")
public class HomeCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeCostService homeCostService = new HomeCostService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("add".equals(action)) {
			add(request, response);
		}else if("delete".equals(action)) {
			delete(request, response);
		}else if("update".equals(action)) {
			update(request, response);
		}else if("list1".equals(action)) {
			list1(request, response);
		}else if("getHomeCostById".equals(action)) {
			getHomeCostById(request, response);
		}else if("query".equals(action)) {
			query(request, response);
		}
	}
	
	//添加消费记录
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页提交的参数
		String name = request.getParameter("name");
		BigDecimal money = WebUtils.bigdecimal(request.getParameter("money"), new BigDecimal(0.00));
		String mess = request.getParameter("mess");
		//封装成类对象
		HomeCost homeCost = new HomeCost(name,money,mess);
		//执行添加操作，返回1,添加成功，反之失败
		if(homeCostService.add(homeCost) == 1) {
			//页面重定向
			response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list1");
		}else {
			//请求转发
			request.setAttribute("msg", "添加失败，联系管理员");
			request.setAttribute("homeCost", homeCost);
			request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
		}
	}
	//删除消费记录
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取id
		int id  = WebUtils.parseInt(request.getParameter("id"), 0);
		//执行删除操作，返回1,删除成功，反之失败
		if (homeCostService.delete(id) == 1) {
			//页面重定向
			response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list1");
		}else {
			//请求转发
			request.setAttribute("msg", "删除失败，联系管理员");
			request.getRequestDispatcher("/manager/homeCostServlet?action=list1").forward(request, response);
		}
		
	}
	//修改消费记录
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		int id  = WebUtils.parseInt(request.getParameter("id"), 0);
		String name = request.getParameter("name");
		BigDecimal money = WebUtils.bigdecimal(request.getParameter("money"), new BigDecimal(0.00));
		String mess = request.getParameter("mess");
		String date = request.getParameter("date");
		//封装
		HomeCost homeCost = new HomeCost(id,name,money,mess,date);
		//执行删除操作，返回1,修改成功，反之失败
		if(homeCostService.update(homeCost) == 1) {
			response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list1");
		}else {
			request.setAttribute("msg", "修改失败，联系管理员");
			request.setAttribute("homeCost", homeCost);
			request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
		}
	}
	//查询全部消费记录
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<HomeCost> homeCost = homeCostService.list();
		request.setAttribute("homeCost", homeCost);
		request.getRequestDispatcher("/manager.jsp").forward(request, response);
		
	}

	//分页查询全部的消费记录
	protected void list1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		// 获取 HttpSession 对象，用于管理用户会话信息

		String sessionUserEmail = (String) session.getAttribute("SessionUserEmail");
		// 从会话中获取名为 "SessionUserEmail" 的属性值，将其转换为 String 类型

		String pageNumStr = request.getParameter("pageNum");
		// 获取请求参数名为 "pageNum" 的值，并将其保存到 pageNumStr 变量中

		int pageNum = pageNumStr != null && !pageNumStr.trim().isEmpty() ? Integer.parseInt(pageNumStr) : 1;
		// 判断 pageNumStr 是否为空或只包含空格，如果不为空，则将其转换为 int 类型，否则设置默认值为 1

		String pageSizeStr = request.getParameter("pageSize");
		// 获取请求参数名为 "pageSize" 的值，并将其保存到 pageSizeStr 变量中

		int pageSize = pageSizeStr != null && !pageSizeStr.trim().isEmpty() ? Integer.parseInt(pageSizeStr) : 5;
		// 判断 pageSizeStr 是否为空或只包含空格，如果不为空，则将其转换为 int 类型，否则设置默认值为 5

		Page<HomeCost> result = homeCostService.findHomeCost(pageNum, pageSize, sessionUserEmail);
		// 调用 homeCostService 的 findHomeCost 方法，传入 pageNum、pageSize 和 sessionUserEmail 参数，获取查询结果

		request.setAttribute("result", result);
		// 将查询结果设置为请求属性，供 JSP 页面使用

		request.getRequestDispatcher("/manager.jsp").forward(request, response);
		// 将请求转发到 "/manager.jsp" 页面进行显示
	}

	//通过id查询该条消费记录
	private void getHomeCostById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id  = WebUtils.parseInt(request.getParameter("id"), 0);
		HomeCost homeCost = homeCostService.getHomeCostById(id);
		request.setAttribute("homeCost", homeCost);
		request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
	}
	//通过关键词查询
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		List<HomeCost> homeCost = homeCostService.query(keyword);
		request.setAttribute("homeCost", homeCost);
		request.getRequestDispatcher("/query.jsp").forward(request, response);
	}
}
