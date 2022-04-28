<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.jsp.jstl.fmt.LocalizationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<%-- Globalisation G11n --%>
<fmt:setBundle basename="net.apasajb.flischeklowa.ressourcesG11n.paquetJSP" />

<head>
	<meta charset="ISO-8859-1">
	<title>[FlischeKlowa] <fmt:message key="J01_TITRE_CONNEXION" /></title>
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
			<h3 class="titre-page"><fmt:message key="J01_TITRE_CONNEXION" /></h3>		<!-- adapter le titre -->
		</div>
		<br/>
	<!--/**************************************************-->
	
	
	<%-- Si connexion presente --%>
	<c:if test="${not empty sessionScope.courrielCookie}" >
		<fieldset class="large800">
			<div class="vert">
				<fmt:message key="J02_CONNEXION_OK" />
				<c:out value="${sessionScope.courrielCookie}" />
			</div>
			<hr class="gris"></hr>
			<div class="extreme-droite">
				<i><b><a href="<c:url value="deconnexion-g11n"/> "><fmt:message key="J03_DECONNEXION" /></a></b></i>
			</div>
		</fieldset>
	</c:if>
	
	<%-- Si connexion absente --%>
	<c:if test="${empty sessionScope.courrielCookie}" >
		<font color="red"><fmt:message key="J04_CONNEXION_KO" /></font>
		<br/>
		<br/>
		<form method="post" action="connexion-g11n">
			<fmt:message key="J05_LABEL_COURRIEL" />
			<br/>
			<input type="text" name="courriel" class="large200" value="${param.courriel}" />
			<font color="red"><i>${sessionScope.erreurCourriel}</i></font>
			<br/>
			<br/>
			<fmt:message key="J06_LABEL_MOT2P" />
			<br/>
			<input type="password" name="mot2p" class="large200" value="${param.mot2p}" />
			<font color="red"><i>${sessionScope.erreurMot2p}</i></font>
			<br/>
			<br/>
			<input type="checkbox" id="idCheckbox09" name="contrat"
				<c:if test="${not empty param.contrat}">checked</c:if>>
				
			<label for="idCheckbox09">
				<i><fmt:message key="J07_LABEL_CONTRAT" /></i>
			</label>
			<br/>
			<font color="red"><i>${sessionScope.erreurContrat}</i></font>
			<br/>
			<input type="submit" value=<fmt:message key="J08_VALIDER" /> class="large206" />
			
		</form>
		<%--/---------------------------/--%>
	</c:if>
	
	
	<!--**************************************************-->
	</fieldset>
	
	<!-- Pied de page -->
	<!-- Footer (JSP) -->
	
	<div class="pied2page">
		<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	</div>
	<!-- /********** -->
	
	
	<!-- ********** -->
	<script type="text/javascript">
		document.getElementById("menuConnexion").className = "pageActuelle";		/* Adapter l'ID ici */
	</script>
	<!--/**************************************************-->
	
</body>
</html>
