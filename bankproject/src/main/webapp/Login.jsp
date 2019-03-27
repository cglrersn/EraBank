<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
        function empty()
        {
          var id;
          var password;
          id = document.getElementById("id").value;
          password = document.getElementById("password").value;
          if (id=="" || password=="") 
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
        <td>
        	<%
        		if(request.getAttribute("balancemustlogin")!=null){
        			String a1=(String)request.getAttribute("balancemustlogin");
					out.println(a1);  
        		}
        		else if(request.getAttribute("depositmustlogin")!=null){
        			String a1=(String)request.getAttribute("depositmustlogin");
					out.println(a1); 
        		}
        		else if(request.getAttribute("transactionmustlogin")!=null){
        			String a1=(String)request.getAttribute("transactionmustlogin");
					out.println(a1);	
        		}
        		else if(request.getAttribute("transfermustlogin")!=null){
        			String a1=(String)request.getAttribute("transfermustlogin");
					out.println(a1);
        		}
        		else if(request.getAttribute("withdrawmustlogin")!=null){
        			String a1=(String)request.getAttribute("withdrawmustlogin");
					out.println(a1);
        		}
        		else if(request.getAttribute("loginerror")!=null){
        			String a1=(String)request.getAttribute("loginerror");
        			out.println(a1);
        		}
        		else if(session.getAttribute("logid")!=null){
    				String a1="YOU ARE ALREADY LOGGED IN!";
    				request.setAttribute("alreadylogin", a1);
    				RequestDispatcher rd=request.getRequestDispatcher("AlreadyLogin.jsp");
    				rd.forward(request, response);
    			}
        	%>
      		<form action="LoginController" method="post">
				<pre>Id      :<input type="number" name="id" maxlength="10" id="id"><br></pre>
				<pre>Password:<input type="password" name="password" maxlength="10" id="password"><br></pre>
				<input type="submit" value="Login" onClick="return empty()">	
			</form>
        </td>
      </tr>
    </table>	

</body>
</html>