<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./resources/style/bootjes.css" rel="stylesheet" type="text/css"></link>
<title>Insert title here</title>
	<script>

	function loadSelection(){
		selectedBootnummer = document.getElementById('selectedbootnummer').value;
		if(selectedBootnummer != null){
			 trId = 'boot-'+ selectedBootnummer;
			 tr = document.getElementById(trId);
			 selectRow(tr, selectedBootnummer);
			
		}
	}
	function selectRow(tr,bootNummer) {
		
		siblings = tr.parentNode.childNodes;
		
		for(i=0;i<siblings.length;i++){
			sibling = siblings[i];
			//alert('JO ' + sibling.name);
			if(sibling.name='tr'){
				sibling.className ='';
			}
		}
		tr.className ='selected';
		document.getElementById('starttochtsubmit').disabled=false;
		document.getElementById('starttochtbootnummer').value= bootNummer;
		document.getElementById('selectedbootnummer').value= bootNummer;
		
	}
	</script>

</head>
<body onload="loadSelection()">
	<h1>Overzicht boten</h1>
	<p>Aantal bootjes <c:out value="${ boten.size()}"></c:out></p>
	
	<form name="boot" action="getvrijeboten.html" method="get">
		<input type="hidden" name="sortColumn">

		
	   <input id="selectedbootnummer" type="hidden" name="selectedbootnummer" value="${selectedbootnummer}"/>

		
	
		
	<table border="1" >
		<thead>
			<tr>
				<th>&nbsp;</th>
				<th><a onClick="javascript:document.forms['boot'].sortColumn.value='nr';document.forms['boot'].submit();">Bootnr</a></th>
				<th><a onClick="javascript:document.forms['boot'].sortColumn.value='naam';document.forms['boot'].submit();">Naam</a></th>
				<th><a onClick="javascript:document.forms['boot'].sortColumn.value='type';document.forms['boot'].submit();">Type</a></th>
				<th><a onClick="javascript:document.forms['boot'].sortColumn.value='ap';document.forms['boot'].submit();" >aantal personen</a></th>
				<th><a onClick="javascript:document.forms['boot'].sortColumn.value='inspectie';document.forms['boot'].submit();">inspectie</a></th>							
			</tr>
		</thead>
		<tbody>
		
			
			
			<c:forEach items="${boten}" var="boot" varStatus="theCount">
				<tr onclick="javascript:selectRow(this,${boot.nummer});" id="boot-${boot.nummer}">
					<td><c:out value="${theCount.index + 1}"></c:out></td>
					<td><c:out value="${boot.nummer}"></c:out></td>
					<td><c:out value="${boot.naam}"></c:out></td>
					<td><c:out value="${boot.type}"></c:out></td>
					<td><c:out value="${boot.aantalPersonen}"></c:out></td>	
					<td><c:out value="${boot.inspectie}"></c:out></td>						
				</tr>
			</c:forEach>		
	
		
		</tbody>
	</table>
	</form>
	<form name="starttocht" action="starttocht.html" method="get">
		<input id="starttochtbootnummer" type="hidden" name="bootNummer"/>
		<input id="starttochtsubmit" type="submit" value="Start Tocht" disabled="true"/>
	</form>
	
	<hr/>
	<a href="index.jsp">Terug</a>
</body>
</html>