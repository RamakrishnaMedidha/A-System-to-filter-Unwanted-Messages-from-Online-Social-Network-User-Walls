package com.socialnet.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.bean.MailDTO;
import com.socialnet.daoimpl.MailDaoImpl;
import com.socialnet.deleget.MailDelegate;
import com.socialnet.util.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * The OutboxAction servlet class implements for to view out box message
 * details.
 * 
 */
public class FilterMailAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		HttpSession session = request.getSession();
		
		Vector<MailDTO> fmail = null;
		MailDTO mail = new MailDTO();
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(mail, map);
			fmail = new MailDaoImpl().viewFilterMail();
			if (!fmail.isEmpty()) {
				request.setAttribute("mails", fmail);
				request.setAttribute("status", UtilConstants._MAILS);
				path = UtilConstants._FILTER_MAIL_BOX;
			} else {
				request.setAttribute("status", UtilConstants._MAIL_BOX_FAIL);
				path = UtilConstants._FILTER_MAIL_BOX;
			}
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("status", e.getMessage());
			path = UtilConstants._FILTER_MAIL_BOX;
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}