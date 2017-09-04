<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="./resources/style/bootjes.css" rel="stylesheet"
	type="text/css"></link>
</head>
<body>
	<h1>Start Tocht</h1>
		
		<c:choose>
   		<c:when test="${not empty errorMsg}">
   			<div class="error"> ${errorMsg} </div>
   			<hr/>
   		</c:when>
   	
   	</c:choose>
	<form name="startmeertocht" action="starttocht.html" method="post">
	
		<c:choose>
			<c:when test="${not empty bootNummer}">
	     			Bootnummer <input type="text" name="bootNummer"
					value="${bootNummer}" />
			</c:when>
			<c:otherwise>
					Boot type <select name="bootType">
					<option value="ROEI_BOOT">Roeiboot</option>
					<option value="ELEKTRISCHE_BOOT">Elektrisch</option>
				</select>
			</c:otherwise>
		</c:choose>

		<br /> Duur <select name="duur">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="4">4</option>
			<option value="8">8</option>

		</select> uren <br /> Tocht type <select name="tochtType">
			<option value="MEER">Meertocht</option>
			<option value="RIVIER">Riviertocht</option>
		</select> <br /> <input type="submit" value="Start Tocht" />
	</form>
	<hr />
	<a href="index.jsp">Terug</a>
</body>
</html>