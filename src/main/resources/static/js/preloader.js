window.addEventListener('load', () => {

	setTimeout(carga, 1000);

	function carga() {
		console.log('pasoporaca')
		document.getElementById('ani1').classList.remove('hide')
		document.getElementById('ani2').classList.remove('hide')
		document.getElementById('preloader').classList.add('hide')
		document.getElementById('ani6').classList.remove('hide')
	}

	setTimeout(carga2, 6000);

	function carga2() {
		console.log('pasoporaca2')
		document.getElementById('ani1').classList.remove('animate__animated', 'animate__fadeIn')
		document.getElementById('ani3').classList.remove('animate__animated', 'animate__fadeIn')
		document.getElementById('ani4').classList.remove('animate__animated', 'animate__fadeIn')
		document.getElementById('ani5').classList.remove('animate__animated', 'animate__fadeIn')
	}
})