<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
        function empty()
        {
          var id;
          var firstname;
          var lastname;
          var money;
          var password;
          id = document.getElementById("id").value;
          firstname = document.getElementById("firstname").value;
          lastname = document.getElementById("lastname").value;
          money = document.getElementById("money").value;
          password = document.getElementById("password").value;
          if (id=="" || firstname=="" || lastname=="" || money=="" || password=="") 
           { 
              alert("Fill Every Blank!");
              return false;
           };
        }
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
        <td align="center">
        	<%
        		if(request.getAttribute("passworderror")!=null){
        			String a1=(String)request.getAttribute("passworderror");
        			out.println(a1);
        		}
        		else if(session.getAttribute("logid")!=null){
    				String a1="LOG OUT TO CREATE A NEW ACCOUNT!";
    				request.setAttribute("alreadyloginre", a1);
    				RequestDispatcher rd=request.getRequestDispatcher("AlreadyLogin.jsp");
    				rd.forward(request, response);
    			}
        		else if(request.getAttribute("sameiderror")!=null){
        			String a1=(String)request.getAttribute("sameiderror");
        			out.println(a1);
        		}
        		else if(request.getAttribute("registeriderror")!=null){
        			String a1=(String)request.getAttribute("registeriderror");
        			out.println(a1);
        		}
        		
        	%>
      		<form action="RegisterController">
				<pre>Id             :<input type="number" name="id" maxlength="10" id="id"><br></pre>
				<pre>First Name     :<input type="text" name="first_name" maxlength="15" id="firstname"><br></pre>
				<pre>Last Name      :<input type="text" name="last_name" maxlength="15" id="lastname"><br></pre>
				<pre>Money Amount   :<input type="number" name="money"maxlength="10" id="money"><br></pre>
				<pre>Password       :<input type="password" name="password" maxlength="10" id="password"><br></pre>
				<pre>Password(Again):<input type="password" name="passwordagain" maxlength="10" id="passwordagain"><br></pre>
				<input type="submit" value="Submit" onClick="return empty()">	
			</form>
        </td>
      </tr>
    </table>
	
</body>
</html>