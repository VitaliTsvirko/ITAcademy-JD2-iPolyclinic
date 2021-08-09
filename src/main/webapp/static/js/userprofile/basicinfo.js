const requestUrl = BASE_URL + '/api/userprofile/';

function updateBasicUserData(id) {
    jQuery(function ($) {
        $.getJSON(requestUrl + id + '/basic', function (data, textStatus, jqXHR) {
            if (!$.isEmptyObject(data)) {
                //store data to page
                $.each(data, function (key, val) {
                    $('#info-' + key).text(val);
                    $('#head-' + key).text(val);
                });
            }

        }).fail(function(jqxhr, textStatus, error) {
            alert( "Ошибка при получении данных пользователя.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
        })
    });
}