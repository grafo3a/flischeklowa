<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.jsp.jstl.fmt.LocalizationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<%-- Globalisation G11n --%>
<fmt:setBundle basename="net.apasajb.aerojava.ressourcesG11n.paquetJSP"/>

<head>
	<meta charset="ISO-8859-1">
	<title>[FlischeKlowa] Contact</title>
	<link type="text/css" rel="stylesheet" href="common/css/bela.css" />
	<link rel="icon" type="image/png" href="favicon.png">
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
	<!-- ********** -->
	
		<div>
			<h3 class="titre-page">Contact</h3>		<!-- adapter le titre -->
		</div>
		<br/>
	<!--/**************************************************-->
	
	
	<p>
	A contact form will be available soon.<br/>
	Meanwhile you can contact Joseph by email or phone.
	</p>
	
	
	<!--**************************************************-->
	</fieldset>
	
	<!-- Footer (JSP) -->
	<div class="pied2page">
		<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	</div>
	
	<!-- ********** -->
	
	<script type="text/javascript">
		document.getElementById("menuContact").className = "pageActuelle";		/* Adapter l'ID ici */
	</script>
	<!--/**************************************************-->
	
</body>
</html>
