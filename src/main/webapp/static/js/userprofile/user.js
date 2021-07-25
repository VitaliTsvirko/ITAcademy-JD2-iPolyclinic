var requestTypeAddress;
var requestTypePassport;
var requestUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/manager/user/';

const AlertsTypes = {SUCCESS : "alert-success",  WARNING : "alert-warning", ERROR : "alert-danger"};

function loadUserProfileData(id) {
        readBasicUserData(id);
        readAddressData(id);
        readPassportData(id);

    jQuery(function($){
        //change buttons id
        $('#address-buttons button').each(function(){
            $(this).attr('id', id);
        });

        $('#passport-buttons button').each(function(){
            $(this).attr('id', id);
        });
    });
}

jQuery(function($){
    $(document).ready(function(){
        // Listen with the jQuery to the tabs click:
        $('#userProfileModalTabsHeader a').click(function (link) {
            //remove all alerts
            $('#address-tab-alert').empty();
            $('#passport-tab-alert').empty();
        })
    })
});

function injectAlert(blockId, messageText, alertType){
    jQuery(function($){
        $('<div class="alert ' + alertType + ' alert-dismissible fade show" role="alert">' + messageText +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>').appendTo('#' + blockId);
    });
}



function readBasicUserData(id) {
    jQuery(function ($) {
        $.getJSON(requestUrl + id + '/basic', function (data, textStatus, jqXHR) {
            if (!$.isEmptyObject(data)) {
                //store data to page
                $.each(data, function (key, val) {
                    $('#info-' + key).text(val);
                });
            }

        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}

function readAddressData(id){
    jQuery(function($){
        $.getJSON(requestUrl + id + '/address', function(data, textStatus, jqXHR) {
            if (jqXHR.status === 200){
                if (!$.isEmptyObject(data)){
                    //store data to page
                    $.each(data, function(key, val) {
                        $('#' + key).val(val);
                    });
                }
                requestTypeAddress = 'PUT';
            } else {
                requestTypeAddress = 'POST';
                //clear form
                $('#form-address input').val('');
                $('#form-address select').val('');
            }
        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных адреса пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}

function readPassportData(id){
    jQuery(function ($) {
    $.getJSON(requestUrl + id + '/passport', function(data, textStatus, jqXHR) {
        if (!$.isEmptyObject(data) && jqXHR.status === 200){
            //store data to page
            $.each(data, function(key, val) {
                $('#' + key).val(val);
            });
            requestTypePassport = 'PUT';
        } else {
            requestTypePassport = 'POST';
        }
    }).fail(function(jqxhr, textStatus, error) {
        alert( "Ошибка при получении пасспортных данных пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
    })
    });
}

function saveAddressData(id) {
    jQuery(function ($) {
        var form_data = JSON.stringify($("#form-address").serializeObject());
        $.ajax({
            url: requestUrl + id + '/address',
            type: requestTypeAddress,
            contentType: 'application/json; charset=utf-8',
            data: form_data,
            success: function (result) {
                injectAlert("address-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                readBasicUserData(id);
            },
            error: function (xhr, resp, text) {
                injectAlert("address-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}

function deleteAddress(id) {
    jQuery(function ($) {
        $.ajax({
            url: requestUrl + id + '/address',
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            success : function(result) {
                injectAlert("address-tab-alert", "Данные успешно удалены", AlertsTypes.SUCCESS);
                readAddressData(id);
            },
            error: function(xhr, resp, text) {
                injectAlert("address-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}


function savePassportData(id) {
    jQuery(function ($) {
        var form_data = JSON.stringify($("#form-passport").serializeObject());
        $.ajax({
            url: requestUrl + id + '/passport',
            type: requestTypePassport,
            contentType: 'application/json; charset=utf-8',
            data: form_data,
            success: function (result) {
                injectAlert("passport-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                readBasicUserData(id);
            },
            error: function (xhr, resp, text) {
                alert("Ошибка. Попробуйте снова! .\nError detail:\nHTTP status " + xhr.status + "\n error: " + text)
            }
        });
        return false;
    });
}

function deletePassport(id) {
    jQuery(function ($) {
        $.ajax({
            url: requestUrl + id + '/passport',
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            success : function(result) {
                injectAlert("address-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                readAddressData(id);
            },
            error: function(xhr, resp, text) {
                alert("Ошибка. Попробуйте снова! .\nError detail:\nHTTP status " + xhr.status + "\n error: " + text)
            }
        });
        return false;
    });
}







