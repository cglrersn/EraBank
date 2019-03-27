package com.banking.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CloseAccountController
 */
public class CloseAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int decision =Integer.parseInt(request.getParameter("yesorno"));
		HttpSession session = request.getSession(false);
		int id = (Integer)session.getAttribute("logid");
		if(decision==0) {
			String message="<h3>THANK YOU FOR STAYING WITH US!</h3>";
			request.setAttribute("message", message);
			RequestDispatcher rd=request.getRequestDispatcher("ShowDecision.jsp");
			rd.forward(request, response);
		}
		else if(decision==1) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				Statement st=(Statement) con.createStatement();
				String sql="DELETE FROM customers WHERE id="+id;
				st.executeUpdate(sql);
				Statement st1=(Statement) con.createStatement();
				String sql1="DELETE FROM infos WHERE id="+id;
				st1.executeUpdate(sql1);
				session.removeAttribute("logid");
				session.invalidate();
				String message="<h3>IT WAS PLEASURE TO HAVE YOU WITH US!</h3>";
				request.setAttribute("message", message);
				RequestDispatcher rd=request.getRequestDispatcher("ShowDecision.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
