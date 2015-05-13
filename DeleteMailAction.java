package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.dao.UserDAO;
import com.socialnet.daoimpl.MailDaoImpl;
import com.socialnet.deleget.MailDelegate;
import com.socialnet.exception.ConnectionException;
import com.socialnet.util.UtilConstants;
public class DeleteMailAction extends HttpServlet {

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
	int mailid=Integer.parseInt(request.getParameter("mailid"));
	System.out.println("mailllllllllll=="+mailid);
	try {
		
			
			flag = new MailDaoImpl().deleteMail(mailid);
				System.out.println(flag);
		
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



