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
	<link href="./resources/style/bootjes.css" rel="stylesheet"
		type="text/css"></link>
</head>
<body>
	<h1>Reserveer Tocht</h1>
	<form name="reserveertocht" action="reserveertocht.html" method="post">
	
	

			<html lang = "en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
</head>
<body>

	<p>
		Datum: <input type="text" id="datepicker" name = "datum">
	</p>


</body>
			</html>

		Boot type <select name="bootType">
			<option value="ROEI_BOOT">Roeiboot</option>
			<option value="ELEKTRISCHE_BOOT">Elektrisch</option>

		</select> Begin tijd <select name="starttijd">
			<option value="0900"> 09.00 uur </option>
			<option value="1000"> 10.00 uur </option>
			<option value="1100"> 11.00 uur </option>
			<option value="1200"> 12.00 uur </option>
			<option value="1300"> 13.00 uur </option>
			<option value="1400"> 14.00 uur </option>
			<option value="1500"> 15.00 uur </option>
			<option value="1600"> 16.00 uur </option>
			<option value="1700"> 17.00 uur </option>


		</select> <br /> Duur <select name="duur">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="4">4</option>
			<option value="8">8</option>


		</select> uren <br /> Tocht type <select name="tochtType">
			<option value="MEER">Meertocht</option>
			<option value="RIVIER">Riviertocht</option></select> <br /> <input type="submit" value="Reserveer Tocht"/>
	</form>




</body>
</html>