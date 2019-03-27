package com.banking.servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.api.mysqla.result.Resultset;
public class CustomerDao {
	public CustomerReturn getCustomer(int id) {
		CustomerReturn a=new CustomerReturn();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			Statement st=con.createStatement();
			Resultset rs=(Resultset) st.executeQuery("SELECT * FROM customers WHERE id="+id);
			if(((ResultSet) rs).next()) {
				a.setId(((ResultSet) rs).getInt("id"));
				a.setFirst_name(((ResultSet) rs).getString("first_name"));
				a.setLast_name(((ResultSet) rs).getString("last_name"));
				a.setMoney(((ResultSet) rs).getInt("money"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return a;
	}

}
