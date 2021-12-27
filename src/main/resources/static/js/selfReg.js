M.AutoInit();

var 
	overlay = document.getElementById('overlay'),
	popup = document.getElementById('popup'),
	btnCerrarPopup = document.getElementById('btn-cerrar-popup'),
	overlay2 = document.getElementById('overlay2')
spiner = document.getElementById('spinner');


btnCerrarPopup.addEventListener('click', function(e) {
	e.preventDefault();
	overlay.classList.remove('active');
	popup.classList.remove('active');
});



function openPopUp() {
	overlay2.classList.remove('active');
	overlay.classList.add('active');
	popup.classList.add('active');
}

function openSpinner() {
	overlay2.classList.add('active');
}

function createAccount() {

	$("#formPatient").submit(function(event) {
		event.preventDefault();
		savePosition();
	})

	// DO POST
	$.ajax({
		type: "POST",
		contentType: "application/json",
		//dataType: "json",
		accept: "*/*",
		url: "/selfRegistration",
		data: JSON.stringify({
			"firstName": document.getElementById("firstName").value,
			"lastName": document.getElementById("lastName").value,
			"dni": document.getElementById("dni").value,
			"birthdate": new Date(document.getElementById("dateAux").value).toISOString(),
			"email": document.getElementById("email").value,
			"password": document.getElementById("password").value
		}),
		beforeSend: openSpinner()
	})
		.done(function() {
			// Por ejemplo removemos la imagen "cargando..."
			console.log("allgood");
			openPopUp();
		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		});
}

$('#formPatient').submit(function(e) {
	e.preventDefault();
	e.savePosition();

	var form = $(this);
	var url = form.attr('action');

	$.ajax({
		type: "POST",
		url: url,
		data: form.serialize(),
		beforeSend: openModal()
		//console.log('openSpinner')
	})
		.done(function() {
			// Por ejemplo removemos la imagen "cargando..."
			console.log('allgood');
			//	openPopUp();
		})
		.fail(function() {
			// Manejar errores
			console.log('all bad');
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status);
			}
		);

});






