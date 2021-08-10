const requestMetricsUrl = BASE_URL + '/api/user/';

const analysisTextStyles = new Map ([
    ["LLOW", "text-danger"],
    ["LOW", "text-warning"],
    ["OK", "text-success"],
    ["HIGH", "text-warning"],
    ["HHIGH", "text-danger"],
    ["UNDEFINED", "text-muted"],
    ["NOT_APPLICABLE", "text-muted"]
]);

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
                    alert('Данные успешно сохранены');

                    //clear form
                    $('#form-user-metrics')[0].reset();

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



function updateUserHealthBasicMetric(data){
    jQuery(function ($) {
        if (!$.isEmptyObject(data)) {
            //store data to page
            $.each(data, function(index, item) {
                $('#' + item.type + "_value").text(Math.round(item.value * 10)/10);
                $('#' + item.type + "_timestamp").text(item.timestamp.split(" ")[0]);
                $('#' + item.type + "_analysis_text").text(item.comment);
                $('#' + item.type + "_analysis_text").addClass(analysisTextStyles.get(item.status));
            });

            // jQuery counterUp
            $('[data-toggle="counterUp"]').counterUp({
                delay: 15,
                time: 1500
            });
        }
    });

}