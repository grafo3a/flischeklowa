<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<head>
	<meta charset="ISO-8859-1"></meta>
	<title>[FlischeKlowa] Project</title>
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
			<h3 class="titre-page">The FlischeKlowa project</h3>		<!-- adapter le titre ici -->
		</div>
		<br/>
	<!--/**************************************************-->
	
	<p>
	As a programming project, FlischeKlowa is an application based on Java EE 8.<br/>
	The author of the project is the programmer Joseph B. Apasa.
	</p>
	<p>The following technologies are used in the project:</p>
	
	<ul class="tech">
		<li>Servlet 4 with Filter;</li>
		<li>JSP with JSTL;</li>
		<li>G11N (globalization including I18n);</li>
		<li>JDBC via DriverManager and DataSource (connection pool);</li>
		<li>JPA via Hibernate (for ORM data persistence);</li>
		<li>JSF 2.3 via Mojarra;</li>
		<li>CDI via Weld (for dependency injections);</li>
		<li>JAX-RS via Jersey (for exposing REST web services);</li>
		<li>JavaScript (plain aka Vanilla);</li>
		<li>HTML with CSS;</li>
		<li>Maven, JUnit 5, tinylog;</li>
		<li>Databases: H2 (in-memory) and PosgreSQL (in cloud).</li>
	</ul>
	
	
	<!--**************************************************-->
	</fieldset>
	
	<!-- Footer (JSP) -->
	
	<div class="pied2page">
		<jsp:include page="./fragments/pied_de_page.xhtml"></jsp:include>
	</div>
	
	<!--==========-->
	
	<script type="text/javascript">
		
		var option = document.getElementById("menuAbout");
		
		option.style.border = "1px solid green";				/* orange faible */
		option.style.backgroundColor = "#ffc966";
		option.style.borderRadius = "4px";
	</script>
	<!--/**************************************************-->
	
</body>
</html>
