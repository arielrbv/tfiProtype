M.AutoInit();
var nPass = document.getElementById('newPass'),
    cnPass = document.getElementById('confirmPass'),
    newPassMsg = document.getElementById('newPassMsg'),
    confirmPassMsg = document.getElementById('confirmPassMsg'),
    validationMsg = document.getElementById('validationMsg');


function openModal() {
    $('#modal1').modal()
    $('#modal1').addClass('zopen')
    $('#modal1').modal('open')
};

function updatePassword() {
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

function validateNewPass() {
    if (nPass.value != cnPass.value) {
        newPassMsg.classList.add('hide')
        confirmPassMsg.classList.add('hide')
        nPass.classList.add('invalid')
        cnPass.classList.add('invalid')
        validationMsg.classList.remove('hide')
    } else {
        updatePassword();
    }
}

$('.input-field').on('change', function() {
    validationMsg.classList.add('hide')
    nPass.classList.remove('invalid')
    cnPass.classList.remove('invalid')
})