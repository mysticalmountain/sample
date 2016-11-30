$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    $("#a_projects").click(function () {
        location.href = 'list.js';
    });

    $("#id").val($.cookie("project-edit-id"));
    $("#id").attr("disabled", "disabled");

    var data = {
        reqId: "123456",
        id: $.cookie("project-edit-id")
    };
    var dataJson = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryProject",
        data: dataJson,
        dataType: "json",
        success: function (res) {
            $("#name").val(res.data[0].name);
            $("#content").val(res.data[0].content);
        },
        error: function (res) {
            alert(res);
        }
    });

    var project = {
        projectId: $.cookie("project-edit-id")//$("#id").val()
    };
    var data = {
        reqid: '123456',
        data: project
    };
    var dataJson = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryVersion",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $("#versionId").empty();
            $.each(data.data, function (i, item) {
                $("#versionId").append("<option></option><option value='" + item.id + "'>" + item.name + "</option>");
            });
        }
    });

    var reqData = {
        reqId: "123456"
    };
    dataJson = JSON.stringify(reqData);

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

    $("#save").click(function () {
        var data = {
            id: $("#id").val(),
            reqid: '123456',
            name: $("#name").val(),
            content: $("#content").val()
        }
        var dataJson = JSON.stringify(data);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/1005",
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
        var id = $("tr[name='id']");
        var kei = $("input[name='kei']");
        var val = $("input[name='val']");
        var content = $("input[name='content']");
        var rows = new Array();
        for (var i = 0; i < kei.size(); i++) {
            // alert($("table[name='id']").get(i));
            // alert($("table[name='id']").get(i).getAttribute("id"));
            // alert($("table[name='id']").get(i).text);
            var row = {
                id: $("tr[name='id']").get(i).getAttribute("id"),
                kei: $("input[name='kei']").get(i).value,
                val: $("input[name='val']").get(i).value,
                projectId: $("#id").val(),
                versionId: $("#versionId").val(),
                envId: $("#envId").val(),
                content: $("*[name='content']").get(i).value
            };
            rows[i] = row;
        }
        var data = {
            reqid: '123456',
            data: rows
        }
        var dataJson = JSON.stringify(data);
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
        $("#itemBody").append(
            "<tr name='id' id='" + tableId + "'>" +
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

    $("#query").click(function () {
        $("#itemBody").empty();
        var projectId = $("#id").val();
        var versionId = $("#versionId").val();
        var envId = $("#envId").val();
        var data = {
            projectId: projectId,
            versionId: versionId,
            envId: envId
        };
        var reqdData = {
            reqid: '123456',
            data: data
        };
        var dataJson = JSON.stringify(reqdData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/queryItem",
            data: dataJson,
            dataType: 'json',
            success: function (data) {
                $.each(data.data, function (i, item) {
                    $("#itemBody").append(
                        // "<table id='" + item.id + "' class='table' name='id'>" +
                        "<tr id='" + item.id + "' class='table' name='id'>" +
                        "<td>" +
                        "<input type='text' class='form-control' name='kei' value='" + item.kei + "'>" +
                        "</td>" +
                        "<td>" +
                        "<input type='text' class='form-control' name='val' value='" + item.val + "'>" +
                        "</td>" +
                        "<td>" +
                        "<textarea name='content' rows='1' cols='200'>" + item.content + "</textarea>" +
                        "</td>" +
                        "<td><button class='btn btn-default' onclick='delTable(" + item.id + ")'>删除</button></td>" +
                        "</tr>"
                        // "</table>"
                    );
                    // $("#versionId").append("<option value='" + item.id + "'>" + item.name + "</option>");
                });
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

