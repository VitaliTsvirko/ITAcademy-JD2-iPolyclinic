var requestType;
var requestUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/manager/user/';

function loadUserProfileData(id) {
        updateBasicUserData(id);
        updateAddressData(id);
        updatePassportData(id);
}

function updateBasicUserData(id) {
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

function updateAddressData(id){
    jQuery(function($){
        $.getJSON(requestUrl + id + '/address', function(data, textStatus, jqXHR) {
            if (!$.isEmptyObject(data)){
                //store data to page
                $.each(data, function(key, val) {
                    $('#' + key).val(val);
                });
            }

        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных адреса пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}

function updatePassportData(id){
    jQuery(function ($) {
    $.getJSON(requestUrl + id + '/passport', function(data, textStatus, jqXHR) {
            if (!$.isEmptyObject(data)) {
                //store data to page
                $.each(data, function (key, val) {
                    $('#' + key).val(val);
                });
            }
    }).fail(function(jqxhr, textStatus, error) {
        alert( "Ошибка при получении пасспортных данных пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
    })
    });
}





