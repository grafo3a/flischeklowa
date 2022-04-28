<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"></meta>
	<title>Connections control</title>
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
			<h3 class="titre-page">Controlling connections</h3>		<!-- adapter le titre ici -->
		</div>
		<br/>
	<!--/**************************************************-->
		
		<fieldset class="large800">
			<legend>State of the database</legend>
			<form method="post" action="test-jdbc">
				<br/>
				<label><c:out value="${requestScope.messageEtatBDD}" escapeXml="false" /></label>
				<br/>
				<br/>
				<button type="submit" class="large206">Test a JDBC connection</button>
			</form>
		</fieldset>
		<br/>
		<br/>
		<fieldset class="large800">
			<!-- === POOL DE CONNEXIONS === -->
			
			<legend>State of the connection pool (Tomcat)</legend>
			
			<c:choose>
				<%-- Si poolConnexions est actif --%>
				<c:when test="${not empty applicationScope.poolConnex}">
					<form method="post" action="controle-pool">
						<br/>
						<label class="vert">The connection pool is on</label>
						<br/>
						<br/>
						<input type="hidden" name="demande" value="arreterPool">
						<button type="submit" class="large206">Stop the connection pool</button>
						<label class="vert">${requestScope.resultatPool}</label>
					</form>
				</c:when>
				
				<%-- Si poolConnexions est inactif --%>
				<c:otherwise>
					<form method="post" action="controle-pool">
						<br/>
						<label class="rouge">The connection pool is off</label>
						<br/>
						<br/>
						<input type="hidden" name="demande" value="demarrerPool">
						
						<button type="submit" class="large206">Start the connection pool</button>
						<label class="rouge">${requestScope.resultatPool}</label>
					</form>
				</c:otherwise>
			</c:choose>
			
			<hr></hr>
			<div class="extreme-droite"><a href="controle-pool"><b><i>Refresh</i></b></a></div>	
		</fieldset>
		<br/>
		<br/>
		<fieldset class="large800">
			<!-- === FABRIQUE EMFACTORY === -->
			
			<legend>State of the JPA Factory (Hibernate)</legend>
			
			<c:choose>
				<%-- Si emFactory est actif --%>
				<c:when test="${not empty applicationScope.emFactory}">
					<form method="post" action="controle-emfactory">
						<br/>
						<label class="vert">The EntityManager Factory is on</label>
						<br/>
						<br/>
						<input type="hidden" name="demande" value="arreterEmfactory">
						<button type="submit" class="large206">Stop the EntityManager Factory</button>
						<label class="vert">${requestScope.resultatEmfactory}</label>
					</form>
				</c:when>
					
				<%-- Si la fabrique JPA est inactive --%>
				<c:otherwise>
					<form method="post" action="controle-emfactory">
						<br/>
						<label class="rouge">The EntityManager Factory is off</label>
						<br/>
						<br/>
						<input type="hidden" name="demande" value="demarrerEmfactory">
						
						<button type="submit" class="large206">Start the EntityManager Factory</button>
						<label class="rouge">${requestScope.resultatEmfactory}</label>
					</form>
				</c:otherwise>
			</c:choose>
			
			<hr></hr>
			<div class="extreme-droite"><a href="controle-emfactory"><b><i>Refresh</i></b></a></div>	
		</fieldset>
		
	<!--**************************************************-->
	</fieldset>
	
	<!-- Footer (JSP) -->
	
	<div class="pied2page">
		<jsp:include page="/WEB-INF/fragments/pied_de_page.xhtml"></jsp:include>
	</div>
	
	<!--==========-->
	
	<script type="text/javascript">
		document.getElementById("menuControle").className = "pageActuelle";		/* Adapter l'ID ici, Cf. menu-central.xhtml */
	</script>
	<!--/**************************************************-->
	
</body>
</html>
