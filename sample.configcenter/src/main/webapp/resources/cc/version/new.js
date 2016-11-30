$(document).ready(function () {

    $.getScript("/resources/cc/common/menu.js");

    $("#a_projects").click(function () {
        location.href = 'list.js';
    });

    $("#linkVersions").click(function () {
        location.href = "list.js";
    });

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryProject",
        data: "{reqId:'123456'}",
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#projectId").append("<option value='" + item.id + "'>" + item.name + "</option>");
            });
        }
    });

    $("#save").click(function () {
        var data = {
            name: $("#name").val(),
            content: $("#content").val(),
            projectId: $("#projectId").val()
        };
        var reqData = {
            reqid: new Date().valueOf(),
            data: data
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/saveVersion",
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

    $('#modal_link').click(function () {
        $('#dialog-message').dialog('open');
        return false;
    });

    $("#dialog-message").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });

});
