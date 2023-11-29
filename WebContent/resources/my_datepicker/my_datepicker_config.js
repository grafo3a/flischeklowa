/* Pour le datepicker jQuery UI */

$(document).ready( function() {
	$( ".datepicker" ).datepicker({
			dateFormat: "yy-mm-dd",
			showOn: "focus"
		}
	);}
);


function afficherDatepicker(){
	document.getElementById('formulaireDetails:dateChoisie').focus();
}
