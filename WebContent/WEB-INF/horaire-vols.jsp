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
				
				<div class="card border-warning mt-4 mb-2">
						<div class="card-header text-center bg-orange-faible">TODAY'S FLIGHTS</div>
						
						<div class="card-body p-2">
							<table>
								<tr>
									<td>Direction</td>
									<td>:&emsp;
										<span id="labelSensChoisi"><i>
											<c:out value="${param.sens}" />
											<c:if test="${empty param.sens}">Departure</c:if></i>
										</span>
									</td>
								</tr>
								<tr>
									<td>Country</td>
									<td>:&emsp;
										<span id="labelPaysChoisi"><i>
											<c:out value="${param.pays}" />
											<c:if test="${empty param.pays}">All</c:if></i>
										</span>
									</td>
								</tr>
							</table>
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
				
				
				<!--=== Flights display filter ===-->
				<div class="container border rounded bg-light p-2">
				
					<form class="row" method="post" action="horaire-vols">
					  <div class="col-6">
					    <div class="input-group input-group-sm">
					      <div class="input-group-text">Country Code</div>
					      <input type="text" class="form-control" name="pays" placeholder="ZZ" />
					    </div>
					  </div>
					  
					  <div class="col-4">
					    <select class="form-select form-select-sm" name="sens">
					      
					      <option value="Departure">Departure</option>
					      <option value="Arrival">Arrival</option>
					      <option value="All">All</option>
					    </select>
					  </div>
					  
					  <div class="col-2">
					    <button type="submit" class="btn btn-warning btn-sm bouton-orangeatre w-100">Filter</button>
					  </div>
					  
					</form>
				</div>
				
				<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
			</div>
			
			
			<!--=== COLUMN B2 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				
				<!--=== Le carrousel ===-->
				<jsp:include page="./fragments/carrousel-centre.xhtml"></jsp:include>
				
			</div>
			
			<p><br/></p> <!--=== Useful code line for the correct display of components above ===-->
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
