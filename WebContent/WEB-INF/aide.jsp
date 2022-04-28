<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] Help</title>
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
			<h3 class="titre-page">Help</h3>		<!-- adapter le titre ici -->
		</div>
		<br/>
	<!--/**************************************************-->
	
		<p>
		<b>Question</b><br/>
		The most frequently asked question is:<br/>
		How can I login to FlischeKlowa?
		</p>
		
		<p>
		<b>Answer</b><br/>
		To login, simply type:<br/>
		- any valid email address<br/>
		- as password, any word with 5 or more characters.<br/>
		Only!
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
