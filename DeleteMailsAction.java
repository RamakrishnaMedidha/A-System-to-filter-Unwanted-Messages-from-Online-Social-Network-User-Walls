package com.socialnet.actions;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.bean.MailDTO;
import com.socialnet.deleget.MailDelegate;
import com.socialnet.util.UtilConstants;

/**
 * The DeleteMailsAction servlet class implements for to to delete your account
 * mails details.
 */
public class DeleteMailsAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean flag = false;
		String path = "";
		String mailbox = request.getParameter("mailbox");
		String ch[] = request.getParameterValues("ch");
		try {
			for (int i = 0; i < ch.length; i++) {
				System.out.println(ch[i]);
				flag = new MailDelegate().deleteMails(Integer.parseInt(ch[i]),
						mailbox);
			}
			if (flag) {
				request.setAttribute("status",
						UtilConstants._MAIL_DELETE_SUCCESS);
				path = UtilConstants._STATUS;
			} else {
				request
						.setAttribute("status",
								UtilConstants._MAILS_DELETE_FAIL);
				path = UtilConstants._STATUS;
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			path = UtilConstants._STATUS;
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
