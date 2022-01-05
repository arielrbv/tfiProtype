M.AutoInit();


function searchBreackfast(breakfastid) {
	$.ajax({
		type: "POST",
		url: "/test/breakfast",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			"id": breakfastid
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
							'<p style="margin-bottom:0px; font-size:12px; font-weight:600;">Opcion: '+(i+1)+'</p>'+
							'<span style="font-size: 12px;">' + response.bVariant[i].description +'</span>' +
						'</div>' +
						'<div class="col l3 m3 s12 right" style="padding: 0px;">' +
							'<form id="theForm1" action="/test/changeBreakfast" method="post">'+
								'<a id="'+response.id+'|'+response.bVariant[i].id+'"'+ 
								'class="waves-effect waves-light btn-small zbtn2 right valign-wrapper"'+
								'style="font-size: 12px;" href="javascript:{}" onclick="changeBreakfast(this.id)">Seleccionar</a>' +
							'</form>'+
						'</div>' +
					'</div>';
			}
			
			content += '</div>'+
			'<div class="modal-footer">'+
				'<a href="/home" class="modal-close center waves-effect waves-green btn-flat">Cancelar</a>'+
			'</div>';
			
			$('.modal').append(content)
		    var elems =  document.getElementById('modal1');
		    var instances = M.Modal.init(elems,{
						dismissible:false
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

function changeBreakfast(breakfastid) {
	$('.modal').modal('close')
    $.ajax({
		type: "POST",
		url: "/test/changeBreakfast",
		accept: "*/*",
		contentType: "application/json",
		data: JSON.stringify({
			//"id": breakfastid
			'breakfastId':breakfastid,
			'patient':{
				'patientId':document.getElementById('jsPatientId').value
			}
		})//,
		//beforeSend: //console.log(breakfastid)
		})
            .done(function() {
                // Por ejemplo removemos la imagen "cargando..."
                console.log("allgood");
                M.toast({html: 'Actualizando Plato...', classes: 'rounded', completeCallback: function(){window.location.replace("/home")}})
                
            })
            .fail(function() {
                // Manejar errores
                console.log("all bad");
            })
            .always(function(jqXHR) {
                console.log(jqXHR.status);
            })
}



