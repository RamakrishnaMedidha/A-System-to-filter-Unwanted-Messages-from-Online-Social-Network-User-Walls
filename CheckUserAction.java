package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.dao.UserDAO;


public class CheckUserAction extends HttpServlet {

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
			throws ServletException, IOException
    {
		String username = request.getParameter("userName");
		
		
		RequestDispatcher rd=null;
		
		try
		{		 
			boolean flag = new UserDAO().checkUserId(username);

			if (flag)
			{
				rd = request.getRequestDispatcher("Registration.jsp?status=<font color=red>Not available</font>&userName="+username);
				rd.forward(request, response);	
				
			}
			
			else
			{
		        rd = request.getRequestDispatcher("Registration.jsp?status=<font color=green>Available</font>&userName="+username);
			    rd.forward(request, response);
			}
		}
		catch (Exception e)
		{
			 rd = request.getRequestDispatcher("Registration.jsp?status=<font color=red>Not Available</font>&userName="+username);
			    rd.forward(request, response);
		}
	
    }

}
