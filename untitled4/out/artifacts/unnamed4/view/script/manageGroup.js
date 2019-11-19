$(document).on("click", "#delete", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletGroup?groupNumber=" + $('#groupNumber').val(),
        success: function(data){
            if (data.error) {
                $("#message").text("Error");
            }
            else{
                $("#message").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#message").text(status + error);
        },
        type : "delete"
    });
});

$(document).on("click", "#add", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletGroup?groupNumber=" + $('#groupNumber').val(),
        success: function(data){
            if (data.error) {
                $("#message").text("Error");
            }
            else{
                $("#message").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#message").text(status);
        },
        type : "POST"
    });
});