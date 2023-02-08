<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] Project</title>
	
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
					
					<div class="fs-3 text-center border rounded page-title">The FlischeKlowa Project</div>
					
					<div class="p-2 mt-3">
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
					As a programming project, FlischeKlowa is an application based on Java EE 8.<br/>
					The author of the project is the programmer Joseph B. Apasa.
					</p>
					<p>The following technologies are used in the project:</p>
						
					<ul>
						<li>Servlet 4 with Filter;</li>
						<li>JSP with JSTL;</li>
						<li>G11N (globalization including I18n);</li>
						<li>JDBC via DriverManager and DataSource (connection pool);</li>
						<li>JPA via Hibernate (for ORM data persistence);</li>
						<li>JSF 2.3 via Mojarra;</li>
						<li>CDI via Weld (for dependency injections);</li>
						<li>JAX-RS via Jersey (for exposing REST web services);</li>
						<li>JavaScript (plain aka Vanilla);</li>
						<li>HTML, CSS, Bootstrap;</li>
						<li>Maven, JUnit 5, tinylog;</li>
						<li>Databases: H2 (in-memory) and PosgreSQL (in cloud).</li>
					</ul>
					
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
		/* Pour les pages a-propos, projet, aide */
		document.getElementById("texteAbout").className = "pageActive";
	</script>
	
</body>
</html>
