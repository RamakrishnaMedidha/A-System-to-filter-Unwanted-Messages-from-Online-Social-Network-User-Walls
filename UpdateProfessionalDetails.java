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
public class UpdateProfessionalDetails extends HttpServlet {

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
		
		
		UserDAO ud=new UserDAO();
		UserBean ubean=new UserBean();
		String user=request.getParameter("auser");
		ubean.setUserid(request.getParameter("auser"));
		ubean.setQualification(request.getParameter("qualification"));
		ubean.setSchool(request.getParameter("school"));
		ubean.setLoc(request.getParameter("loc"));
		ubean.setUniversity(request.getParameter("university"));
		ubean.setOccupation(request.getParameter("occupation"));
		ubean.setSkills(request.getParameter("skills"));
		ubean.setInterests(request.getParameter("interests"));
		System.out.println(request.getParameter("skills"));
		boolean flag=ud.updateUserProfessional(ubean);
		
		if(flag){
			
			response.sendRedirect("Professional.jsp?status=Update SuccessFully&auser="+user);
		}
		else{
			
			response.sendRedirect("Professional.jsp?status=Updation Failed&auser="+user);
			
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
