M.AutoInit();


function searchBreackfast(breakfastId) {
	$.ajax({
		type: "POST",
		url: "/nutriplan/getBreakfast",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": breakfastId
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function(response) {
			document.getElementById('modal1').classList.add('modal');
			var content = '<div class="modal-content" style="padding: 15px;">' +
				'<div class="row m-0">' +
				'<h6 style="padding-left: 0px; font-weight:600;">Edicion del plato</h6>' +
				'</div>';
			console.log(response.id)
			for (var i = 0; i < response.bVariant.length; i++) {
				content += '<div class="row" style="margin:0px; margin-bottom:7px;">' +
					'<div class="col col-12 l9 m9 s12" style="padding-left: 0px;">' +
					'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: ' + (i + 1) + '</p>' +
					'<span style="font-size: 12px;">' + response.bVariant[i].description + '</span>' +
					'</div>' +
					'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
					'<form id="theForm1" action="/nutriplan/changeBreakfast" method="post">' +
					'<a id="' + response.id + '|' + response.bVariant[i].id + '"' +
					'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"' +
					'style="font-size: 12px;" href="javascript:{}" onclick="changeBreakfast(this.id)">Seleccionar</a>' +
					'</form>' +
					'</div>' +
					'</div>';
			}

			content += '</div>' +
				'<div class="modal-footer">' +
				'<a href="/professional/mypatients/patient?patientId=" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>' +
				'</div>';

			$('.modal').append(content)
			var elems = document.getElementById('modal1');
			var instances = M.Modal.init(elems, {
				dismissible: false
			});
			instances.open();
		})
		.fail(function() {
			console.log('all bad')
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status)
			}
		);
}

function changeBreakfast(breakfastId) {
	$('.modal').modal('close')
	$.ajax({
		type: "POST",
		url: "/nutriplan/changeBreakfast",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			//"id": breakfastid
			'breakfastId': breakfastId,
			'patient': {
				'patientId': document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function() {
			// Por ejemplo removemos la imagen "cargando..."
			console.log("allgood");
			M.toast({ html: '<b>Actualizando Plato...</b>', classes: 'rounded top green darken-1', completeCallback: function() { window.location.reload() } })

		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		})
}

function searchMsnack(msnackId) {
	$.ajax({
		type: "POST",
		url: "/nutriplan/getMsnack",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": msnackId
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function(response) {
			document.getElementById('modal1').classList.add('modal');
			var content = '<div class="modal-content" style="padding: 15px;">' +
				'<div class="row m-0">' +
				'<h6 style="padding-left: 0px; font-weight:600;">Edicion del plato</h6>' +
				'</div>';
			console.log(response.id)
			for (var i = 0; i < response.msVariant.length; i++) {
				content += '<div class="row" style="margin:0px; margin-bottom:7px;">' +
					'<div class="col col-12 l9 m9 s12" style="padding-left: 0px;">' +
					'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: ' + (i + 1) + '</p>' +
					'<span style="font-size: 12px;">' + response.msVariant[i].description + '</span>' +
					'</div>' +
					'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
					'<form id="theForm1" action="/nutriplan/changeMsnack" method="post">' +
					'<a id="' + response.id + '|' + response.msVariant[i].id + '"' +
					'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"' +
					'style="font-size: 12px;" href="javascript:{}" onclick="changeMsnack(this.id)">Seleccionar</a>' +
					'</form>' +
					'</div>' +
					'</div>';
			}

			content += '</div>' +
				'<div class="modal-footer">' +
				'<a href="/home" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>' +
				'</div>';

			$('.modal').append(content)
			var elems = document.getElementById('modal1');
			var instances = M.Modal.init(elems, {
				dismissible: false
			});
			instances.open();
		})
		.fail(function() {
			console.log('all bad')
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status)
			}
		);
}

function changeMsnack(msnackId) {
	$('.modal').modal('close')
	$.ajax({
		type: "POST",
		url: "/nutriplan/changeMsnack",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			//"id": breakfastid
			'msnackId': msnackId,
			'patient': {
				'patientId': document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function() {
			// Por ejemplo removemos la imagen "cargando..."
			console.log("allgood");
			M.toast({ html: '<b>Actualizando Plato...</b>', classes: 'rounded top green darken-1', completeCallback: function() { window.location.reload() } })

		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		})
}

function searchLunch(lunchId) {
	$.ajax({
		type: "POST",
		url: "/nutriplan/getLunch",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": lunchId
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function(response) {
			document.getElementById('modal1').classList.add('modal');
			var content = '<div class="modal-content" style="padding: 15px;">' +
				'<div class="row m-0">' +
				'<h6 style="padding-left: 0px; font-weight:600;">Edicion del plato</h6>' +
				'</div>';
			console.log(response.id)
			for (var i = 0; i < response.lVariant.length; i++) {
				content += '<div class="row" style="margin:0px; margin-bottom:7px;">' +
					'<div class="col col-12 l9 m9 s12" style="padding-left: 0px;">' +
					'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: ' + (i + 1) + '</p>' +
					'<span style="font-size: 12px;">' + response.lVariant[i].description + '</span>' +
					'</div>' +
					'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
					'<form id="theForm1" action="/nutriplan/changeLunch" method="post">' +
					'<a id="' + response.id + '|' + response.lVariant[i].id + '"' +
					'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"' +
					'style="font-size: 12px;" href="javascript:{}" onclick="changeLunch(this.id)">Seleccionar</a>' +
					'</form>' +
					'</div>' +
					'</div>';
			}

			content += '</div>' +
				'<div class="modal-footer">' +
				'<a href="/home" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>' +
				'</div>';

			$('.modal').append(content)
			var elems = document.getElementById('modal1');
			var instances = M.Modal.init(elems, {
				dismissible: false
			});
			instances.open();
		})
		.fail(function() {
			console.log('all bad')
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status)
			}
		);
}

function changeLunch(lunchId) {
	$('.modal').modal('close')
	$.ajax({
		type: "POST",
		url: "/nutriplan/changeLunch",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			//"id": breakfastid
			'lunchId': lunchId,
			'patient': {
				'patientId': document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function() {
			// Por ejemplo removemos la imagen "cargando..."
			console.log("allgood");
			M.toast({ html: '<b>Actualizando Plato...</b>', classes: 'rounded top green darken-1', completeCallback: function() { window.location.reload() } })

		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		})
}

function searchAsnack(asnackId) {
	$.ajax({
		type: "POST",
		url: "/nutriplan/getAsnack",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": asnackId
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function(response) {
			document.getElementById('modal1').classList.add('modal');
			var content = '<div class="modal-content" style="padding: 15px;">' +
				'<div class="row m-0">' +
				'<h6 style="padding-left: 0px; font-weight:600;">Edicion del plato</h6>' +
				'</div>';
			console.log(response.id)
			for (var i = 0; i < response.asVariant.length; i++) {
				content += '<div class="row" style="margin:0px; margin-bottom:7px;">' +
					'<div class="col col-12 l9 m9 s12" style="padding-left: 0px;">' +
					'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: ' + (i + 1) + '</p>' +
					'<span style="font-size: 12px;">' + response.asVariant[i].description + '</span>' +
					'</div>' +
					'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
					'<form id="theForm1" action="/nutriplan/changeLunch" method="post">' +
					'<a id="' + response.id + '|' + response.asVariant[i].id + '"' +
					'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"' +
					'style="font-size: 12px;" href="javascript:{}" onclick="changeAsnack(this.id)">Seleccionar</a>' +
					'</form>' +
					'</div>' +
					'</div>';
			}

			content += '</div>' +
				'<div class="modal-footer">' +
				'<a href="/home" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>' +
				'</div>';

			$('.modal').append(content)
			var elems = document.getElementById('modal1');
			var instances = M.Modal.init(elems, {
				dismissible: false
			});
			instances.open();
		})
		.fail(function() {
			console.log('all bad')
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status)
			}
		);
}

function changeAsnack(asnackId) {
	$('.modal').modal('close')
	$.ajax({
		type: "POST",
		url: "/nutriplan/changeAsnack",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			'asnackId': asnackId,
			'patient': {
				'patientId': document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function() {
			console.log("allgood");
			M.toast({ html: '<b>Actualizando Plato...</b>', classes: 'rounded top green darken-1', completeCallback: function() { window.location.reload() } })
		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		})
}



function searchPdsnack(pdsnackId) {
	$.ajax({
		type: "POST",
		url: "/nutriplan/getPdsnack",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": pdsnackId
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function(response) {
			document.getElementById('modal1').classList.add('modal');
			var content = '<div class="modal-content" style="padding: 15px;">' +
				'<div class="row m-0">' +
				'<h6 style="padding-left: 0px; font-weight:600;">Edicion del plato</h6>' +
				'</div>';
			console.log(response.id)
			for (var i = 0; i < response.pdsVariant.length; i++) {
				content += '<div class="row" style="margin:0px; margin-bottom:7px;">' +
					'<div class="col col-12 l9 m9 s12" style="padding-left: 0px;">' +
					'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: ' + (i + 1) + '</p>' +
					'<span style="font-size: 12px;">' + response.pdsVariant[i].description + '</span>' +
					'</div>' +
					'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
					'<form id="theForm1" action="/nutriplan/changeLunch" method="post">' +
					'<a id="' + response.id + '|' + response.pdsVariant[i].id + '"' +
					'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"' +
					'style="font-size: 12px;" href="javascript:{}" onclick="changePdsnack(this.id)">Seleccionar</a>' +
					'</form>' +
					'</div>' +
					'</div>';
			}

			content += '</div>' +
				'<div class="modal-footer">' +
				'<a href="/home" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>' +
				'</div>';

			$('.modal').append(content)
			var elems = document.getElementById('modal1');
			var instances = M.Modal.init(elems, {
				dismissible: false
			});
			instances.open();
		})
		.fail(function() {
			console.log('all bad')
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status)
			}
		);
}

function changePdsnack(pdsnackId) {
	$('.modal').modal('close')
	$.ajax({
		type: "POST",
		url: "/nutriplan/changePdsnack",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			'pdsnackId': pdsnackId,
			'patient': {
				'patientId': document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function() {
			console.log("allgood");
			M.toast({ html: '<b>Actualizando Plato...</b>', classes: 'rounded top green darken-1', completeCallback: function() { window.location.reload() } })
		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		})
}

function searchDinner(dinnerId) {
	$.ajax({
		type: "POST",
		url: "/nutriplan/getDinner",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": dinnerId
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function(response) {
			document.getElementById('modal1').classList.add('modal');
			var content = '<div class="modal-content" style="padding: 15px;">' +
				'<div class="row m-0">' +
				'<h6 style="padding-left: 0px; font-weight:600;">Edicion del plato</h6>' +
				'</div>';
			console.log(response.id)
			for (var i = 0; i < response.dVariant.length; i++) {
				content += '<div class="row" style="margin:0px; margin-bottom:7px;">' +
					'<div class="col col-12 l9 m9 s12" style="padding-left: 0px;">' +
					'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: ' + (i + 1) + '</p>' +
					'<span style="font-size: 12px;">' + response.dVariant[i].description + '</span>' +
					'</div>' +
					'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
					'<form id="theForm1" action="/nutriplan/changeLunch" method="post">' +
					'<a id="' + response.id + '|' + response.dVariant[i].id + '"' +
					'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"' +
					'style="font-size: 12px;" href="javascript:{}" onclick="changeDinner(this.id)">Seleccionar</a>' +
					'</form>' +
					'</div>' +
					'</div>';
			}

			content += '</div>' +
				'<div class="modal-footer">' +
				'<a href="/home" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>' +
				'</div>';

			$('.modal').append(content)
			var elems = document.getElementById('modal1');
			var instances = M.Modal.init(elems, {
				dismissible: false
			});
			instances.open();
		})
		.fail(function() {
			console.log('all bad')
		})
		.always(
			function(jqXHR) {
				console.log(jqXHR.status)
			}
		);
}

function changeDinner(dinnerId) {
	$('.modal').modal('close')
	$.ajax({
		type: "POST",
		url: "/nutriplan/changeDinner",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			'dinnerId': dinnerId,
			'patient': {
				'patientId': document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
	})
		.done(function() {
			console.log("allgood");
			M.toast({ html: '<b>Actualizando Plato...</b>', classes: 'rounded top green darken-1', completeCallback: function() { window.location.reload() } })
		})
		.fail(function() {
			// Manejar errores
			console.log("all bad");
		})
		.always(function(jqXHR) {
			console.log(jqXHR.status);
		})
}



function animate(elems) {
	elems.classList.add('animate__animated', 'animate__fadeIn')
}
function unHide(elems) {
	elems.classList.remove('hide')
}
function unAnimate(elems) {
	elems.classList.remove('animate__animated', 'animate__fadeIn')
	document.getElementById('warn').classList.add('animate__animated', 'animate__fadeIn')
}


window.addEventListener('load', () => {

	setTimeout(carga, 1000);
	function carga() {
		var elems = document.querySelectorAll('.hide');
		document.getElementById('preloader').classList.add('hide')
		elems.forEach(animate)
		elems.forEach(unHide)
	}
	
	setTimeout(carga2, 2000);
	function carga2() {
		var elems = document.querySelectorAll('.animate__animated')
		elems.forEach(unAnimate)
	}
	
})
