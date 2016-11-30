$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    $("#id").val($.cookie("env-edit-id"));
    $("#id").attr("disabled", "disabled");

    var envDto = {
        id: $.cookie("env-edit-id")
    };

    var reqData = {
        reqId: "123456",
        data: envDto
    };
    var dataJson = JSON.stringify(reqData);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryEnv",
        data: dataJson,
        dataType: 'json',
        success: function (res) {
            $("#name").val(res.data.name);
            $("#code").val(res.data.code);
        }
    });

    $("#save").click(function () {
        var envDto = {
            id: $("#id").val(),
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
