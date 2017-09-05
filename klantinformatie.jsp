<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./resources/style/bootjes.css" rel="stylesheet" type="text/css"></link>
<title>Insert title here</title>
</head>
<body>
	<h1>Klantinformatie</h1>
	
	<form name="klantinformatie" action="klantinformatie" method="get">
		
	<table border="1" >
		<thead>
			<tr>
				<th>&nbsp;</th>
				<th>Naam</th>
				<th>Adres</th>
				<th>Woonplaats</th>
				<th>Telefoonnummer</th>							
			</tr>
		</thead>
		<tbody>
		
				<tr>
					<td>1</td>
					<td>${klantInfo.naam}</td>
					<td>${klantInfo.adres}</td>
					<td>${klantInfo.woonplaats}</td>
					<td>${klantInfo.telefoonnr}</td>					
				</tr>
		
		</tbody>
	</table>
	</form>
	<hr/>
	<a href="index.jsp">Terug</a>
</body>
</html>