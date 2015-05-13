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

public class UpdatePersonalDetailsAction extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
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
	    
		boolean flag=false;
	    
	    UserBean ubean=new UserBean();
	    UserDAO ud=new UserDAO();
	     
	    String user=request.getParameter("auser");
	    ubean.setUserid(request.getParameter("auser"));
	    
	    ubean.setFirstname(request.getParameter("firstname"));
	    int ht=Integer.parseInt(request.getParameter("height"));
	    ubean.setHight(ht);
	    ubean.setColor(request.getParameter("color"));
	    ubean.setBuild(request.getParameter("build"));
	    ubean.setLooks(request.getParameter("looks"));
	    ubean.setBestlook(request.getParameter("bestlook"));
	    ubean.setAboutme(request.getParameter("aboutme"));
	    ubean.setHobbies(request.getParameter("hobbies"));
	    ubean.setHabbits(request.getParameter("habbits"));
	    ubean.setIhate(request.getParameter("ihate"));
	    ubean.setIlike(request.getParameter("ilike"));
	    ubean.setPurpose(request.getParameter("purpose"));
	    
	    
	    flag=ud.updateUserPersonal(ubean);
	    if(flag)
	    {
	    	response.sendRedirect("PersonalDetails.jsp?status=Updation Successfull&auser="+user);
	    }
	    else{
	    	
	    	response.sendRedirect("PersonalDetails.jsp?status=Sorry Updation Failed&auser="+user);
	    }
		
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
