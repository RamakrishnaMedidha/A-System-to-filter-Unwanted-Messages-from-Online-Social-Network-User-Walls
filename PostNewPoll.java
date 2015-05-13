package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.socialnet.bean.UserBean;
import com.socialnet.dao.UserDAO;
import com.socialnet.util.DateWrapper;
public class PostNewPoll extends HttpServlet {

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    doPost(request,response);
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	 
		
		UserBean ub=new UserBean();
		ub.setPoll(request.getParameter("npoll"));
		String doexp=DateWrapper.parseDate(request.getParameter("doexp"));
		ub.setDob(doexp);
		ub.setSender(request.getParameter("user"));
		String user=request.getParameter("user");
		ub.setPoptn1(request.getParameter("op1"));
		ub.setPoptn2(request.getParameter("op2"));
		
		boolean flag=new UserDAO().postNewPoll(ub);
		if(flag)
		{
			response.sendRedirect("CreateNewPoll.jsp?status=success&auser="+user);
		}else
		{
			response.sendRedirect("CreateNewPoll.jsp?status=failed&auser="+user);
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
