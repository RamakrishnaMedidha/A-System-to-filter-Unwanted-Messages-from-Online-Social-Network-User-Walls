package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.bean.UserBean;
import com.socialnet.dao.MasterDao;
import com.socialnet.dao.UserDAO;

public class AddServiceScoreAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		boolean flag = false;
		String user = request.getParameter("auser");
		String score = request.getParameter("score");
		System.out.println(user);
		System.out.println(score);
		flag = new UserDAO().addServiceScore(user, score);
		if (flag) {
			response
					.sendRedirect("SearchFriend.jsp?status=added successfully&auser="
							+ (String) session.getAttribute("userid"));
		} else {
			response.sendRedirect("SearchFriend.jsp?status=Error&auser="
					+ (String) session.getAttribute("userid"));
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
