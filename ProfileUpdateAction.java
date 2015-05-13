package com.socialnet.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.bean.AdminBean;
import com.socialnet.dao.MasterDao;

public class ProfileUpdateAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AdminBean abean = null;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		boolean flag = false;
		abean = new AdminBean();
		abean.setAdminid((String) session.getAttribute("uname"));
		abean.setAdmintype((String) session.getAttribute("utype"));
		abean.setFirstname(request.getParameter("firstname"));
		abean.setMidlename(request.getParameter("midlename"));
		abean.setLastname(request.getParameter("lastname"));
		abean.setDob(request.getParameter("dob"));
		abean.setQualification(request.getParameter("qualification"));
		abean.setUniversity(request.getParameter("university"));
		System.out
				.println("adminuividfd:" + request.getParameter("university"));
		abean.setMobile(request.getParameter("mobile"));
		abean.setVillage(request.getParameter("village"));
		abean.setCity(request.getParameter("city"));
		abean.setState(request.getParameter("state"));
		abean.setCountry(request.getParameter("country"));
		abean.setPin(request.getParameter("pin"));
		flag = new MasterDao().updateAdminDetails(abean);
		if (flag) {
			response.sendRedirect("AdminProfile.jsp?status=Profile Updated Successfully");
		} else {
			response.sendRedirect("AdminProfile.jsp?status=Updation Failed");
		}

	}

	/**
	 * The doPut method of the servlet. <br>
	 * 
	 * This method is called when a HTTP put request is received.
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
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
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
