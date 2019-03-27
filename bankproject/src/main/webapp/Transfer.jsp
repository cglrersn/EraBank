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
          var transfer;
          id = document.getElementById("id").value;
          firstname = document.getElementById("firstname").value;
          lastname = document.getElementById("lastname").value;
          transfer = document.getElementById("transfer").value;
          if (id=="" || firstname=="" || lastname=="" || transfer=="") 
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
	<%
		if(session.getAttribute("logid")==null){
			String a1="YOU MUST BE LOGGED IN TO TRANSFER!";
			request.setAttribute("transfermustlogin", a1);
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
	%>
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
        	<%
        		if(request.getAttribute("transfererror")!=null){
        			String a1=(String)request.getAttribute("transfererror");
        			out.println(a1);
        		}
        		else if(request.getAttribute("customererror")!=null){
        			String a1=(String)request.getAttribute("customererror");
        			out.println(a1);
        		}
        	%>
        	<h3>INFORMATION FOR RECEIVER ACCOUNT OWNER!</h3>
      		<form action="TransferController">
      			<pre>Enter The Id You Want To Transfer Money:<input type="number" name="transferid" maxlength="10" id="id"><br></pre>
      			<pre>Enter The First Name                   :<input type="text" name="firstname" maxlength="15" id="firstname"><br></pre>
      			<pre>Enter The Last Name                    :<input type="text" name="lastname" maxlength="15" id="lastname"><br></pre>
				<pre>Enter The Amount You Want To Transfer  :<input type="number" name="transfer" maxlength="10" id="transfer"><br></pre>
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