<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="ISO-8859-1">
    <title>Dana Outdoor Adventures and Experiences</title>
    <link href="./resources/style/bootjes.css" rel="stylesheet" type="text/css"></link>
    <script src="./resources/javascript/miauw.js"></script>

    
   </head>
   <body>
   <!--  
   <audio controls autoplay><source src="./resources/music/boot.mp3" type="audio/mpeg"></audio>
   -->

	  <h1>Dana's Outdoor Experience</h1>
	  <img 
	  	width="100em" 
	  	alt="Hallo ik ben Kitty" 
	  	src="./resources/images/hello-kitty.svg_.png"></img>
	  	 <hr/>
	     <input type = "button" value= "Start Tocht" onclick = "location.href = 'starttocht.jsp'"/>
		<hr/>
		   	<c:choose>
   		<c:when test="${not empty errorMsg}">
   			<div class="error"> ${errorMsg} </div>
   			<hr/>
   		</c:when>
   	
   	</c:choose>
		 <form name="beindigTochtForm" action="beindigTocht.html" method="post">
		 	 <span> Bootnr </span><input name="eindetocht" type="text" />  <br>
		     <input type="submit" value="Beeindig tocht"/>
		 </form>
		  <hr/>
		 <input type="submit" value="Uitvoeren inspectie"/> <input name="inspectie" type="text" />  <br>
		 <input type="submit" value="Aantal beeindigde tochten"/> <br>
		 <input type="submit" value="Gemiddelde duur"/> <br>
		 
		 <form name="BootInfoForm" action="bootinformatie.html" method="get">
		 <span> Bootnr </span> <input name="bootnr" type="text" /> <br>
		 <input type="submit" value="Geef bootinformatie"/>
		 </form>
		 
		 <input type="submit" value="Registreren klant"/> <br>
		 <input type="submit" value="Reserveren"/> <br>
		 
		 <!-- Action verwijderd -->		
		 <form name="KlantInfoForm" method="get">
		 <span> Klantnr </span> <input name="klantnr" type="text" /> <br>
		 <input type="submit" value="Geef klantinformatie"/>
		 </form>
		 <hr/>
		 	<input type = "button" value= "Overzicht Vrije Boten" onclick = "location.href = 'getvrijeboten.html'"/>
		 <hr/>
		 <input type="submit" value="Afsluiten"/> <br>
	  </form>
	  
   </body>
</html>