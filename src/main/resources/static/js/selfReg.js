M.AutoInit();

var overlay = document.getElementById('overlay'),
    popup = document.getElementById('popup'),
    btnCerrarPopup = document.getElementById('btn-cerrar-popup'),
    overlay2 = document.getElementById('overlay2'),
    spiner = document.getElementById('spinner');





btnCerrarPopup.addEventListener('click', function(e) {
    e.preventDefault();
    overlay.classList.remove('active');
    popup.classList.remove('active');
});


document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems, {
        autoClose: true,
        yearRange: [new Date().getFullYear()-90,new Date().getFullYear()-18],
        format: "dd/mm/yyyy",
        i18n: {
            months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Dic"],
            weekdays: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
            weekdaysShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
            weekdaysAbbrev: ["D", "L", "M", "M", "J", "V", "S"]
        }
    });
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

    var dateString = document.getElementById('dateAux').value;
    var dateParts = dateString.split("/");
    var dateObject = new Date(dateParts[2], dateParts[1], +dateParts[0]);

    $("#formPatient").submit(function(event) {
        event.preventDefault();
    })


    //"birthdate": new Date(document.getElementById("dateAux").value).toISOString(),
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
                "birthdate": new Date(dateParts[2], dateParts[1], +dateParts[0]).toISOString(),
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