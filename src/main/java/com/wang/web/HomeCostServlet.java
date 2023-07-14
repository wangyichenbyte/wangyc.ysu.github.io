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
 * ���ʵ�ַurl:localhost:8080/homeCost/manager/homeCostServlet
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
	
	//������Ѽ�¼
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ҳ�ύ�Ĳ���
		String name = request.getParameter("name");
		BigDecimal money = WebUtils.bigdecimal(request.getParameter("money"), new BigDecimal(0.00));
		String mess = request.getParameter("mess");
		//��װ�������
		HomeCost homeCost = new HomeCost(name,money,mess);
		//ִ����Ӳ���������1,��ӳɹ�����֮ʧ��
		if(homeCostService.add(homeCost) == 1) {
			//ҳ���ض���
			response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list1");
		}else {
			//����ת��
			request.setAttribute("msg", "���ʧ�ܣ���ϵ����Ա");
			request.setAttribute("homeCost", homeCost);
			request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
		}
	}
	//ɾ�����Ѽ�¼
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡid
		int id  = WebUtils.parseInt(request.getParameter("id"), 0);
		//ִ��ɾ������������1,ɾ���ɹ�����֮ʧ��
		if (homeCostService.delete(id) == 1) {
			//ҳ���ض���
			response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list1");
		}else {
			//����ת��
			request.setAttribute("msg", "ɾ��ʧ�ܣ���ϵ����Ա");
			request.getRequestDispatcher("/manager/homeCostServlet?action=list1").forward(request, response);
		}
		
	}
	//�޸����Ѽ�¼
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����
		int id  = WebUtils.parseInt(request.getParameter("id"), 0);
		String name = request.getParameter("name");
		BigDecimal money = WebUtils.bigdecimal(request.getParameter("money"), new BigDecimal(0.00));
		String mess = request.getParameter("mess");
		String date = request.getParameter("date");
		//��װ
		HomeCost homeCost = new HomeCost(id,name,money,mess,date);
		//ִ��ɾ������������1,�޸ĳɹ�����֮ʧ��
		if(homeCostService.update(homeCost) == 1) {
			response.sendRedirect(request.getContextPath()+"/manager/homeCostServlet?action=list1");
		}else {
			request.setAttribute("msg", "�޸�ʧ�ܣ���ϵ����Ա");
			request.setAttribute("homeCost", homeCost);
			request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
		}
	}
	//��ѯȫ�����Ѽ�¼
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<HomeCost> homeCost = homeCostService.list();
		request.setAttribute("homeCost", homeCost);
		request.getRequestDispatcher("/manager.jsp").forward(request, response);
		
	}

	//��ҳ��ѯȫ�������Ѽ�¼
	protected void list1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		// ��ȡ HttpSession �������ڹ����û��Ự��Ϣ

		String sessionUserEmail = (String) session.getAttribute("SessionUserEmail");
		// �ӻỰ�л�ȡ��Ϊ "SessionUserEmail" ������ֵ������ת��Ϊ String ����

		String pageNumStr = request.getParameter("pageNum");
		// ��ȡ���������Ϊ "pageNum" ��ֵ�������䱣�浽 pageNumStr ������

		int pageNum = pageNumStr != null && !pageNumStr.trim().isEmpty() ? Integer.parseInt(pageNumStr) : 1;
		// �ж� pageNumStr �Ƿ�Ϊ�ջ�ֻ�����ո������Ϊ�գ�����ת��Ϊ int ���ͣ���������Ĭ��ֵΪ 1

		String pageSizeStr = request.getParameter("pageSize");
		// ��ȡ���������Ϊ "pageSize" ��ֵ�������䱣�浽 pageSizeStr ������

		int pageSize = pageSizeStr != null && !pageSizeStr.trim().isEmpty() ? Integer.parseInt(pageSizeStr) : 5;
		// �ж� pageSizeStr �Ƿ�Ϊ�ջ�ֻ�����ո������Ϊ�գ�����ת��Ϊ int ���ͣ���������Ĭ��ֵΪ 5

		Page<HomeCost> result = homeCostService.findHomeCost(pageNum, pageSize, sessionUserEmail);
		// ���� homeCostService �� findHomeCost ���������� pageNum��pageSize �� sessionUserEmail ��������ȡ��ѯ���

		request.setAttribute("result", result);
		// ����ѯ�������Ϊ�������ԣ��� JSP ҳ��ʹ��

		request.getRequestDispatcher("/manager.jsp").forward(request, response);
		// ������ת���� "/manager.jsp" ҳ�������ʾ
	}

	//ͨ��id��ѯ�������Ѽ�¼
	private void getHomeCostById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id  = WebUtils.parseInt(request.getParameter("id"), 0);
		HomeCost homeCost = homeCostService.getHomeCostById(id);
		request.setAttribute("homeCost", homeCost);
		request.getRequestDispatcher("/cost_edit.jsp").forward(request, response);
	}
	//ͨ���ؼ��ʲ�ѯ
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		List<HomeCost> homeCost = homeCostService.query(keyword);
		request.setAttribute("homeCost", homeCost);
		request.getRequestDispatcher("/query.jsp").forward(request, response);
	}
}
