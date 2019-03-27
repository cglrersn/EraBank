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

import com.banking.servlet.CustomerReturn;

public class DepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerReturn a=new CustomerReturn();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deposit=Integer.parseInt(request.getParameter("deposit"));
		HttpSession session = request.getSession(false);
		int id = (Integer)session.getAttribute("logid"); 
		if(deposit>0) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				Statement st=(Statement) con.createStatement();
				String sql="SELECT money FROM customers WHERE id="+id;
				ResultSet rs=st.executeQuery(sql);
				if(rs.next()) {
					int money =  ((Number) rs.getObject(1)).intValue();
					int newbalance=money+deposit;
					Statement st2=(Statement) con.createStatement();
					String sql2="UPDATE customers SET money="+newbalance+" WHERE id="+id;
					st2.executeUpdate(sql2);
					request.setAttribute("newbalance", newbalance);
					RequestDispatcher rd=request.getRequestDispatcher("ShowDeposit.jsp");
					rd.forward(request, response);
					}
				}
			 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			String depositerror="ENTER AN POSITIVE AMOUNT OF MONEY!";
			request.setAttribute("depositerror", depositerror);
			RequestDispatcher rd=request.getRequestDispatcher("Deposit.jsp");
			rd.forward(request, response);
		}
			
		
			
		}

}
