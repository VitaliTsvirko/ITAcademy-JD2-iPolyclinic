let requestTypeAddress;
const requestAddressUrl = BASE_URL + '/api/userprofile/';

function saveAddressData(id) {
    jQuery(function ($) {
        var form_data = JSON.stringify($("#form-address").serializeObject());

        if ($("#city").text() === "") {
            requestTypeAddress = 'POST';
        } else {
            requestTypeAddress = 'PUT';
        }

        $.ajax({
            url: requestAddressUrl + id + '/address',
            type: requestTypeAddress,
            contentType: 'application/json; charset=utf-8',
            data: form_data,
            success: function (result) {
                if (!$.isEmptyObject(result)){
                    //store data to page
                    $.each(result, function(key, val) {
                        $('#' + key).text(val);
                    });

                    //hide buttons
                    $('#tap-address-data').removeClass("collapse");
                    $('#div-address-add-btn').addClass("collapse");

                    injectAlert("address-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                }
                //readBasicUserData(id);
            },
            error: function (xhr, resp, text) {
                injectAlert("address-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}

function deleteAddress(id) {
    jQuery(function ($) {
        $.ajax({
            url: requestAddressUrl + id + '/address',
            type : 'DELETE',
            contentType : 'application/json; charset=utf-8',
            success : function(result) {
                //clear page data
                $('#country_name').text("");
                $('#city').text("");
                $('#street').text("");
                $('#home_no').text("");
                $('#corp_no').text("");
                $('#flat_no').text("");

                //clear form
                $('#form-address input').val('');
                $('#form-address select').val('');

                //hide buttons
                $('#tap-address-data').addClass("collapse");
                $('#div-address-add-btn').removeClass("collapse");

                //update user basic info
                updateBasicUserData(id);

                injectAlert("address-tab-alert", "Данные успешно удалены", AlertsTypes.SUCCESS);
            },
            error: function(xhr, resp, text) {
                injectAlert("address-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}
