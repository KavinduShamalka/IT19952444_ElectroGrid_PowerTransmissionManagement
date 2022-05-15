<%@page import="com.PowerTransmission"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Transmission Management</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/PowerTransmission.js"></script>
</head>
<body style="background-color: #F3F3F3"> 
<div class="container p-3 mb-2 mx-auto" style="background-color: #F3F3F3" >
<div class="row mx-auto">
<div class="col-5 mx-auto"> 
<h1 style="width:550px; margin-left: -50px;">Power Transmission Management</h1>
<br>
<div class="p-3 mb-2 text-black mx-auto" style="background-color: #85B5F2; border-radius: 10px;">
<form id="formPT" name="formPT" method="post" action="PowerTransmission.jsp">
 Power Transmission ID: 
 <input id="t_ID" name="t_ID" type="text" 
 class="form-control form-control-sm" placeholder="Enter Power Transmission ID">
 <br> Commercial Area Units: 
 <input id="t_Acommercial" name="t_Acommercial" type="text" 
 class="form-control form-control-sm" placeholder="Enter Commercial Area Units">
 <br> Residential Area Units: 
 <input id="t_Aresidential" name="t_Aresidential" type="text" 
 class="form-control form-control-sm" placeholder="Enter Residential Area Units">
 <br> Agriculture Area Units: 
 <input id="t_Aagriculture" name="t_Aagriculture" type="text" 
 class="form-control form-control-sm" placeholder="Enter Agriculture Area Units">
 <br> Transmitted Date: 
 <input id="t_date" name="t_date" type="date" 
 class="form-control form-control-sm" placeholder="Enter Transmitted Date">
 <br>
 <div class="mx-auto"style="width:50px">
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidptIDSave" 
 name="hidptIDSave" value="">
 </div>
</form>
</div>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

</div> </div> </div> 
<div id="divPTDGrid" class="col-5 p-3 mb-2 " style="background-color: #F4F4F4; margin-left: 380px;">
 <%
	 PowerTransmission ptObj = new PowerTransmission(); 
	 out.print(ptObj.readPTdata()); 
 %>
</div>

</body>
</html>