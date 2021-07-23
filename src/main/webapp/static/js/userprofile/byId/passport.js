var requestType;
var requestPassportUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/user/passport';

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
