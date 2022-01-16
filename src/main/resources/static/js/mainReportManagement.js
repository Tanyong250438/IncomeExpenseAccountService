/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let data = {};
let dataSet;
let table;
let start;
let length;
let updateAppID;


$(document).ready(function () {
    search();
    disableAdd();
    disableUpdate();

});

function disableAdd() {
    $("#addAPIKey,#addAPISecret,#addAppName,#addAppStatus").keyup(function () {
        $('#submitAdd').prop('disabled',
                $('#addAPIKey').val() &&
                $('#addAPISecret').val() &&
                $('#addAppName').val() &&
                $('#addAppStatus').val()
                ? false : true);
    });
}
function disableUpdate() {
    $('#alertErrorUpdate').hide();
    $('#alertErrorDelete').hide();
    $('#submitUpdate').prop('disabled', false);
    $("#updateAPIKey,#updateAPISecret,#updateAppStatus").keyup(function () {
        $('#submitUpdate').prop('disabled',
                $('#updateAPIKey').val() &&
                $('#updateAPISecret').val() &&
                $('#updateAppStatus').val() &&
                updateAppID
                ? false : true);
    });
}
function clearData() {
    $('#alertErrorAdd').hide();
    $('#submitAdd').prop('disabled', true);
    $('#addAPIKey').val("");
    $('#addAPISecret').val("");
    $('#addAppName').val("");
    $('#addAppStatus').val("A");
}



function search() {

    $.fn.dataTable.ext.errMode = 'none';
    table = $('#dataTable').DataTable({
        data: dataSet,
        searching: false,
        bLengthChange: false,
        scrollX: true,
        lengthChange: false,
        pageLength: 10,
        processing: true,
        serverSide: true,
        ordering: false,
        ajax: {
            url: "getUserDailyReport",
            type: "POST",
            contentType: "application/json",
            data: function (d) {

                start = d.start;
                length = d.length;
                let object = {
                    "start": start,
                    "length": length
                };
                return JSON.stringify(object);
            },
            dataSrc: function (response) {
                return response.result ? response.result : [];
            }

        },

        columns: [

            {data: 'appName', title: "App Name"},
            {data: 'appAuthKey', title: "API Key"},
            {data: 'appSecretKey', title: "API Secret"},
            {title: "Update"},
            {title: "Delete"}

        ]
        , columnDefs: [

            {
                width: 120,
                targets: 0,
                render: function (data) {
                    return data ? data : '-';
                }
            }, {
                width: 120,
                targets: 1,
                render: function (data) {
                    return data ? data : '-';
                }
            }, {
                width: 120,
                targets: 2,
                render: function (data) {
                    return data ? data : '-';
                }
            },
            {
                width: "8%",
                targets: 3,
                render: function (data) {
                    return `<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#edit'>Update</button>`;
                }
            },
            {
                width: "8%",
                targets: 4,
                render: function (data) {
                    return "<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#delete'>DELETE</button>";
                }
            }

        ], language: {
            emptyTable: 'No result found.'
        }, fnDrawCallback: function (settings, json) {
//            nameClick();
        }
    });

    clearDataTable();

    $('#dataTable tbody').on('click', 'tr', function () {
        const  dataontable = table.row(this).data();
        updateAppID = dataontable.appID;
        $('#updateAPIKey').val(dataontable.appAuthKey);
        $('#updateAPISecret').val(dataontable.appSecretKey);
        $('#updateAppStatus').val(dataontable.appStatus);
        $('#deleteAppName').text(`Are you sure to delete ${dataontable.appName} ?`);
    });
}
function clearDataTable() {
    table.clear().draw();
}

function update() {
    let object = {
        "action": "update",
        "appID": updateAppID,
        "appAuthKey": $('#updateAPIKey').val(),
        "appSecretKey": $('#updateAPISecret').val(),
        "appStatus": $('#updateAppStatus').val()
    };
    updateAppChennel(object);

}


function deleteAppChannel() {

    let object = {
        "action": "delete",
        "appID": updateAppID
    };
    updateAppChennel(object);

}

function add() {
    let object = {
        "action": "add",
        "appName": $('#addAppName').val(),
        "appID": updateAppID,
        "appAuthKey": $('#addAPIKey').val(),
        "appSecretKey": $('#addAPISecret').val(),
        "appStatus": $('#addAppStatus').val()
    };
    updateAppChennel(object);

}


function updateAppChennel(request) {

    $.ajax({
        async: false,
        url: "updateAppChannel",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(request),
        success: function (response) {

            switch (response.status) {
                case "success":
                    haddleSuccess(request, response);
                    break;
                case "fail":
                    haddleError(request, response);
                    break;
                default:
                    haddleError(request, response);
                    break;
            }

            search();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown.toString(), textStatus.toString(), jqXHR.toString());
//            $('#updateMobileAuthFail').show();
        }
    });

}
function haddleSuccess(request, response) {
    switch (request.action) {
        case "add":
            $('#add').modal('hide');
            $('#alertErrorAdd').hide();
            break;
        case "update":
            $('#edit').modal('hide');
            $('#alertErrorUpdate').hide();

            break;
        case "delete":
            $('#delete').modal('hide');
            $('#alertErrorDelete').hide();
            break;
        default:
            break;
    }

}

function haddleError(request, response) {
    let alert;
    switch (request.action) {
        case "add":
            alert = $('#alertErrorAdd');
            break;
        case "update":
            alert = $('#alertErrorUpdate');
            break;
        case "delete":
            alert = $('#alertErrorDelete');
            break;
        default:
            break;
    }
    alert.text(response.message);
    alert.show();
}




