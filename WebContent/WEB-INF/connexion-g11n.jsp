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
		
		<!--=== Affichage date et heure ===-->
		<div class="row bg-gris-faible">
			<jsp:include page="./fragments/jour-date-heure.jsp"></jsp:include>
		</div>
		
		<!--=== ROW A ===-->
		<div class="row">
			
			<!--=== COLUMN A1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">	
				<div class="fs-3 text-start page-title">
					<fmt:message key="J01_TITRE_CONNEXION" />
				</div>
				
				<div class="sous-titre"></div>
			</div>
			
			<!--=== COLUMN A2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
				
				<!--=== Le carrousel ===-->
				<jsp:include page="./fragments/carrousel-centre.xhtml"></jsp:include>
				
			</div>
		</div>
		
		
		<!--=== ROW B ===-->
		<div class="row">
			
			<!--=== COLUMN B1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
				
				<div class="container border rounded bg-light p-2 mt-4">
					
					<%-- Si connexion presente --%>
					<c:if test="${not empty sessionScope.courrielCookie}" >
						
						<div class="text-success">
							<fmt:message key="J02_CONNEXION_OK" />
							<c:out value="${sessionScope.courrielCookie}" />
						</div>
						
						<div class="border-top text-end mt-2">
							<i><a href="<c:url value='deconnexion-g11n'/>">
									[<fmt:message key="J03_DECONNEXION" />]</a></i>
						</div>
					</c:if>
					
					<%-- Si connexion absente --%>
					<c:if test="${empty sessionScope.courrielCookie}" >
						<div class="text-danger"><fmt:message key="J04_CONNEXION_KO" />
							<a href="aide"><i>[Help]</i></a>
						</div>
						
						<form method="post" action="connexion-g11n">
						 
							<div class="row mt-4">
								<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
								    <div class="input-group input-group-sm">
										
										<div class="input-group-text large120px">
											<fmt:message key="J05_LABEL_COURRIEL" />
										</div>
										<input type="text" class="form-control" name="courriel"
								      			placeholder="email@company.zz"
								      			value="${param.courriel}" />
									</div>
									
								</div>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<font color="red"><i>${requestScope.erreurCourriel}</i></font>
								</div>
								
								<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
									<div class="input-group input-group-sm">
										
										<div class="input-group-text large120px">
											<fmt:message key="J06_LABEL_MOT2P" />
										</div>
										
										<input type="password" class="form-control"
												name="mot2p" value="${param.mot2p}" />
									</div>
								</div>
								
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<font color="red"><i>${requestScope.erreurMot2p}</i></font>
								</div>
							</div>
							
							<div class="row mt-3">
								
								<div class="col-1">
									<input type="checkbox" id="idCheckbox09" name="contrat"
										<c:if test="${not empty param.contrat}">checked</c:if>>
								</div>
								
								<div class="col-11">
									<label for="idCheckbox09">
										<i><fmt:message key="J07_LABEL_CONTRAT" /></i>
									</label>
								</div>
								
								<font color="red"><i>${requestScope.erreurContrat}</i></font>
							</div>
							
							<button type="submit"
									class="btn btn-warning btn-sm bouton-orangeatre large220px mt-3">
									<fmt:message key="J08_VALIDER" />
					    	</button>
						</form>
						
						<div class="border-top mt-2">
							
							<!--=== Create-account-button ===-->
							<form action="inscription-g11n?#" class="mt-2">
								<button type="submit"
										class="btn btn-warning border btn-sm bg-orangeatre large220px">
									Create an account
								</button>
							</form>
						</div>
					</c:if>
				</div>
				
				<p><br/></p> <!--=== Useful code line for correct display of components above ===-->
			</div>
			
			
			<!--=== COLUMN B2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
			</div>
			
			<p><br/></p> <!--=== Useful code line for correct display of components above ===-->
		</div>
	</div>
	
	
	<!--=== FOOTER (JSP) ===-->
	<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	
	<script src="common/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		document.getElementById("menuLogin").className = "pageActive";		/* Adapter l'ID ici */
	</script>
	
</body>
</html>
