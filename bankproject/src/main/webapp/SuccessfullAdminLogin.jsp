<%@page import="com.banking.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
        function empty()
        {
          var id;
          id = document.getElementById("id").value;
          if (id=="") 
           { 
              alert("Fill Every Blank!");
              return false;
           };
        }
    </script>
	<script>
    	history.forward();
	</script>
	<meta charset="ISO-8859-1">
	<title>BANK</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<img id="logo" src="https://3.bp.blogspot.com/-rqNrN6lKmec/V8HGstlFyMI/AAAAAAAAyyw/bCX0Z1ugh50PLugvEyMmDsyITSY9-E8lACLcB/s1600/Alzheimer%2527s%2BCare%2BBright%2BLight.jpg">
	<div id="name">ERA BANK</div>
	<table id="ut" border="1">
		<tr>
			<td><a href="Login.jsp">LOGIN</a></td>
			<td><a href="Register.jsp">NEW ACCOUNT</a></td>
			<td><a href="Balance.jsp">BALANCE</a></td>
			<td><a href="Deposit.jsp">DEPOSIT</a></td>
			<td><a href="Withdraw.jsp">WITHDRAW</a></td>
			<td><a href="Transfer.jsp">TRANSFER</a></td>
			<td><a href="CloseAccount.jsp">CLOSE ACC</a></td>
			<td><a href="ContactUs.jsp">CONTACT US</a></td>
		</tr>
	</table>
	<table id="dt" border="1">
      <tr>
        <td>
      		<h3>ADMIN!</h3>
      		<%
				if(session.getAttribute("adminlogid")==null){
					String a1="YOU MUST BE LOGGED IN ORDER TO MAKE THIS TRANSACTION!";
					request.setAttribute("transactionmustlogin", a1);
					RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);
				}
				else if(request.getAttribute("adminlogin")!=null){
					String a1=(String)request.getAttribute("adminlogin");
					out.println(a1);
				}
				else if(request.getAttribute("iderror")!=null){
      				String a1=(String)request.getAttribute("iderror");
      				out.println(a1);
      			}
				else if(request.getAttribute("viewcustomer")!=null){
					CustomerReturn a1=(CustomerReturn)request.getAttribute("viewcustomer");
	      			out.println(a1);
				}
			%>
			<form action="AdminController">
				<pre>Enter A Valid Customer Id To View:<input type="number" name="id" maxlength="10" id="id"><br></pre>
				<input type="submit" value="Submit" onClick="return empty()">
			</form>
      		
        </td>
      </tr>
    </table>	
	<div id="logout">
		<form action="LogoutController">
			<input type="submit" value="Logout">
		</form>
	</div>
</body>
</html>