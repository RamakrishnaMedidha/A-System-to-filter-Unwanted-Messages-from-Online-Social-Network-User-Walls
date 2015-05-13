package com.socialnet.actions;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.bean.MailDTO;
import com.socialnet.deleget.MailDelegate;
import com.socialnet.util.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class ViewMailAttachmentAction extends HttpServlet {

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
		String target = "";
		Vector<MailDTO> vmail = null;
		int messageid = Integer.parseInt(request.getParameter("messageid"));
		String mailbox = request.getParameter("mailbox");
		try {
			vmail = new MailDelegate().viewMailAttachment(messageid, mailbox,
					request.getRealPath("/images"));
			if (!vmail.isEmpty()) {
				request.setAttribute("vmail", vmail);
				target = UtilConstants._MAIL_ATTACHMENTS;
			} else {
				request.setAttribute("status",
						UtilConstants.__MAIL_MESSAGE_FAIL);
				target = UtilConstants._MAIL_ATTACHMENTS;
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			target = UtilConstants._MAIL_ATTACHMENTS;
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}
}
