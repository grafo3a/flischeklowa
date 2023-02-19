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
		<div class="row border-top border-bottom bg-light">
			
			<!--=== COLUMN A1 ===-->
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<div class="mt-4 mb-2">
					
					<div class="fs-3 text-center border rounded page-title">Today's flights</div>
					
					<div class="pt-2 mt-3">
						
						<!--=== Flights display filter ===-->
						<form method="post" action="horaire-vols">
							
							<nav class="nav justify-content-end">
								
								<div class="nav-item m-1">
									Direction:
								</div>
								
								<div class="nav-item m-1">
									<select id="sensChoisi" class="form-select form-select-sm large120px" name="sens">
										
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
								
								<div class="nav-item m-1">
									&nbsp;&nbsp;
									Country Code:
								</div>
								
								<div class="nav-item m-1">
									
									<input id="paysChoisi" type="text" class="form-control form-control-sm large120px"
											name="pays" placeholder="XY" value="<c:out value='${param.pays}'/>" />
								</div>
								
								<div class="nav-item mt-1 mb-1">
									<button type="submit"
											class="btn btn-warning btn-sm bouton-orangeatre large120px">Filter</button>
								</div>
								
							</nav>
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
		document.getElementById("menuHoraire").className = "pageActive";
		/* Adapter l'ID ici, Cf. menu-central.xhtml */
	</script>

</body>
</html>
