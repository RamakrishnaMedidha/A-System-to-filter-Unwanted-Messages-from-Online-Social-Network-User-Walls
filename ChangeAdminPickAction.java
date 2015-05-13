package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.dao.UserDAO;

public class ChangeAdminPickAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doPost(request, response);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path = request.getParameter("photo");
		String user = request.getParameter("auser");
		System.out.println("User Name:-->" + user);
		boolean flag = new UserDAO().insertPhoto(path, user);
		if (flag) {
			response.sendRedirect("ChangeAdminPoto.jsp?auser=" + user);
		} else
			response.sendRedirect("ChangeAdminPoto.jsp?status=sorry try again");
		out.flush();
		out.close();
	}

}
