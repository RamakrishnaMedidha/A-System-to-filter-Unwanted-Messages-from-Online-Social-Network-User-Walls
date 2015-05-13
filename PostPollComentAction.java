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

public class PostPollComentAction extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();				   
	    int pid=Integer.parseInt(request.getParameter("pid"));
	    String sender=request.getParameter("sender");
	    
	    String pcoment=request.getParameter("pollcoment");
	    String poptn=request.getParameter("optn");
	    boolean flag=new UserDAO().postPollComent(pid,sender,pcoment,poptn);
	    if(flag)
	    {
	    	response.sendRedirect("PollComents.jsp?pstatus=Posted Successfully&auser="+sender+"&pollid="+pid);
	    }
	    else
	    	response.sendRedirect("PollComents.jsp?pstatus=Posting Failed&auser="+sender+"&pollid="+pid);
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    doPost(request,response);
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
