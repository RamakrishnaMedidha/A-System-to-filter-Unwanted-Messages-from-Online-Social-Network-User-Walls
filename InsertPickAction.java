package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.dao.UserDAO;

public class InsertPickAction extends HttpServlet {
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
		String path = request.getParameter("upoto");
		String user = request.getParameter("auser");
		boolean flag=false;
		try {
			flag = new UserDAO().insertPicture(path, user);
		} catch (SQLException e) {
System.out.println(e);
			e.printStackTrace();
		}
		if (flag) {
			response
					.sendRedirect("UserPotos.jsp?status=your photo added&auser="
							+ user);
		} else
			response.sendRedirect("UserPotos.jsp?status=sorry try again&auser="
					+ user);
		out.flush();
		out.close();
	}

}
