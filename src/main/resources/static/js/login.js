M.AutoInit();


document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.carousel');
    var instances = M.Carousel.init(elems, {
        duration: 200
    });
});
autoplay();


function autoplay() {
    $('.carousel').carousel('next');
    setTimeout(autoplay, 5000);
}


$('#pass').on('change', function() {
    document.getElementById('validationMsg').classList.add('hide')
});

$('#email').on('change', function() {
    document.getElementById('validationMsg').classList.add('hide')
});