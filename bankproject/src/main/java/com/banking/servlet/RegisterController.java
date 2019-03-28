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

public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");
		int money=Integer.parseInt(request.getParameter("money"));
		String password=request.getParameter("password");
		String passwordagain=request.getParameter("passwordagain");
		if(id<0) {
			String registeriderror="ENTER A POSITIVE NUMBER AS ID!";
			request.setAttribute("registeriderror", registeriderror);
			RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		}
		else if(password.equals(passwordagain) && id!=00000) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				Statement st=(Statement) con.createStatement();
				String sql="INSERT INTO customers "+"(id,first_name,last_name,money)"+" VALUES ("+id+",'"+first_name+"','"+last_name+"',"+money+")";
				st.executeUpdate(sql);
				Statement st2=(Statement) con.createStatement();
				String sql2="INSERT INTO infos "+"(id,password)"+" VALUES ("+id+",'"+password+"')";
				st2.executeUpdate(sql2);
				HttpSession session=request.getSession();
				session.setAttribute("logid", id);
				CustomerDao dao=new CustomerDao();
				CustomerReturn a1=dao.getCustomer(id);
				request.setAttribute("customer", a1);
				RequestDispatcher rd=request.getRequestDispatcher("SuccessfullRegister.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				String sameiderror="THIS ID IS ALREADY IN USE!";
				request.setAttribute("sameiderror", sameiderror);
				RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(id==00000) {
			String registeriderror="ENTER A POSITIVE NUMBER AS ID!";
			request.setAttribute("registeriderror", registeriderror);
			RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		}
		else {
			String passworderror="PASSWORDS DO NOT MATCH!";
			request.setAttribute("passworderror", passworderror);
			RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		}
		
	}
}
