/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    $('#username,#password').keyup(function () {
        if ($('#username').val().length == 0 || $('#password').val().length == 0) {
            $('#submit').attr('disabled', true);
        } else {
            $('#submit').attr('disabled', false);
        }
    });

    check();
});

function check() {
    if ($('#username').val().length == 0 || $('#password').val().length == 0) {
        $('#submit').attr('disabled', true);
    } else {
        $('#submit').attr('disabled', false);
    }
}

