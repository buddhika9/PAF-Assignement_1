<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="com.Fund"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Management</title>

<link rel="stylesheet" href="view/bootstrap.min.css">

</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<hr style="border-width: 5px; border-color: black;">
<h2>Fund Management </h2>
<hr style="border-width: 5px; border-color: black;">

<form id="formItem" name="formItem">
	  <br>
	  Project ID: 
	 <input id="ProjectID" name=ProjectID type="text" 
	 class="form-control form-control-sm">
	 <br> Announcement: 
	 <input id="Announcement" name="Announcement" type="text" 
	 class="form-control form-control-sm">
	 <br> Duration: 
	 <input id="Duration" name="Duration" type="text" 
	 class="form-control form-control-sm">
	 <br> Instructions: 
	 <input id="Instructions" name="Instructions" type="text" 
	 class="form-control form-control-sm">
	 <br> Amount:
	 <input id="Amount" name="Amount" type="text" 
	 class="form-control form-control-sm">
	 <br>
 
 
 
 
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">

<div>
	<hr style="border-width: 8px; border-color: black;">
		<h1>Previously Added Fund Details</h1>
	<hr style="border-width: 8px; border-color: black;">
		
		<br>

</div>
 <%
 Fund FundObj = new Fund(); 
 out.print(FundObj.ReadFundDetails()); 
 %>
</div>
</div> </div> </div> 


<script src="compo/jquery-3.2.1.min.js"></script>
<script src="compo/fund.js"></script>

<!-- required for number formatting only -->
<script src="compo/numeral.min.js"></script>
<!-- the jquery calx lib -->
<script src="compo/jquery-calx-2.2.8.min.js"></script>


</body>
</html>