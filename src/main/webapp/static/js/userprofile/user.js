var requestType;
var requestUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/manager/user/';
var requestAddressUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/user/address';
var requestPassportUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/user/passport';

function loadUserProfileData(id) {
        updateBasicUserData(id);
        updateAddressData(id);
        updatePassportData(id);
}

function updateBasicUserData(id) {
    if (CONSOLE_DEBUG_ON) console.log("loadUserProfileData -> Request to update data");
    jQuery(function ($) {
        $.getJSON(requestUrl + id, function (data, textStatus, jqXHR) {
            if (CONSOLE_DEBUG_ON) console.log("loadUserProfileData result ->" + data);
            if (!$.isEmptyObject(data)) {
                //store data to page
                $.each(data, function (key, val) {
                    $('#info-' + key).text(val);
                });
            }

        }).fail(function () {
            console.log("loadUserProfileData -> error");
        });
    });
}

function updateAddressData(id){
    if (CONSOLE_DEBUG_ON) console.log("updateAddressData -> Request to update data");
    jQuery(function($){
        $.getJSON(requestUrl + id, function(data, textStatus, jqXHR) {
            if (CONSOLE_DEBUG_ON) console.log("updateAddressData result ->" + data);
            if (!$.isEmptyObject(data)){
                //store data to page
                $.each(data, function(key, val) {
                    $('#' + key).text(val);
                });
            }

        }).fail(function() {
            console.log( "updateAddressData -> error" );
        })
    });
}

function updatePassportData(id){
    if (CONSOLE_DEBUG_ON) console.log("updatePassportData -> Request to update data");
    $.getJSON(requestUrl + id, function(data, textStatus, jqXHR) {
        jQuery(function ($) {
            if (CONSOLE_DEBUG_ON) console.log("updatePassportData result ->" + data);
            if (!$.isEmptyObject(data)) {
                //store data to page
                $.each(data, function (key, val) {
                    $('#' + key).text(val);
                });
            }
        }).fail(function() {
            console.log( "updatePassportData -> error" );
        })
    });
}





