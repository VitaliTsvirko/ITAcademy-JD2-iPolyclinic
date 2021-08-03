var requestType;
var requestPassportUrl = BASE_URL + '/api/userprofile/';

function savePassportData(id) {
    jQuery(function ($) {
        var form_data = JSON.stringify($("#form-passport").serializeObject());

        if ($("#personal_no").text() === "") {
            requestTypePassport = 'POST';
        } else {
            requestTypePassport = 'PUT';
        }

        $.ajax({
            url: requestPassportUrl + id + '/passport',
            type: requestTypePassport,
            contentType: 'application/json; charset=utf-8',
            data: form_data,
            success: function (result) {
                if (!$.isEmptyObject(result)){
                    //store data to page
                    $.each(result, function(key, val) {
                        $('#' + key).text(val);
                    });

                    //hide buttons
                    $('#tap-passport-data').removeClass("collapse");
                    $('#div-passport-add-btn').addClass("collapse");

                    //update user basic info
                    updateBasicUserData(id);

                    injectAlert("passport-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                }
            },
            error: function (xhr, resp, text) {
                injectAlert("passport-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}

function deletePassport(id) {
    jQuery(function ($) {
        $.ajax({
            url: requestPassportUrl + id + '/passport',
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            success : function(result) {
                //clear page data
                $('#name').text("");
                $('#surname').text("");
                $('#patronymic').text("");
                $('#date_of_birth').text("");
                $('#place_of_birth').text("");
                $('#personal_no').text("");
                $('#passport_no').text("");
                $('#country_of_issue_code').text("");
                $('#issue_date').text("");
                $('#expiration_date').text("");

                //clear form
                $('#form-passport input').val('');
                $('#form-passport select').val('');

                //hide buttons
                $('#tap-passport-data').addClass("collapse");
                $('#div-passport-add-btn').removeClass("collapse");

                injectAlert("passport-tab-alert", "Данные успешно удалены", AlertsTypes.SUCCESS);
            },
            error: function(xhr, resp, text) {
                injectAlert("passport-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}



jQuery(function($){

    $(document).ready(function() {
        updatePassportData();
    });

    function updatePassportData(){
        if (CONSOLE_DEBUG_ON) console.log("updatePassportData -> Request to update data");
        $.getJSON(requestPassportUrl, function(data, textStatus, jqXHR) {
            if (CONSOLE_DEBUG_ON) console.log("updatePassportData result ->" + data);
            if ($.isEmptyObject(data) || jqXHR.status === 204){
                //address is null
                $('#tap-passport-data').addClass("collapse");
                $('#div-passport-add-btn').removeClass("collapse");
                $('#form-passport-save-btn').text("Добавить");
                requestType = "POST";
            } else {
                $('#tap-passport-data').removeClass("collapse");
                $('#div-passport-add-btn').addClass("collapse");
                $('#form-passport-save-btn').text("Сохранить");
                requestType = "PUT";

                //store data to page
                $.each(data, function(key, val) {
                    $('#' + key).text(val);
                });
            }

        }).fail(function() {
            console.log( "updatePassportData -> error" );
        })
    }


    //Button save
    $(document).on('click', '#form-passport-save-btn', function(e){
        e.preventDefault();
        var form_data=JSON.stringify($("#form-passport").serializeObject());

        console.log(form_data);

        $.ajax({
            url: requestPassportUrl,
            type : requestType,
            contentType : 'application/json; charset=utf-8',
            data : form_data,
            success : function(result) {
                updatePassportData();
            },
            error: function(xhr, resp, text) {
                alert("Ошибка. Попробуйте снова!")
                console.log(xhr, resp, text);
            }
        });
        return false;
    });


    //Button delete
    $(document).on('click', '#form-passport-delete-btn', function(e){
        e.preventDefault();

        $.ajax({
            url: requestPassportUrl,
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            data : "",
            success : function(result) {
                updatePassportData();
            },
            error: function(xhr, resp, text) {
                alert("Ошибка. Попробуйте снова!")
                console.log(xhr, resp, text);
            }
        });
        return false;
    });

});
