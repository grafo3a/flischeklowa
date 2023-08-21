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
		<div class="row bg-gris-faible border-bottom-gris">
			<jsp:include page="./fragments/jour-date-heure.jsp"></jsp:include>
		</div>
		
		<!--=== ROW A ===-->
		<div class="row">
			
			<!--=== COLUMN A1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
					
				<div class="fs-3 text-start page-title">Contact</div>
				
				<div class="sous-titre"></div>
			</div>
			
			<!--=== COLUMN A2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
				
				<!--=== Le carrousel ===-->
				<jsp:include page="./fragments/carrousel-centre.xhtml"></jsp:include>
				
			</div>
		</div>
		
		
		<!--=== ROW B ===-->
		<div class="row border-bottom-gris">
			
			<!--=== COLUMN B1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
			
				<div class="container border rounded bg-light p-1 mt-4">
					<p>
					A contact form will be available soon.<br/>
					Meanwhile you can contact Joseph by email or phone.
					</p>
				</div>
				
				<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
			</div>
			
			
			<!--=== COLUMN B2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
			</div>
			
			<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
		</div>
	</div>
	
	
	<!--=== FOOTER (JSP) ===-->
	<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	
	<script src="common/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		document.getElementById("menuContact").className = "pageActive";		/* Adapter l'ID ici */
	</script>
	
</body>
</html>
