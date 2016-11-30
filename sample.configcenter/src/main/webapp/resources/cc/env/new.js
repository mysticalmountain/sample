$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    var data = {
        reqId: "123456"
    };
    var dataJson = JSON.stringify(data);


    $("#save").click(function () {
        var envDto = {
            name: $("#name").val(),
            code: $("#code").val()
        };
        var reqData = {
            reqId: '123456',
            data: envDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/editEnv",
            data: dataJson,
            dataType: 'json',
            success: function (result) {
                $("#errorCode").html('');
                $("#errorMessage").html('');
                $("#errorCode").html(result.errorCode);
                $("#errorMessage").html(result.errorMsg);
                $("#dialog-message").dialog('open');
                return false;
            }
        });
    });

    $("#dialog-message").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                if ($("#errorCode").text() == 000000) {
                    $(this).dialog("close");
                } else {
                    $(this).dialog("close");
                }
            }
        }
    });
});
