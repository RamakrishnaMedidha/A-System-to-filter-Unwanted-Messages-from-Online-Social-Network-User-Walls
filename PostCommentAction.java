package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.bean.UserBean;
import com.socialnet.dao.UserDAO;

public class PostCommentAction extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
	    UserBean sbean=new UserBean();
	    String user=request.getParameter("user");
	    sbean.setSender(request.getParameter("user"));
	    int sid=Integer.parseInt(request.getParameter("sawalid").trim());
	    sbean.setSid(sid);
	    sbean.setScmnt(request.getParameter("scoment"));
	   
	    
	    boolean flag=new UserDAO().postSawalComment(sbean);
	    if(flag)
	    {
	    	response.sendRedirect("UserHome.jsp?<font color=yellow>sstatus=Posted Successfully</font>&auser="+user);
	    }
	    else
	    	response.sendRedirect("UserHome.jsp?<font color=red>sstatus=Posting Failed</font>&auser="+user);
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
