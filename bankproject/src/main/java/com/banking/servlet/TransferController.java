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

public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerReturn a=new CustomerReturn();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int transferid=Integer.parseInt(request.getParameter("transferid"));
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		int transfer=Integer.parseInt(request.getParameter("transfer"));
		HttpSession session = request.getSession(false);
		int id = (Integer)session.getAttribute("logid");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			Statement st=(Statement) con.createStatement();
			String sql="SELECT money FROM customers WHERE id="+id;
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				int money =  ((Number) rs.getObject(1)).intValue();
				if(transfer>0 && money>=transfer) {
					int newbalance=money-transfer;
					String sql3="SELECT money FROM customers WHERE id="+transferid+" AND first_name='"+firstname+"' AND last_name='"+lastname+"'";
					ResultSet rs1=st.executeQuery(sql3);
					if(rs1.next()) {
						Statement st2=(Statement) con.createStatement();
						String sql2="UPDATE customers SET money="+newbalance+" WHERE id="+id;
						st2.executeUpdate(sql2);
						int transfermoney =  ((Number) rs1.getObject(1)).intValue();
						int newtransfermoney=transfermoney+transfer;
						Statement st3=(Statement) con.createStatement();
						String sql4="UPDATE customers SET money="+newtransfermoney+" WHERE id="+transferid;
						st3.executeUpdate(sql4);
						request.setAttribute("newbalance", newbalance);
						RequestDispatcher rd=request.getRequestDispatcher("ShowTransfer.jsp");
						rd.forward(request, response);
					}
					else {
						String customererror="MAKE SURE THE RECEIVER ACCOUNT INFORMATIONS ARE TRUE!";
						request.setAttribute("customererror", customererror);
						RequestDispatcher rd=request.getRequestDispatcher("Transfer.jsp");
						rd.forward(request, response);
					}
				}
				else {
					String transfererror="YOU DO NOT HAVE ENOUGH MONEY TO TRANSFER!";
					request.setAttribute("transfererror", transfererror);
					RequestDispatcher rd=request.getRequestDispatcher("Transfer.jsp");
					rd.forward(request, response);
				}
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
}
