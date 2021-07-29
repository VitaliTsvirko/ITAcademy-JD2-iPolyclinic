var requestType;
var requestAddressUrl = BASE_URL + '/api/user/address';

jQuery(function($){

    $(document).ready(function() {
        updateAddressData();
    });

    function updateAddressData(){
        if (CONSOLE_DEBUG_ON) console.log("updateAddressData -> Request to update data");
        $.getJSON(requestAddressUrl, function(data, textStatus, jqXHR) {
            if (CONSOLE_DEBUG_ON) console.log("updateAddressData result ->" + data);
            if ($.isEmptyObject(data) || jqXHR.status === 204){
                //address is null
                $('#tap-address-data').addClass("collapse");
                $('#div-address-add-btn').removeClass("collapse");
                $('#form-address-save-btn').text("Добавить");
                requestType = "POST";
            } else {
                $('#tap-address-data').removeClass("collapse");
                $('#div-address-add-btn').addClass("collapse");
                $('#form-address-save-btn').text("Сохранить");
                requestType = "PUT";

                //store data to page
                $.each(data, function(key, val) {
                    $('#' + key).text(val);
                });
            }

        }).fail(function() {
            console.log( "updateAddressData -> error" );
        })
    }


    //Button save
    $(document).on('click', '#form-address-save-btn', function(e){
        e.preventDefault();
        var form_data=JSON.stringify($("#form-address").serializeObject());

        console.log(form_data);

        $.ajax({
            url: requestAddressUrl,
            type : requestType,
            contentType : 'application/json; charset=utf-8',
            data : form_data,
            success : function(result) {
                updateAddressData();
            },
            error: function(xhr, resp, text) {
                alert("Ошибка. Попробуйте снова!")
                console.log(xhr, resp, text);
            }
        });
        return false;
    });


    //Button delete
    $(document).on('click', '#form-address-delete-btn', function(e){
        e.preventDefault();

        $.ajax({
            url: requestAddressUrl,
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            data : "",
            success : function(result) {
                updateAddressData();
            },
            error: function(xhr, resp, text) {
                alert("Ошибка. Попробуйте снова!")
                console.log(xhr, resp, text);
            }
        });
        return false;
    });

});
