package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.bean.AdminBean;
import com.socialnet.dao.MasterDao;
public class ChangePassword extends HttpServlet {

	MasterDao md=new MasterDao();


	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
        HttpSession session=request.getSession();
        
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean flag=false;
		
		AdminBean abean=new AdminBean();
		abean.setNewadminpwd(request.getParameter("newpwd"));
		abean.setNewadminid(request.getParameter("oldid"));
		abean.setAdminid((String)session.getAttribute("uname"));
		
		flag=md.changePassword(abean);
		if(flag)
		{ 
			
			response.sendRedirect("ChangePassword.jsp?status=Password Changed Successfully");
		}
		else
		{
			response.sendRedirect("ChangePassword.jsp?status=Operation Failed");
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPut method of the servlet. <br>
	 *
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
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
