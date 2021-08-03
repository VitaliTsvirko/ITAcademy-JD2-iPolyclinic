const requestUrl = BASE_URL + '/api/appointment/';

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
        let id = $('#id').val();

        let data = JSON.stringify({
            id: id,
            type: $('input[name=appointmentType]:checked', '#form-appointment').val(),
            complaints: $('#complaints').val(),
            temperature: $('#temperature').val(),
            systolic_blood_pressure: $('#systolicBloodPressure').val(),
            diastolic_blood_pressure: $('#diastolicBloodPressure').val(),
            diagnosis_code: $('.js-diseases-search').val(),
            therapy: $('#therapy').val(),
            health_status: $('input[name=health_status]:checked', '#form-appointment').val()
        });

        let form_data = data;

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






