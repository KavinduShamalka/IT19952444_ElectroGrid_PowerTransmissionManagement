<%@page import="com.PowerTransmission"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/PowerTransmission.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-7"> 
<h1>Power Transmission Management</h1>
<form id="formPT" name="formPT" method="post" action="PowerTransmission.jsp">
 Power Transmission ID: 
 <input id="t_ID" name="t_ID" type="text" 
 class="form-control form-control-sm">
 <br> Commercial Area Units: 
 <input id="t_Acommercial" name="t_Acommercial" type="text" 
 class="form-control form-control-sm">
 <br> Residential Area Units: 
 <input id="t_Aresidential" name="t_Aresidential" type="text" 
 class="form-control form-control-sm">
 <br> Agriculture Area Units: 
 <input id="t_Aagriculture" name="t_Aagriculture" type="text" 
 class="form-control form-control-sm">
 <br> Transmitted Date: 
 <input id="t_date" name="t_date" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidptIDSave" 
 name="hidptIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPTDGrid">
 <%
	 PowerTransmission ptObj = new PowerTransmission(); 
	 out.print(ptObj.readPTdata()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>