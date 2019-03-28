package com.banking.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(session.getAttribute("logid")!=null) {
			session.removeAttribute("logid");
			session.invalidate();
			response.sendRedirect("Login.jsp");
		}
		else if(session.getAttribute("adminlogid")!=null) {
			session.removeAttribute("adminlogid");
			session.invalidate();
			response.sendRedirect("Login.jsp");
		}
	}

}
