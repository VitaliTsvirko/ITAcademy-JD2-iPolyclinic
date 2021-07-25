var requestTypeAddress;
var requestTypePassport;
var requestUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/manager/user/';

const AlertsTypes = {SUCCESS : "alert-success",  WARNING : "alert-warning", ERROR : "alert-danger"};

function loadUserProfileData(id) {
        readBasicUserData(id);
        readAddressData(id);
        readPassportData(id);
}

jQuery(function($){
    $(document).ready(function(){
        // Listen with the jQuery to the tabs click:
        $('#userProfileModalTabsHeader a').click(function (link) {
            //remove all alerts
            $('#address-tab-alert').empty();
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
            if (!$.isEmptyObject(data)){
                //store data to page
                $.each(data, function(key, val) {
                    $('#' + key).val(val);
                });
                requestTypeAddress = 'PUT';
            } else {
                requestTypeAddress = 'POST';
            }
            //change buttons id
            $('#address-buttons button').each(function(){
                $(this).attr('id', id);
            });
        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных адреса пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}

function readPassportData(id){
    jQuery(function ($) {
    $.getJSON(requestUrl + id + '/passport', function(data, textStatus, jqXHR) {
        if (!$.isEmptyObject(data)){
            //store data to page
            $.each(data, function(key, val) {
                $('#' + key).val(val);
            });
            requestTypePassport = 'PUT';
        } else {
            requestTypePassport = 'POST';
        }
        //change buttons id
        $('#passport-buttons button').each(function(){
            $(this).attr('id', id);
        });
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
                alert("Ошибка. Попробуйте снова! .\nError detail:\nHTTP status " + xhr.status + "\n error: " + text)
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




