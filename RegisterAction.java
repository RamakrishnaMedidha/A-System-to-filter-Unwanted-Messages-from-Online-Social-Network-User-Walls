package com.socialnet.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialnet.bean.UserBean;
import com.socialnet.dao.MasterDao;

public class RegisterAction extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doPost(request, response);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		UserBean ubean = new UserBean();
		ubean.setUserid(request.getParameter("userid")+"@social.com");
		ubean.setUserpwd(request.getParameter("userpwd"));
		ubean.setFirstname(request.getParameter("firstName"));
		ubean.setMidlename(request.getParameter("midlename"));
		ubean.setLastname(request.getParameter("lastName"));
		ubean.setDob(request.getParameter("dob"));
		ubean.setCity(request.getParameter("city"));
		ubean.setCountry(request.getParameter("country"));
		ubean.setMobile(request.getParameter("mobile"));
		ubean.setPin(request.getParameter("pin"));
		ubean.setSex(request.getParameter("gender"));
		ubean.setState(request.getParameter("state"));
		ubean.setVillage(request.getParameter("village"));
		ubean.setSecans(request.getParameter("secans"));
		ubean.setQuestn(request.getParameter("squest"));
		ubean.setAccmode(request.getParameter("accmode"));
		ubean.setMail(request.getParameter("email"));
		System.out.println("photo path :" + request.getParameter("photo"));
		ubean.setPhoto(request.getParameter("photo"));
		boolean flag = new MasterDao().registerUser(ubean);
		if (flag) {
			response.sendRedirect("loginform.jsp?alert=Registered successfully");
		} else {
			response
					.sendRedirect("Registration.jsp?msg=Sorry Registration Failed");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
