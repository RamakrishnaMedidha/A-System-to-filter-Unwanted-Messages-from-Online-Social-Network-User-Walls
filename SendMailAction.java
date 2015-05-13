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

public class SendMailAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	static ArrayList<String> database = null;
	static ArrayList<String> friend = null;
	static ArrayList<String> matchedword = null;
	static ArrayList<String> cr = null;
	static ArrayList<String> ncr = null;
	static ArrayList<String> cp = null;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    RequestDispatcher rd = null;
    HttpSession session=request.getSession();
boolean flag = false, flag1 = false;
String path = "";
MailDTO mail = new MailDTO();
String to=null;
String sub="";
String msg;
String from;
String user = (String) session.getAttribute("uname");
mail.setSenderid(user);
System.out.println("=======================senderidid===="+user);
to = request.getParameter("empid");
mail.setReceiver(to);
System.out.println("=======================reciverid===="+to);
sub = request.getParameter("mailsub");
mail.setSubject(sub);
System.out.println("======================sub====="+sub);
msg = request.getParameter("mailbody");
System.out.println("==========================msg======"+msg);
mail.setMessage(msg);
database=new ArrayList <String>();
friend=new ArrayList <String>();
try 
{
Connection conn=AbstractDataAccessObject.getConnection();
Statement st=conn.createStatement();
Statement st1=conn.createStatement();
ResultSet rs=st.executeQuery("select word from words");
ResultSet rs1=st1.executeQuery("select friend_id from user_friends where user_id="+ "'" + user + "'");
while(rs.next())
{
	database.add(rs.getString("word"));
}
while(rs1.next())
{
	friend.add(rs1.getString("friend_id"));
}
}
catch (SQLException se) {
	se.printStackTrace();
	System.out.println(se);
}

int friendcount=0;
for(int k = 0; k < friend.size(); k++) 
{
     String dd=(String)friend.get(k);
	 if(to.equalsIgnoreCase(dd)) 
     {	
	    System.out.println("this uses is Present is your Friend List");
	    friendcount=1;
	 }
}  
if(friendcount==0)
{
try 
{
	boolean flag111 = new MailDaoImpl(). blacklistMail(mail);
	 System.out.println("Iflag11flag11flag11111--------" + flag111);
 } catch (ConnectionException e1) {
	e1.printStackTrace();
  } catch (SQLException e1) {
	e1.printStackTrace();
  }
}
   matchedword=new ArrayList <String>();
    cr=new ArrayList <String>();
	ncr=new ArrayList <String>();
       cp=new ArrayList <String>();
	
	
	//////////////////////////////////////////////////////////////////////////
	String input = msg;
    String[] words = input.split(" ");

    for(String word : words)
    {
        if(word.length() > 0 && Character.isUpperCase(word.charAt(0)))
        {
            System.out.println("Upper case: " + word);
            cp.add(word);
        }
        
    }
    System.out.println("Upper word " + cp);
    ///////////////////////////////////////////////////////////////////////////
    
	String data = msg;

	int totalCharacters=0;
	
	totalCharacters = data.length();
	System.out.println("Number of characters = " + totalCharacters + "\n");
	
	///////////////////////////////////////////////////////////////////////////
	   String s = msg;
    int counter = 0;
    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;
    int counter4 = 0;
    int counter5 = 0;
    int counter6 = 0;
    int counter7 = 0;
    int counter8 = 0;
   
    for( int i=0; i<s.length(); i++ ) {
        if( s.charAt(i) == '!' ) {
            counter++;
        } 
        if( s.charAt(i) == '?' ) {
            counter1++;
        } 
        if( s.charAt(i) == ':' ) {
            counter2++;
        } 
        if( s.charAt(i) == ';' ) {
            counter3++;
        } 
        if( s.charAt(i) == '.' ) {
            counter4++;
        } 
        if( s.charAt(i) == ',' ) {
            counter5++;
        } 
        if( s.charAt(i) == '[' ) {
            counter6++;
        } 
        if( s.charAt(i) == ']' ) {
            counter7++;
        } 
        if( s.charAt(i) == '.' ) {
            counter8++;
        } 
        }
    
    int pcn= counter1+counter2+counter3+counter4+counter5+counter6+counter7+counter8+counter;
    System.out.println("PCN====>"+pcn);
    System.out.println("!!!!!!!!!!!!!!!EEEMMM====>"+counter);
    System.out.println("?????????QMMMMM====>"+counter1);
    
    
    mail.setTc(totalCharacters);
    mail.setPc(pcn);
    mail.setEm(counter);
    mail.setQm(counter1);
   
    ////////////////////////////////////////////////////////////////////////
    
    StringTokenizer st = new StringTokenizer(msg);

     while (st.hasMoreElements()) 
     {
	      String str=(String)st.nextElement();
	    matchedword.add(str);
      }
     
    
     for(int i = 0; i < matchedword.size(); i++) {
	 
    	  String mm=(String)matchedword.get(i);
	     for(int j = 0; j < database.size(); j++) 
	       {
		  String dd=(String)database.get(j);
			 if(mm.equalsIgnoreCase(dd)) 
		     {	
			 cr.add(mm);
			 }
	    }  
	 
	}
 
     ncr = matchedword;
      for (int i = 0; i < cr.size(); i++) {
        System.out.println("str1----" + cr.get(i));
        for (int j = 0; j < ncr.size(); j++) {
        System.out.println("str2----" +ncr.get(j));
        if ((cr.get(i)).equals(ncr.get(j))) {
	    ncr.remove(ncr.get(j));
       
       }
       }
       }
    System.out.println("match word---------" + cr);
    System.out.println("not match word---------" + ncr);
   String  st1=null;
   String  st11=null;
   if(cr!=null)
   {
   for(int x=0;x<cr.size();x++)
   {
	   st1=(String)cr.get(x);
	   st11=st11+" "+st1;
   }
   st11=st11.substring(5);
   System.out.println("correct Message---------" + st11);
   mail.setCword(st11);
   }
   String  st2=null;
   String  st22=null;
   if(ncr!=null)
   {
   for(int y=0;y<ncr.size();y++)
   {
	   st2=(String)ncr.get(y);
	   st22=st22+" "+st2;
   }
   st22=st22.substring(5);
   System.out.println("Incorrect word --------" + st22);
   mail.setBword(st22);
   }
   String  st3=null;
   String  st33=null;
   if(cp!=null)
   {
   for(int z=0;z<cp.size();z++)
   {
	   st3=(String)cp.get(z);
	   st33=st33+" "+st3;
   }
   st33=st33.substring(5);
   System.out.println("Incorrect word --------" + st33);
   mail.setCpword(st33);
   }
  
   
  
   try 
   {
	boolean flag11 = new MailDaoImpl().filterMail(mail);
	 System.out.println("Iflag11flag11flag11--------" + flag11);
    } catch (ConnectionException e1) {
	e1.printStackTrace();
     } catch (SQLException e1) {
	e1.printStackTrace();
     }
   Map map = request.getParameterMap();
   try {
	BeanUtils.populate(mail, map);
	if ((request.getParameter("image[image_1]")) != null) 
	{
		if (!request.getParameter("image[image_1]").equals("")) 
		{
			String ch[] = request.getParameterValues("ch");
			flag = new MailDelegate().sendMail(mail);
			for (int j = 1; j <= ch.length; j++) 
			{
				String filaname = request.getParameter("image[image_"
						+ j + "]");

				mail.setFilepath(filaname);
				try 
				{
					flag1 = new MailDelegate().insestAttachment(mail);
				} catch (Exception e) 
				{
					System.out.println(e);
				}
			}
		}
	}
	if (flag) 
	{
		matchedword=null;
		database=null;
		request.setAttribute("status", "Mail Sended Successfully...");
		path = UtilConstants._SEND_MAILS;
	}
	

 else{
	   request.setAttribute("status", "Mail Sended Successfully...");
		path = UtilConstants._SEND_MAILS;
	}
} catch (Exception e) {
	request.setAttribute("status", e.getMessage());
	path = UtilConstants._SEND_MAILS;
}

rd = request.getRequestDispatcher(path);
rd.forward(request, response);
}
}

