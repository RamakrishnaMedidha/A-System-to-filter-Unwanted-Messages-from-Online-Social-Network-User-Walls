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

public class UpdateSocialDetails extends HttpServlet {

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
		
		boolean flag=false;
	   
	    UserBean ubean=new UserBean();
	    UserDAO ud=new UserDAO();
	    String user=request.getParameter("auser");
	    
	    ubean.setUserid(request.getParameter("auser"));
	    ubean.setMaritual_status(request.getParameter("mstatus"));
	    ubean.setRelegion(request.getParameter("relegion"));
	    ubean.setHumour(request.getParameter("humour"));
	    ubean.setSexuality(request.getParameter("sexuality"));
	    ubean.setSmoking(request.getParameter("smoking"));
	    ubean.setDrinking(request.getParameter("drinking"));
	    ubean.setPets(request.getParameter("pets"));
	    ubean.setLiving(request.getParameter("living"));
	    ubean.setNativity(request.getParameter("native"));
	    ubean.setMovies(request.getParameter("movies"));
	    ubean.setBooks(request.getParameter("books"));
	    ubean.setWebsites(request.getParameter("websites"));
	    
	    
	    flag=ud.updateUserSocialDetails(ubean);
	    if(flag)
	    {
	    	response.sendRedirect("SocialDetails.jsp?status=Updation Successfull&auser="+user);
	    }
	    else{
	    	
	    	response.sendRedirect("SocialDetails.jsp?status=Sorry Updation Failed&auser="+user);
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
