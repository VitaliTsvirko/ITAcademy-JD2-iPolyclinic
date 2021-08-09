//https://only-to-top.ru/blog/programming/2019-11-11-jquery-ajax-json-php.html

const AlertsTypes = {SUCCESS : "alert-success",  WARNING : "alert-warning", ERROR : "alert-danger"};

const BASE_URL = window.location.origin + "/IPolyclinic-1.0.0";

jQuery(function($){
});

// функция для создания значений формы в формате json
$.fn.serializeObject = function() {
    let o = {};
    let a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || null);
        } else {
            o[this.name] = this.value || null;
        }
    });
    return o;
};


function injectAlert(blockId, messageText, alertType){
    jQuery(function($){
        $('<div class="alert ' + alertType + ' alert-dismissible fade show" role="alert">' + messageText +
            '<button type="button" class="position-absolute position-sm-relative m-2 m-sm-0 top-0 end-0 btn btn-icon ms-sm-auto" data-bs-dismiss="alert" aria-label="Close"><i class="bi bi-x fs-2"></i></button></div>').appendTo('#' + blockId);
    });
}