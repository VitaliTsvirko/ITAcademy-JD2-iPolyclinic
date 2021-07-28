var requestTypeAddress;
var requestTypePassport;
var requestUrl = 'http://localhost:8081/IPolyclinic-1.0.0/api/appointment/';

jQuery(function($){
    $(document).ready(function(){
        // Listen with the jQuery to the tabs click:
    })
});

function injectAlert(blockId, messageText, alertType){
    jQuery(function($){
        $('<div class="alert ' + alertType + ' alert-dismissible fade show" role="alert">' + messageText +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>').appendTo('#' + blockId);
    });
}

function saveAppointmentData() {
    jQuery(function ($) {
        var id = $('#id').val();

        var data = JSON.stringify({
            id : $('#id').val(),
            type : $('input[name=type]:checked', '#form-appointment').val(),
            complaints : $('#complaints').val(),
            temperature : $('#temperature').val(),
            systolicBloodPressure : $('#systolicBloodPressure').val(),
            diastolicBloodPressure : $('#diastolicBloodPressure').val(),
            diagnosis_code : $('.js-diseases-search').val(),
            therapy : $('#therapy').val(),
            healthStatus : $('input[name=health_status]:checked', '#form-appointment').val()
        });

        var form_data = data;

        $.ajax({
            url: requestUrl + id,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: form_data,
            success: function (result) {
                //injectAlert("address-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                //readBasicUserData(id);
                alert('Данные успешно сохранены')
            },
            error: function (xhr, resp, text) {
                //injectAlert("address-tab-alert", "Ошибка!" + xhr.responseJSON.message, AlertsTypes.ERROR);
                alert('Ошибка')
            }
        });
        return false;
    });
}






