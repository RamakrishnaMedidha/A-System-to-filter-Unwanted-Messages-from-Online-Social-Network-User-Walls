package com.socialnet.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.socialnet.bean.NotesDTO;
import com.socialnet.bean.MailDTO;
import com.socialnet.daoimpl.MailDaoImpl;
import com.socialnet.deleget.MailDelegate;
import com.socialnet.util.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class AddWordAction extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public AddWordAction() {
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

	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean flag = false;
		String path = "";
		NotesDTO notesDTO = new NotesDTO();
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(notesDTO, map);
			flag = new MailDaoImpl().addWords(notesDTO);
			if (flag) {
				path = UtilConstants._STATUS;
				request.setAttribute(UtilConstants._STATUS_STRING,
						UtilConstants._WORD_ADDED_SUCCESS);
			} else {
				path = UtilConstants._STATUS;
				request.setAttribute(UtilConstants._STATUS_STRING,
						UtilConstants._WORD_ADD_FAIL);
			}
		} catch (IllegalAccessException illegalAccessException) {
			illegalAccessException.printStackTrace();
			request.setAttribute(UtilConstants._STATUS_STRING,
					UtilConstants._WORD_ADD_FAIL);
		} catch (InvocationTargetException invocationTargetException) {
			invocationTargetException.printStackTrace();
			request.setAttribute(UtilConstants._STATUS_STRING,
					UtilConstants._WORD_ADD_FAIL);
		} 
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
