<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] About</title>
	
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
					<div class="card-header text-center bg-orange-faible">ABOUT FLISCHEKLOWA</div>
					
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
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
			
				<div class="container border rounded bg-light p-1 mt-4">
					
					<p>
					FlischeKlowa stands for Flight Scheduler of Klow Airport.
					</p>
					<p>
					Klow Airport is the main airport of Klow.<br/>
					Klow is the capital city of Syldavia, an imaginary country in the Adventures of Tintin.<br/>
					Although it exists only in the fiction world, the airport is probably located somewhere around Warsaw in Poland.
					</p>
					<p>
					Using FlischeKlowa gives you an opportunity to have the power and responsibilities of a flight scheduling administrator in an airport.<br/>
					Come on, have fun and schedule flights by exploring FlischeKlowa.
					</p>
					
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
		
		var option = document.getElementById("menuAbout");
		option.style.border = "1px solid orange";		/* orange faible */
		option.style.borderRadius = "20%";
		option.style.borderBottom = "3px solid orange";
		option.style.color = "black";
	</script>
	
</body>
</html>
