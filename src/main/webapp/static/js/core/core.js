//https://only-to-top.ru/blog/programming/2019-11-11-jquery-ajax-json-php.html
var CONSOLE_DEBUG_ON = true;

jQuery(function($){
});
 
// функция для создания значений формы в формате json 
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
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