



jQuery(function ($) {
    $(document).ready(function() {
        updateUserHealthBasicMetric();
    });
});


function updateUserHealthBasicMetric(){
    jQuery(function ($) {

        let id = $("#user_id").val();

        $.getJSON(BASE_URL + '/api/user/' + id + '/healthmetrics', function (result, textStatus, jqXHR) {
            if (!$.isEmptyObject(result)) {
                //store data to page
                $.each(result, function(key, val) {
                    $('#' + key).text(val);
                });

                // jQuery counterUp
                $('[data-toggle="counterUp"]').counterUp({
                    delay: 15,
                    time: 1500
                });
            }

        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}