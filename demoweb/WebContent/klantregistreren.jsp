<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./resources/style/bootjes.css" rel="stylesheet" type="text/css"></link>
<title>Registreren als klant</title>
</head>
<body>
<h1>Klantregistratie formulier</h1>
			 <form name="klantRegistrerenForm" action="klantregistreren.html" method="post">
		 	 <span> Uw naam </span><input name="naam" type="text" />  <br>
		 	  <span> Uw adres </span><input name="adres" type="text" />  <br>
		 	   <span> Uw woonplaats </span><input name="woonplaats" type="text" />  <br>
		 	    <span> Uw telefoonnummer </span><input name="telefoonnummer" type="text" />  <br>
		     <input type="submit" value="Registreren"/>
		 </form>

	<a href="index.jsp">Terug</a>
</body>
</html>