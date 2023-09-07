/* Pour le datepicker jQuery UI */

$(document).ready( function() {
	$( ".datepicker" ).datepicker({
			dateFormat: "yy-mm-dd",
			showOn: "focus"
		}
	);}
);


function trigger(){
	document.getElementById('datepicker').focus();
}
