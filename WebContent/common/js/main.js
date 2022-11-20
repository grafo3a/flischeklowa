/**
 * INTEGRATION D'AJAX
 */

var xmlHttpRequest;
var tour = 0;


function sendInfo() {
	
	// Chargement d'un objet Ajax
	if (window.XMLHttpRequest) {
		xmlHttpRequest=new XMLHttpRequest();  
		
	} else if(window.ActiveXObject){
		xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	
	// Phase Aller
	try {
	  
		xmlHttpRequest.onreadystatechange=getInfo;  
		xmlHttpRequest.open("GET",urlAjax,true);
		xmlHttpRequest.send();
		
	} catch(ex) {
		alert("[ERROR] Not able to connect to server. " + ex.message);
	}
}


function getInfo(){		//--- Phase Retour
	
	if(this.readyState == 4 && this.status == 200){
		
		var responseVal=xmlHttpRequest.responseText;
		document.getElementById('affichageHoraireVols').innerHTML = responseVal;
		
	} else {
		//alert("--- Reponse Ajax non ressue!");
	}
}


function actualiserInfosChaqueSeconde() {
	
	//--- On actualise l'heure affichee
	//--- setInterval = execute cette fonction apres chaque x millisecondes.
	//--- La fonction doit etre sans parametre.
	
	var moment = new Date();
	var nombreSecondes = moment.getSeconds();
	var nombreMinutes = moment.getMinutes();
	var nombreHeuresLocal = moment.getHours();
	var offsetLocal = moment.getTimezoneOffset();
	
	var nombreHeuresGMT = nombreHeuresLocal + (offsetLocal/60);
	var offsetKlow = parseInt(document.getElementById("decalageHoraire").innerText.trim());
	var nombreHeures = nombreHeuresGMT + offsetKlow;
	var numeroDate = moment.getDate();
	
	var heures = nombreHeures.toString();
	var minutes = nombreMinutes.toString();
	var secondes = nombreSecondes.toString();
	
	
	if (nombreHeures >= 24){
		nombreHeures = nombreHeures - 24;
		numeroDate = numeroDate + 1;
	}
	
	if (nombreHeures < 10) {
		heures = "0" + nombreHeures;
	}
	
	if (nombreMinutes < 10) {
		minutes = "0" + nombreMinutes;
	}
	
	if (nombreSecondes < 10) {
		secondes = "0" + nombreSecondes;
	}
	
	var heureAffichee = heures  + ":" + minutes + ":" + secondes;
	
	//--- On actualise l'heure
	document.getElementById('temps').innerHTML=heureAffichee;
	
	// Chaque 1ere fois ou 1ere seconde, on met à jour la longue date
	// Et si on est sur la JSP d'accueil, on envoie une requete Ajax qui met à jour l'horaire.
	
	if (tour == 0 || nombreSecondes == 1) {
		
		//On met A jour la longue date
		let jour = moment.getDay();
		let dayName = getDayName(jour);
		let dateNow = numeroDate.toString();
		
		if (numeroDate < 10) {
			dateNow = "0" + dateNow;
		}
		
		let mois = moment.getMonth();
		let monthName = getMonthName(mois);
		let annee = moment.getFullYear().toString();
		
		let jourDate = dayName + " " + dateNow + " " + monthName + " " + annee; 
		
		document.getElementById('jour-date').innerHTML = jourDate;
			
		try {
			var sensAjax = document.getElementById('labelSensChoisi').innerText.trim();
			var paysAjax = document.getElementById('labelPaysChoisi').innerText.trim();
			
			if (paysAjax == "All") {
				paysAjax = "";
			}
			
			urlAjax="horaire-vols-ajax?sens=" + sensAjax + "&pays=" + paysAjax;
			sendInfo();
		}
		catch(ex) { //ignore
		}
	}
	
	tour++;
}


function getDayName(jour) {
	
	switch(jour){
		
		case 0:
			return "Sunday";
		
		case 1:
			return "Monday";
		
		case 2:
			return "Tuesday";
			
		case 3:
			return "Wednesday";
			
		case 4:
			return "Thursday";
			
		case 5:
			return "Friday";
			
		case 6:
			return "Saturday";
	}
}


function getMonthName(mois) {
	
	switch(mois) {
		
		case 0:
			return "January";
		
		case 1:
			return "February";
		
		case 2:
			return "March";
		
		case 3:
			return "April";
		
		case 4:
			return "May";
		
		case 5:
			return "June";
		
		case 6:
			return "July";
		
		case 7:
			return "August";
		
		case 8:
			return "September";
		
		case 9:
			return "October";
		
		case 10:
			return "November";
		
		case 11:
			return "December";
	}
}


function activerAjax(){
	
	//--- D'abord on execute la fonction 1 fois,
	actualiserInfosChaqueSeconde();
	
	//--- ensuite on active la repetition des executions chaque seconde
	//--- Cette repetition met a jour l'heure chaque seconde et l'horaire chaque minute.
	setInterval(actualiserInfosChaqueSeconde, 1000);
}

//--- Quand la page est completement chargee
window.onload = activerAjax;

//----------------------------------
