package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import com.socialnet.bean.AdminBean;
import com.socialnet.conections.AbstractDataAccessObject;
import com.socialnet.dao.*;
import com.socialnet.exception.DataAcessException;

public class LoginAction extends HttpServlet {
	public LoginAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		boolean flag = false;
		HttpSession session = request.getSession();
		String uid = request.getParameter("id");
		String upwd = request.getParameter("pwd");
		String utype = "";
		utype = new MasterDao().isAuthenticated(uid, upwd);
		System.out.println(utype);
		if (uid.equals("") || upwd.equals("")) {
			response.sendRedirect("loginform.jsp?alert=field values required");
		} else if (utype.equals("admin")) {
			System.out.println(uid);
			session.setAttribute("uname", uid);
			request.setAttribute("auser", uid);
			session.setAttribute("auser", uid);
			session.setAttribute("upass", upwd);
			session.setAttribute("ownuser", uid);
			session.setAttribute("utype", utype);
			response.sendRedirect("AdminHome.jsp");
		} else if (utype.equals("user")) {
			System.out.println(uid);
			session.setAttribute("upass", upwd);
			session.setAttribute("ownuser", uid);
			request.setAttribute("auser", uid);
			session.setAttribute("uname", uid);
			session.setAttribute("auser", uid);
			session.setAttribute("utype", utype);
			response.sendRedirect("UserHome.jsp?auser=" + uid);
		} else if (utype.equals("Govtbody")) {
			session.setAttribute("upass", upwd);
			session.setAttribute("uname", uid);
			session.setAttribute("ownuser", uid);
			request.setAttribute("auser", uid);
			session.setAttribute("utype", utype);
			response.sendRedirect("UserHome.jsp?auser=" + uid);
		} else if (utype.equalsIgnoreCase("locked")) {

			response.sendRedirect("loginform.jsp?alert=Sorry Your Account was Locked");
		} else 
		{
			response.sendRedirect("loginform.jsp?alert=Loginfailed");
		}

	}

	

}
