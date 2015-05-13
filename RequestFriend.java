package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.dao.UserDAO;

public class RequestFriend extends HttpServlet {
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
		String userid = request.getParameter("userid");
		System.out.println(userid);
		String requser = request.getParameter("requser");
		System.out.println(requser);
		String msg = request.getParameter("msg");
		boolean flag = new UserDAO().requestFriend(userid, requser, msg);
		if (flag) {
			response
					.sendRedirect("RUserHome.jsp?status=your request passed have patience untill he/she accept&auser="
							+ userid);

		} else
			response
					.sendRedirect("RUserHome.jsp?status=your are already requested , have patience untill he/she accept&auser="
							+ userid);
	}

}
