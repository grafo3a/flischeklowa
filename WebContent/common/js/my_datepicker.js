
/* Pour le datepicker jQuery UI */

$( function() {
		$( "#datepicker" ).datepicker(
			{
				dateFormat: "yy-mm-dd",
				showOn: "focus", 
				buttonImage: "icone_datepicker_01.png",
				buttonImageOnly: true
			}
		);
	}
);
	
	
function trigger(){
	document.getElementById('datepicker').focus();
}
