<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--=== Affichage date et heure ===-->
<div class="container mt-2 mb-2 text-end">
	
	<!-- Info invisible, utile pour JavaScript -->
	<span id="decalageHoraire"><c:out value="${requestScope.heuresDecalageKlow}"></c:out></span>
	
	<!-- Infos visibles -->
	<i><span id="jour-date">Today dd Month yyyy</span>
	<span>(<c:out value="${requestScope.texteDecalageKlow}">UTC+02:00</c:out>)</span>
	<b><span id="temps">hh:mm:ss</span></b></i>
</div>
