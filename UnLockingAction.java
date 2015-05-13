package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import com.socialnet.bean.*;
import com.socialnet.db.DBFactory;

public class UnLockingAction extends HttpServlet {

	Connection con=null;
	PreparedStatement pstmt=null;
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
		
	    try{
	    
	    	pstmt=con.prepareStatement("update login_details set lock_info=? where login_id=?");
	    	pstmt.setString(1,"unlock");
	    	pstmt.setString(2, request.getParameter("uid"));
	    	int count=pstmt.executeUpdate();
	    	if(count>0)
	    	{
	    		
	    		response.sendRedirect("ViewAllAccounts.jsp?lock=lock");
	    	}
	    	else{
	    		
	    		response.sendRedirect("ViewAllAccounts.jsp?lock=unlock");
	    	}
	    }catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException 
	{
		try{
		    con=DBFactory.getConnection();
		// Put your code here
	     	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
