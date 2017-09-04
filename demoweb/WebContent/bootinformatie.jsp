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
	<h1>Bootinformatie</h1>
	
	<form name="bootinformatie" action="bootinformatie" method="get">
		
	<table border="1" >
		<thead>
			<tr>
				<th>&nbsp;</th>
				<th>Bootnummer</th>
				<th>Boottype</th>
				<th>Naam</th>
				<th>Aantal personen</th>
				<th>Inspectie</th>
				<th>Verwachte eindtijd</th>							
			</tr>
		</thead>
		<tbody>
		
				<tr>
					<td>1</td>
					<td>${bootInfo.nummer}</td>
					<td>${bootInfo.type}</td>
					<td>${bootInfo.naam}</td>
					<td>${bootInfo.aantalPersonen}</td>	
					<td>${bootInfo.inspectie}</td>	
					<td> <c:choose> 
					<c:when test="${not empty bootInfo.verwachteEindtijd}">${bootInfo.verwachteEindtijd}
					</c:when>
					<c:otherwise>
					Onbekend
					</c:otherwise>
					</c:choose>
					
					</td>					
				</tr>
		
		</tbody>
	</table>
	
	<hr/>
	<a href="index.jsp">Terug</a>
</body>
</html>