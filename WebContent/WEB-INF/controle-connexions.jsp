<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] Controlling connections</title>
	
	<link rel="stylesheet" href="common/css/bootstrap.css" />
	<link rel="stylesheet" href="common/css/bootstrap-icons.css" />
	<link rel="stylesheet" href="common/css/bela.css" />
	<link rel="icon" type="image/png" href="favicon.png" />
	<script type="text/javascript" src="common/js/main.js"></script>
</head>


<body>
	
	<!--=== HEADER (JSP) ===-->
	<jsp:include page="./fragments/entete.xhtml"></jsp:include>
	<jsp:include page="./fragments/menu-central.xhtml"></jsp:include>
	
	
	<!--=== MAIN ===-->
	<div class="container-fluid">
		
		<!--=== ROW A ===-->
		<div class="row border bg-light">
			
			<!--=== COLUMN A1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				
				<div class="card border-warning mt-4 mb-2">
					<div class="card-header text-center bg-orange-faible">CONNECTIONS CONTROL</div>
					
					<div class="card-body p-2">
					  <p class="card-text"></p>
					</div>
				</div>
			</div>
			
			<!--=== COLUMN A2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				
				<!--=== Affichage date et heure ===-->
				<jsp:include page="./fragments/jour-date-heure.jsp"></jsp:include>
			</div>
		</div>
		
		
		<!--=== ROW B ===-->
		<div class="row">
			
			<!--=== COLUMN B1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 mt-4">
				
				<div class="container border rounded bg-light p-1">
					<h5>State of the database</h5>
					
					<form method="post" action="test-jdbc">
						<label><c:out value="${requestScope.messageEtatBDD}" escapeXml="false" /></label>
						<br/>
						<br/>
						<button type="submit" class="btn btn-warning btn-sm bouton-orangeatre large220px"
								>Test a JDBC connection</button>
					</form>
				</div>
				
				
				<!--=== POOL DE CONNEXIONS ===-->
				<div class="container border rounded bg-light p-1 mt-4">
					
					<h5>State of the connection pool (Tomcat)</h5>
					
					<c:choose>
						<%--=== Si poolConnexions est actif ===--%>
						<c:when test="${not empty applicationScope.poolConnex}">
							<form method="post" action="controle-pool">
								
								<label class="text-success">The connection pool is on</label>
								<br/>
								<br/>
								<input type="hidden" name="demande" value="arreterPool">
								<button type="submit"
										class="btn btn-warning btn-sm bouton-orangeatre large220px"
										>Stop the connection pool</button>
								<label class="text-success">${requestScope.resultatPool}</label>
							</form>
						</c:when>
						
						<%--=== Si poolConnexions est inactif ===--%>
						<c:otherwise>
							<form method="post" action="controle-pool">
								
								<label class="text-danger">The connection pool is off</label>
								<br/>
								<br/>
								<input type="hidden" name="demande" value="demarrerPool">
								
								<button type="submit"
										class="btn btn-warning btn-sm bouton-orangeatre large220px"
											>Start the connection pool</button>
								<label class="text-danger">${requestScope.resultatPool}</label>
							</form>
						</c:otherwise>
					</c:choose>
					
					<hr></hr>
					<div class="text-end"><a href="controle-pool"><b><i>Refresh</i></b></a></div>	
				</div>
				
				<!--=== FABRIQUE EMFACTORY ===-->
				<div class="container border rounded bg-light p-1 mt-4">
					
					<h5>State of the JPA Factory (Hibernate)</h5>
					
					<c:choose>
						<%--=== Si emFactory est actif ===--%>
						<c:when test="${not empty applicationScope.emFactory}">
							<form method="post" action="controle-emfactory">
								
								<label class="text-success">The EntityManager Factory is on</label>
								<br/>
								<br/>
								<input type="hidden" name="demande" value="arreterEmfactory">
								<button type="submit" class="btn btn-warning btn-sm bouton-orangeatre large220px"
									>Stop the EntityManager Factory</button>
								<label class="text-success">${requestScope.resultatEmfactory}</label>
							</form>
						</c:when>
						
						<%--=== Si la fabrique JPA est inactive ===--%>
						<c:otherwise>
							<form method="post" action="controle-emfactory">
								
								<label class="text-danger">The EntityManager Factory is off</label>
								<br/>
								<br/>
								<input type="hidden" name="demande" value="demarrerEmfactory">
								
								<button type="submit" class="btn btn-warning btn-sm bouton-orangeatre large220px"
									>Start the EntityManager Factory</button>
								<label class="text-danger">${requestScope.resultatEmfactory}</label>
							</form>
						</c:otherwise>
					</c:choose>
					
					<hr></hr>
					<div class="text-end"><a href="controle-emfactory"><b><i>Refresh</i></b></a></div>
				</div>
				
				<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
			</div>
			
			
			<!--=== COLUMN B2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				
				<!--=== Le caroussel ===-->
				
				<div class="container border rounded caroussel shadow-sm m-4 p-2">
					<img src="common/images/airport_by_weston-mackinnon_1080p.jpg"
						alt="airport-image" width="500px" height="300px" />
				</div>
				
			</div>
			
			<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
		</div>
	</div>
	
	
	<!--=== FOOTER (JSP) ===-->
	<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	
	<script src="common/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		document.getElementById("menuControle").className = "pageActuelle";
		/* Adapter l'ID ici, Cf. menu-central.xhtml */
	</script>
	
</body>
</html>
