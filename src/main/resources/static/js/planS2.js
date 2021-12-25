// M.AutoInit();
var cards = document.querySelectorAll('.card'),
	theForm = document.getElementById('theForm'),
	previousCardId = String;
	
//var instance = M.Modal.getInstance('modal1');


function deselectThis(cards) {
	cards.classList.remove('zdisabled', 'zactive')
}

function selectOther(cards) {
	cards.classList.add('zdisabled')
}

function selectThis() {
	if (this.id == previousCardId) {
		cards.forEach(deselectThis)
		previousCardId = null
		document.getElementById('selectedCard').value = previousCardId
	} else {
		cards.forEach(selectOther)
		this.classList.remove('zdisabled')
		this.classList.add('zactive')
		previousCardId = this.id
		document.getElementById('selectedCard').value = previousCardId
	}
}

cards.forEach(function(cards) {
	cards.addEventListener('click', selectThis)
})

function openModal(){
    $('#modal1').modal();
    $('#modal1').modal('open'); 
 };

$(document).ready(function(){
    $('#modal1').modal();
    $('#modal1').modal('open'); 
 });


$('#theForm').submit(function(e) {
	e.preventDefault();

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























