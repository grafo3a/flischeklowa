<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] About</title>
	<link type="text/css" rel="stylesheet" href="common/css/bela.css" />
	<link rel="icon" type="image/png" href="favicon.png" />
</head>


<body>
	
	<!--**************************************************-->
	<!-- Header (JSP) -->
	
	<div class="entete">
		<jsp:include page="./fragments/entete.xhtml"></jsp:include>
	</div>
	
	<!-- Le menu central A gauche -->
	
	<div class="menu-central">
		<jsp:include page="./fragments/menu-central.xhtml"></jsp:include>
	</div>
	
	
	<!-- Main -->
	<fieldset class="contenu-principal">
	
	<!--==========-->
	
		<div>
			<h3 class="titre-page">About FlischeKlowa</h3>		<!-- adapter le titre ici -->
		</div>
		<br/>
	<!--/**************************************************-->
	
	
	<p>
	FlischeKlowa stands for Flight Scheduler of Klow Airport.
	</p>
	<p>
	Klow Airport is the main airport of Klow.<br/>
	Klow is the capital city of Syldavia, an imaginary country in the Adventures of Tintin.<br/>
	Although it exists only in the fiction world, the airport is probably located somewhere around Warsaw in Poland.
	</p>
	<p>
	Using FlischeKlowa gives you an opportunity to have the power and responsibilities of a flight scheduling administrator in an airport.<br/>
	Come on, have fun and schedule flights by exploring FlischeKlowa.
	</p>
	
	
	<!--**************************************************-->
	</fieldset>
	
	<!-- Footer (JSP) -->
	
	<div class="pied2page">
		<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	</div>
	
	<!--==========-->
	
	<script type="text/javascript">
		
		var option = document.getElementById("menuAbout");
		
		option.style.border = "1px solid green";				/* orange faible */
		option.style.backgroundColor = "#ffc966";
		option.style.borderRadius = "4px";
	</script>
	<!--/**************************************************-->
	
</body>
</html>
