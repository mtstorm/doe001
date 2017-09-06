<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserveer Tocht</title>
</head>

<body>
	<link href="./resources/style/style.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
 <div id="header">
 <img src="./resources/images/hello-kitty.svg_.png " alt="kitty" width="50" align="left"> 
  <h1> DOE <br>
  Reserveer Tocht </h1>

 </div>
 
 <div>
 <br/>
<h1> </h1>
	<form name="reserveertocht" action="reserveertocht.html" method="post">
	
	<c:choose>
   		<c:when test="${not empty errorMsg}">
   			<div class="error"> ${errorMsg} </div>
   			<hr/>
   		</c:when>
   			</c:choose>

		<html lang="en">
		<div>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">

</style>
<script>
	var dateToday = new Date(); 
	$(function() {
		$("#datepicker").datepicker({ 
			dateFormat: 'dd-mm-yy',
			numberOfMonths: 1,
	        showButtonPanel: true,
	        minDate: dateToday});
	});
</script>
   
</head>
</div>
<body>

	<p>
		Datum: <input type="text" id="datepicker" name="datum">
	</p>

</div>
</body>
		</html>

		Boot type <select name="bootType" class="selectInvoer">
			<option value="ROEI_BOOT">Roeiboot</option>
			<option value="ELEKTRISCHE_BOOT">Elektrisch</option>

		</select> <br /> 
		Begin tijd <select name="starttijd" class="selectInvoer">
			<option value="09:00"> 09.00 uur </option>
			<option value="10:00"> 10.00 uur </option>
			<option value="11:00"> 11.00 uur </option>
			<option value="12:00"> 12.00 uur </option>
			<option value="13:00"> 13.00 uur </option>
			<option value="14:00"> 14.00 uur </option>
			<option value="15:00"> 15.00 uur </option>
			<option value="16:00"> 16.00 uur </option>
			<option value="17:00"> 17.00 uur </option>


		</select> <br /> 
		Duur <select name="duur" class="selectInvoer">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="4">4</option>
			<option value="8">8</option>


		</select> uren <br /> 
		Tocht type <select name="tochtType" class="selectInvoer">
			<option value="MEER">Meertocht</option>
			<option value="RIVIER">Riviertocht</option>
		</select> <br /> <input type="submit" value="Reserveer Tocht" />
	</form>
	<br/>
	<a href="test2.html">Terug</a>


</body>
</html>