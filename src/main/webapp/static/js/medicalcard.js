const requestUrl = BASE_URL + '/api/mcard/';

jQuery(function($){
    $(document).ready(function(){

        $.getJSON(requestUrl + id + '/user', function(data, textStatus, jqXHR) {
            if (jqXHR.status === 200){
                if (!$.isEmptyObject(data)){
                    //store data to page
                    $.each(data, function(key, val) {
                        $('#patient_' + key).text(val);
                    });

                    $("#patient_age_date_of_birth").text(data.age + " лет, " + data.date_of_birth);

                }
            } else {
            }
        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных адреса пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    })
});