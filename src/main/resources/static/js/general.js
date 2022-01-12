M.AutoInit();

var elem = document.querySelector('.collapsible.expandable');
var instance = M.Collapsible.init(elem, {
    accordion: false
});



function createPopUP() {
    document.getElementById('modal1').classList.add('modal');

    var cont = '<div class="modal-content" style="padding: 10px;">' +
        '<div class="row">' +
        '<h5 style="padding-left: 0px;">Edicion del plato</h5>' +
        '</div>' +
        '<div class="row">' +
        '<div class="col l9 m9 s12" style="padding-left: 0px;">' +
        '<span style="font-size: 12px;">la opcion de menu</span>' +
        ' </div>' +
        '<div class="col l3 m3 s12 right" style="padding: 0px;">' +
        '<a id="##" class="waves-effect waves-light btn-small right" style="font-size: 12px;">Seleccionar</a>' +
        ' </div>' +
        '</div>' +
        '</div>';
    M.AutoInit();

    document.getElementById("modal1").style.height = document.getElementById('modal1').offsetHeight;

    $('.modal').append(cont)
    $('.modal').modal('open')


}

function animate(elems) {
	elems.classList.add('animate__animated', 'animate__fadeIn')
}
function unHide(elems) {
	elems.classList.remove('hide')
}
function unAnimate(elems) {
	elems.classList.remove('animate__animated', 'animate__fadeIn')
}


window.addEventListener('load', () => {

	setTimeout(carga, 1000);
	function carga() {
		var elems = document.querySelectorAll('.hide');
		document.getElementById('preloader').classList.add('hide')
		elems.forEach(animate)
		elems.forEach(unHide)
	}
	
	setTimeout(carga2, 6000);
	function carga2() {
		var elems = document.querySelectorAll('.animate__animated')
		elems.forEach(unAnimate)
	}
	
})


