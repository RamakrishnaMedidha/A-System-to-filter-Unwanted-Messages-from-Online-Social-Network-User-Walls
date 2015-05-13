package com.socialnet.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.conections.AbstractDataAccessObject;
import com.socialnet.bean.MailDTO;
import com.socialnet.deleget.MailDelegate;
import com.socialnet.util.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import com.sun.swing.internal.plaf.basic.resources.basic;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.socialnet.daoimpl.MailDaoImpl;
import com.socialnet.exception.ConnectionException;

public class FilterSendMailAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    RequestDispatcher rd = null;
    HttpSession session=request.getSession();
boolean flag = false;
boolean flag1= false;
String path = "";
MailDTO mail = new MailDTO();
String to=null;
String sub=null;
String msg=null;
String from=null;


int userid=Integer.parseInt(request.getParameter("filtermailid"));
System.out.println("=======================userid===="+userid);


from=request.getParameter("senderid");
mail.setSenderid(from);
System.out.println("=======================senderid===="+from);


to = request.getParameter("reciverid");
mail.setReceiver(to);
System.out.println("=======================reciverid===="+to);



sub = request.getParameter("subject");
mail.setSubject(sub);
System.out.println("======================sub====="+sub);

msg = request.getParameter("message");
mail.setMessage(msg);
System.out.println("==========================msg======"+msg);



  
   try {
	
	if ((request.getParameter("senderid")) != null) 
	{
		
			
			flag = new MailDaoImpl().sendFilterMail(mail);
			if(flag)
			{
				flag= new MailDaoImpl().deletefilterMails(userid);
			}
			
	}
	if (flag) 
	{
		
		request.setAttribute("status", "Mail Sended Successfully...");
		path = UtilConstants._SEND_MAILS1;
	}
	

 else{
		request.setAttribute("status", UtilConstants._SEND_MAIL_FAIL);
		path = UtilConstants._SEND_MAILS1;
	}
} catch (Exception e) {
	request.setAttribute("status", e.getMessage());
	path = UtilConstants._SEND_MAILS;
}

rd = request.getRequestDispatcher(path);
rd.forward(request, response);
}
}

