<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"></meta>
	<title>Welcome to FlischeKlowa!</title>
	<link type="text/css" rel="stylesheet" href="common/css/bela.css" />
	<link rel="icon" type="image/png" href="favicon.png" />
	<script type="text/javascript" src="common/js/main.js"></script>
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
			<div class="moment">
				<span id="jour-date">Today dd Month yyyy</span>
				<span>---</span>
				<b><span id="temps">hh:mm:ss</span></b>
				<span>(<c:out value="${requestScope.texteDecalageKlow}">UTC+02:00</c:out>)</span>
				<span id="decalageHoraire"><c:out value="${requestScope.heuresDecalageKlow}">2</c:out></span>
			</div>
			<h3 class="titre-page">Today's flights</h3>		<!-- adapter le titre ici -->
		</div>
		<br/>
	<!--/**************************************************-->
		
		<div>
			<i>
			<span>---------- Direction:</span>
			<span id="labelSensChoisi">
				<b>
				<c:out value="${param.sens}" />
				<c:if test="${empty param.sens}">Departure</c:if>
				</b>
			</span>
			<span>---------- Country Code:</span>
			<span id="labelPaysChoisi">
				<b>
				<c:out value="${param.pays}" />
				<c:if test="${empty param.pays}">All</c:if>
				</b>
			</span>
			</i>
			<br/>
		</div>
		
		<br/>
		<br/>
		
		<!--=== Affichage de la table-horaire de vols (avec coloration des statuts) ===-->
		<div id="affichageHoraireVols" class="affichage-horaire">
			${requestScope.tableHoraireVols}
		</div>
		<br/>
		
		<b><i>Filter</i></b>
		
		<%--=== affichage du formulaire ===--%>
		<form method="post" action="horaire-vols">
			Direction:
			<select name="sens" class="large140">
				<option value="Departure">Departure</option>
				<option value="Arrival">Arrival</option>
				<option value="All">All</option>
			</select>
			
			Country Code: <input type="text" name="pays" placeholder="e.g. PL" class="large140" />
			<button type="submit" class="large140">Display</button>
		</form>
		
	<!--**************************************************-->
	</fieldset>
	
	
	<!-- Footer (JSP) -->
	
	<div class="pied2page">
		<jsp:include page="/WEB-INF/fragments/pied_de_page.xhtml"></jsp:include>
	</div>
	
	<!--==========-->
	
	<script type="text/javascript">
		document.getElementById("menuHoraire").className = "pageActuelle";		/* Adapter l'ID ici, Cf. menu-central.xhtml */
	</script>
	<!--/**************************************************-->
	
</body>
</html>
