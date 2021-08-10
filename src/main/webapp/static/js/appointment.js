const requestUrl = BASE_URL + '/api/appointment/';

jQuery(function($){
    $(document).ready(function(){
        // Listen with the jQuery to the tabs click:
    })
});

function saveAppointmentData() {
    jQuery(function ($) {
        let id = $('#id').val();

        let data = JSON.stringify({
            id: id,
            type: $('input[name=appointmentType]:checked', '#form-appointment').val(),
            complaints: $('#complaints').val(),
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
                alert('Данные успешно сохранены')
            },
            error: function (xhr, resp, text) {
                alert('Ошибка')
            }
        });
        return false;
    });
}


function addMetrics() {
    jQuery(function ($) {
        let id = $('#user_id').val();

        let metrics = [];
        $.each($('#form-user-metrics :input').serializeArray(), function(i, field) {
            if (!$.isEmptyObject(field.value)){
                let metric = {
                    'type' : field.name,
                    'value' : field.value
                }
                metrics.push(metric);
            }
        });

        let form_data = JSON.stringify(metrics);

        if (metrics.length !== 0) {
            $.ajax({
                url: BASE_URL + '/api/user/' + id + '/healthmetrics',
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: form_data,
                success: function (result, textStatus, jqXHR) {
                    //injectAlert("address-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                    //readBasicUserData(id);
                    updateUserHealthBasicMetric(result);
                    alert('Данные успешно сохранены')
                },
                error: function (xhr, resp, text) {
                    //injectAlert("address-tab-alert", "Ошибка!" + xhr.responseJSON.message, AlertsTypes.ERROR);
                    alert('Ошибка')
                }
            });
        }
        return false;
    });
}



jQuery(function ($) {
    $(document).ready(function() {
        readUserHealthBasicMetric();
    });
});


function readUserHealthBasicMetric(){
    jQuery(function ($) {

        let id = $("#user_id").val();

        $.getJSON(BASE_URL + '/api/user/' + id + '/healthmetrics', function (result, textStatus, jqXHR) {
            updateUserHealthBasicMetric(result);
        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}


function updateUserHealthBasicMetric(data){
    jQuery(function ($) {
        if (!$.isEmptyObject(data)) {
            //store data to page
            $.each(data, function(index, item) {
                $('#' + item.type + "_value").text(Math.round(item.value * 10)/10);
                $('#' + item.type + "_timestamp").text(item.timestamp.split(" ")[0]);
            });

            // jQuery counterUp
            $('[data-toggle="counterUp"]').counterUp({
                delay: 15,
                time: 1500
            });
        }
    });

}





