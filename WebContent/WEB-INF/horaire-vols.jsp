<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] Today's flights</title>
	<!-- <link type="text/css" rel="stylesheet" href="common/css/bela.css" />  -->
	
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
				<div class="mt-4 mb-2">
					<div class="fs-3 text-center border rounded page-title">Today's flights</div>
					
					<div class="pt-2 pb-2 mt-4">
						
						<!--=== Flights display filter ===-->
						<form class="row" method="post" action="horaire-vols">
							
							<div class="col-5">
								<div class="input-group input-group-sm">
									<div class="input-group-text">Direction</div>
									
									<select id="sensChoisi" class="form-select form-select-sm" name="sens">
										
										<option value="Departure"
											<c:if test="${param.sens == 'Departure'}">selected="selected"</c:if>>
											Departure
										</option>
										
										<option value="Arrival"
											<c:if test="${param.sens == 'Arrival'}">selected="selected"</c:if>>
											Arrival
										</option>
										
										<option value="All"
											<c:if test="${param.sens == 'All'}">selected="selected"</c:if>>
											All
										</option>
									</select>
								</div>
							</div>
							
							<div class="col-5">
								<div class="input-group input-group-sm">
									<div class="input-group-text">Country Code</div>
									
									<input id="paysChoisi" type="text" class="form-control"
											name="pays" placeholder="ZZ" value="<c:out value='${param.pays}'/>" />
											
								</div>
							</div>
							
							<div class="col-2">
								<button type="submit"
										class="btn btn-warning btn-sm bouton-orangeatre w-100">Filter</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<!--=== COLUMN A2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				
				<!--*** Affichage date et heure ***-->
				<jsp:include page="./fragments/jour-date-heure.jsp"></jsp:include>
			</div>
		</div>
		
		<!--=== ROW B ===-->
		<div class="row">
			
			<!--=== COLUMN B1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
			
				<!--=== Affichage du tableau-horaire de vols ===-->
				<div id="affichageHoraireVols" class="mt-4">
					${requestScope.tableHoraireVols}
				</div>
				
				<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
			</div>
			
			
			<!--=== COLUMN B2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				
				<!--=== Le carrousel ===-->
				<jsp:include page="./fragments/carrousel-centre.xhtml"></jsp:include>
				
			</div>
			
			<p><br/></p> <!--=== Useful code line for correct display of components above ===-->
		</div>
	</div>
	
	
<!--=== FOOTER (JSP) ===-->
	<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	
	<script src="common/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		document.getElementById("menuHoraire").className = "pageActuelle";
		/* Adapter l'ID ici, Cf. menu-central.xhtml */
	</script>

</body>
</html>
