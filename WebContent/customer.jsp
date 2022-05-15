<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer_Service_IT19171302</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Buyer.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Buyer Service</h1>
				<form id="formCustomer" name="formCustomer" method="post" action="Customer.jsp">
					Name: 
					<input id="Name" name="Name" type="text" class="form-control form-control-sm"> <br> 
					Email: 
					<input id="Email" name="Email" type="text" class="form-control form-control-sm"> <br> 
				    Type:
					<input id="Type" name="Type" type="text" class="form-control form-control-sm"> <br> 
					Contact Number: 
					<input id="ContactNumber" name="ContactNumber" type="text" class="form-control form-control-sm"> <br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidBuyerIDSave" name="hidBuyerIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<%
				out.print(session.getAttribute("statusMsg"));
				%>
				
				<br>
				<div id="divCustomerGrid">

					<%
					Customer buyerObj = new Customer();
					out.print(CustomerObj.readCustomer());
					%>
				</div>

			</div>
		</div>
	</div>

</body>
</html>