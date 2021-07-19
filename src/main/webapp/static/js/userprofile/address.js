var requestType;
var requestUrl = 'http://localhost:8080/IPolyclinic-1.0.0/api/user/address';

jQuery(function($){

    $(document).ready(function() {
        console.log( "document loaded" );
        $.getJSON(requestUrl, function(data) {
            if ($.isEmptyObject(data) || data.status === 204){
                toogleView(false)
            } else {
                toogleView(true)
            }

            console.log("requestType = " + requestType);
        }).fail(function() {
            console.log( "fail to request data on init" );
        })
    });


    function toogleView(showData){
        if (showData){
            $('#tap-address-data').removeClass("collapse");
            $('#div-address-add-btn').addClass("collapse");
            $('#form-address-save-btn').text("Сохранить");
            requestType = "PUT";
        } else{
            $('#tap-address-data').addClass("collapse");
            $('#div-address-add-btn').removeClass("collapse");
            $('#form-address-save-btn').text("Добавить");
            requestType = "POST";
        }
    }


    function updateAddressData(){
        if (CONSOLE_DEBUG_ON) console.log("updateAddressData -> Request to update data");
        $.getJSON('http://localhost:8080/IPolyclinic-1.0.0/api/user/address', function(data) {
            if (CONSOLE_DEBUG_ON) console.log("updateAddressData result ->" + data);
            if(data.country_name == null){
                toogleView(false);
            } else {
                /* $('#country_name').text(data.country_name);
                $('#city').text(data.city);
                $('#street').text(data.street);
                $('#home_no').text(data.home_no);
                $('#corp_no').text(data.corp_no);
                $('#flat_no').text(data.flat_no);*/
                toogleView(true);
                $.each(data, function(key, val) {
                    $('#' + key).text(val);
                });
            }

        }).fail(function() {
            console.log( "updateAddressData -> error" );
        })
    }


//Add new address
    $(document).on('click', '#form-address-save-btn', function(e){
        e.preventDefault();
        var form_data=JSON.stringify($("#form-address").serializeObject());

        console.log(form_data);

        $.ajax({
            url: requestUrl,
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


    $(document).on('click', '#form-address-delete-btn', function(e){
        e.preventDefault();

        $.ajax({
            url: 'http://localhost:8080/IPolyclinic-1.0.0/api/user/address',
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            data : "",
            success : function(result) {
                toogleView(false);
            },
            error: function(xhr, resp, text) {
                alert("Ошибка. Попробуйте снова!")
                console.log(xhr, resp, text);
            }
        });

        return false;
    });

});
