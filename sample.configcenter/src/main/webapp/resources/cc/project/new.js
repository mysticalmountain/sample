$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    $("#a_projects").click(function () {
        location.href = 'list.js';
    });

    var reqData = {
        reqId: "123456"
    };
    var dataJson = JSON.stringify(reqData);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryEnv",
        data: dataJson,
        dataType: 'json',
        success: function (result) {
            $.each(result.data, function (i, item) {
                $("#envId").append("<option value='" + item.id + "'>" + item.name + "</option>");
            });
        }
    });

    reqData = {
        reqId: "123456"
    };
    dataJson = JSON.stringify(reqData);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryVersion",
        data: dataJson,
        dataType: 'json',
        success: function (result) {
            $.each(result.data, function (i, item) {
                $("#versionId").append("<option value='" + item.id + "'>" + item.name + "</option>");
            });
        }
    });

    $("#save").click(function () {
        var data = {
            reqid: '123456',
            name: $("#name").val(),
            content: $("#content").val()
        }
        var dataJson = JSON.stringify(data);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/editProject",
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

    $("#saveItem").click(function () {
        var kei = $("input[name='kei']");
        var val = $("input[name='val']");
        var content = $("input[name='content']");
        var rows = new Array();
        for (var i = 0; i < kei.size(); i++) {
            var row = {
                // projectId:
                // versionId:
                // envId:
                kei: $("input[name='kei']").get(i).value,
                val: $("input[name='val']").get(i).value,
                content: $("*[name='content']").get(i).value
            };
            rows[i] = row;
        }
        var data = {
            reqid: '123456',
            data: rows
        }
        var dataJson = JSON.stringify(data);
        alert(dataJson);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/editItem",
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

    $("#add").click(function () {
        var tableId = new Date().valueOf();
        $("#keiVals").append(
            "<tr id='" + tableId + "'>" +
            "<td>" +
            "<input type='text' class='form-control' name='kei' placeholder='键'>" +
            "</td>" +
            "<td>" +
            "<input type='text' class='form-control' name='val' placeholder='值'>" +
            "</td>" +
            "<td>" +
            "<textarea name='content' rows='1' cols='200'></textarea>" +
            "</td>" +
            "<td><button class='btn btn-default' onclick='delTable(" + tableId + ")'>删除</button></td>" +
            "</tr>"
        );
    });

    $("#dialog-message").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                if ($("#errorCode").text() == 000000) {
                    $(this).dialog("close");
                    // location.href = 'list.js';
                } else {
                    $(this).dialog("close");
                }
            }
        }
    });

});

function delTable(id) {
    $("#" + id).remove();
}

