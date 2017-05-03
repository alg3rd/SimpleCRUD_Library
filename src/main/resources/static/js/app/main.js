
$(document).ready(function(){
    // click on button submit
    $("#submit").on('click', function(){
        // send ajax
        $.ajax({
            url: 'api/user/', // url where to submit the request
            type : "POST", // type of action POST || GET
            dataType : 'json', // data type
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(objectifyForm($("#form").serializeArray())), // post data || get data
            success : function(result) {
                console.log(result);
            },
            error: function(xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        })
    });
});
function objectifyForm(formArray) {//serialize data function

    var returnArray = {};
    for (var i = 0; i < formArray.length; i++){
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}
function test(){}