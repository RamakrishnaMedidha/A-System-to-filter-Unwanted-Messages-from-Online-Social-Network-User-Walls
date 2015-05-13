package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.socialnet.dao.UserDAO;
import com.socialnet.util.DateWrapper;
public class PostNewSawal extends HttpServlet {

	
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
	     
		HttpSession session=request.getSession();
		String user=request.getParameter("auser");
		 
		String sawal=request.getParameter("sawal");
		String doexp=DateWrapper.parseDate(request.getParameter("doexp"));
		String head=request.getParameter("shead");
		boolean flag=new UserDAO().postNewSawal(user,sawal,doexp,head);
		if(flag)
		{
			response.sendRedirect("NewSawal.jsp?status=success&auser="+user);
		}else
		{
			response.sendRedirect("NewSawal.jsp?status=failedauser="+user);
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
