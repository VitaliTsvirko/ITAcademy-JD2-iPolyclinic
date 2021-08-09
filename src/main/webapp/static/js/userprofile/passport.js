let requestTypePassport;
const requestPassportUrl = BASE_URL + '/api/userprofile/';

function savePassportData(id) {
    jQuery(function ($) {
        let form_data = JSON.stringify($("#form-passport").serializeObject());


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
                    $('#passport-add-btn').addClass("collapse");
                    $('#passport-edit-btn').removeClass("collapse");
                    $('#passport-delete-btn').removeClass("collapse");

                    $("#confirm-passport-btn").removeClass("collapse");

                    $("#user-status-badge").addClass("badge-warning");
                    $("#user-status-badge").removeClass("badge-success");
                    $("#user-status-badge").text("Не подтвержден");

                    //update user basic info
                    updateBasicUserData(id);

                    injectAlert("passport-tab-alert", "Данные успешно обновлены", AlertsTypes.SUCCESS);
                }
            },
            error: function (xhr, resp, text) {
                injectAlert("passport-tab-alert", "Ошибка. Проверьте, возможно не заполнено одно из обязательных полей!", AlertsTypes.ERROR);
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
                $('#passport-add-btn').removeClass("collapse");
                $('#passport-edit-btn').addClass("collapse");
                $('#passport-delete-btn').addClass("collapse");

                $("#user-status-badge").addClass("badge-warning");
                $("#user-status-badge").removeClass("badge-success");
                $("#user-status-badge").text("Не подтвержден");

                injectAlert("passport-tab-alert", "Данные успешно удалены", AlertsTypes.SUCCESS);
            },
            error: function(xhr, resp, text) {
                injectAlert("passport-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}



function confirmPassportData(id) {
    jQuery(function ($) {
        $.ajax({
            url: requestPassportUrl + id + '/passport/confirm',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: '{}',
            success: function (result) {
                if (!$.isEmptyObject(result)){
                    //update user basic info

                    //close alert
                    $("#userprofile-alert").alert('close')
                    //hide confirm button
                    $("#confirm-passport-btn").addClass("collapse");
                    $("#user-status-badge").removeClass("badge-warning");
                    $("#user-status-badge").addClass("badge-success");
                    $("#user-status-badge").text("Подтвержден");

                    updateBasicUserData(id);

                    injectAlert("passport-tab-alert", "Данные паспорта подтверждены. Аккаунт активирован.", AlertsTypes.SUCCESS);
                }
            },
            error: function (xhr, resp, text) {
                injectAlert("passport-tab-alert", "Ошибка. Попробуйте снова!", AlertsTypes.ERROR);
            }
        });
        return false;
    });
}
