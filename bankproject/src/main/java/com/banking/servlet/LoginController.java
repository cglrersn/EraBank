package com.banking.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String password=request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			Statement st=(Statement) con.createStatement();
			String sql="SELECT * FROM infos WHERE id="+id+" AND password='"+password+"'  ";
			ResultSet rs=st.executeQuery(sql);
			if(((ResultSet) rs).next()) {
				HttpSession session=request.getSession();
				session.setAttribute("logid", id);
				CustomerDao dao=new CustomerDao();
				CustomerReturn a1=dao.getCustomer(id);
				request.setAttribute("customer", a1);
				RequestDispatcher rd=request.getRequestDispatcher("SuccessfullLogin.jsp");
				rd.forward(request, response);
			}
			else {
				String loginerror="WRONG ID OR PASSWORD!";
				request.setAttribute("loginerror", loginerror);
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
