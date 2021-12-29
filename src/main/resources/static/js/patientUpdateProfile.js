M.AutoInit();

function openModal() {
    $('#modal1').modal()
    $('#modal1').addClass('zopen')
    $('#modal1').modal('open')
};

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems, {
        autoClose: true,
        format: "dd/mm/yyyy",
        yearRange: [new Date().getFullYear() - 50, new Date().getFullYear()],
        i18n: {
            months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Dic"],
            weekdays: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
            weekdaysShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
            weekdaysAbbrev: ["D", "L", "M", "M", "J", "V", "S"]
        }
    });
});


function updatePatient() {
    $("#theForm").submit(function(event) {
        event.preventDefault();
        var form = $(this);
        var url = form.attr('action');

        // DO POST
        $.ajax({
                type: "POST",
                url: url,
                data: form.serialize(),
				//beforeSend: openSpinner()
            })
            .done(function() {
                // Por ejemplo removemos la imagen "cargando..."
                console.log("allgood");
                openModal();
            })
            .fail(function() {
                // Manejar errores
                console.log("all bad");
            })
            .always(function(jqXHR) {
                console.log(jqXHR.status);
            })
    })
}