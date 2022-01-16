$(document).ready(function () {
    $('#textShow').show();
});

function hideAlert() {

    $('#saveUserProfileAlertFailed').hide();
    $('#saveUserProfileAlertIncorrectNameOrPwd').hide();
    $('#saveUserProfileAlertSuccess').hide();
    $('#saveUserProfileAlertsamePwd').hide();

    $('#editUserProfileAlertFailed').hide();
    $('#editUserProfileAlertNotExist').hide();
    $('#editUserProfileAlertSuccess').hide();

//    searchUserProfile();
}

function sendUpdate() {
    var userName;
    var oldPwd;
    var newPwd;
    hideAlert();
    userName = $("#username").val();
    oldPwd = $("#oldPassword").val();
    newPwd = $("#newPassword").val();

    $.ajax({
        async: false,
        url: "updateUserPwd",
        type: "POST",
        data: {userName: userName, oldPassword: oldPwd, newPassword: newPwd},
        success: function (data) {
            if (data.status === "SUCCESS") {
                $('#btn').prop('disabled', true);
                $('#saveUserProfileAlertSuccess').show();
                $('#textShow').hide();
            } else {
                $('#textShow').hide();
                document.getElementById('oldPassword').value = '';
                document.getElementById('newPassword').value = '';
                if (data.message === "samePwd") {
                    $('#saveUserProfileAlertsamePwd').show();
                } else if (data.message === "notFondUser") {
                    $('#saveUserProfileAlertIncorrectNameOrPwd').show();
                }

            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown.toString(), textStatus.toString(), jqXHR.toString());
            $('#saveUserProfileAlertFailed').show();
        }
    });

}