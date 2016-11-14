$(document).ready(function () {
    $("#header").load("/views/common/header.html");
    $("#footer").load("/views/common/footer.html");
    $("#navbar").load("/views/common/navbar.html");

    $("#a_projects").click(function () {
        location.href = 'list.html';
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
        url: "http://localhost:8080/service/1001",
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
        projectId: $("#id").val()
    };
    var data = {
        reqid: '123456',
        data: project
    };
    var dataJson = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/1022",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#versionId").append("<option value='" + item.id + "'>" + item.name + "</option>");
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
        var id = $("table[name='id']");
        var kei = $("input[name='kei']");
        var val = $("input[name='val']");
        var content = $("input[name='content']");
        var rows = new Array();
        for (var i = 0; i < kei.size(); i++) {
            // alert($("table[name='id']").get(i));
            // alert($("table[name='id']").get(i).getAttribute("id"));
            // alert($("table[name='id']").get(i).text);
            var row = {
                id: $("table[name='id']").get(i).getAttribute("id"),
                kei: $("input[name='kei']").get(i).value,
                val: $("input[name='val']").get(i).value,
                projectId: $("#id").val(),
                versionId: $("#versionId").val(),
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
            url: "http://localhost:8080/service/1030",
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
            "<table id='" + tableId + "' class='table' name='id'>" +
            "<tr>" +
            "<td>" +
            "<label class='col-sm-2 control-label'>键</label>" +
            "<input type='text' class='form-control' name='kei' placeholder='键'>" +
            "</td>" +
            "<td>" +
            "<label class='col-sm-2 control-label'>值</label>" +
            "<input type='text' class='form-control' name='val' placeholder='值'>" +
            "</td><td></td>" +
            "</tr>" +
            "<tr>" +
            "<td colspan='2'>" +
            "<label class='col-sm-2 control-label'>描述</label>" +
            "<textarea name='content' rows='1' cols='200'></textarea>" +
            "</td><td><button class='btn btn-default' onclick='delTable(" + tableId + ")'>删除</button></td>" +
            "</tr>" +
            "</table>"
        );
    });

    $("#query").click(function () {
        $("#itemBody").empty();
        var projectId = $("#id").val();
        var versionId = $("#versionId").val();
        var data = {
            projectId: projectId,
            versionId: versionId
        };
        var reqdData = {
            reqid: '123456',
            data: data
        };
        var dataJson = JSON.stringify(reqdData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/1031",
            data: dataJson,
            dataType: 'json',
            success: function (data) {
                $.each(data.data, function (i, item) {
                    $("#itemBody").append(
                        "<table id='" + item.id + "' class='table' name='id'>" +
                        "<tr>" +
                        "<td>" +
                        "<label class='col-sm-2 control-label'>键</label>" +
                        "<input type='text' class='form-control' name='kei' value='" + item.kei + "'>" +
                        "</td>" +
                        "<td>" +
                        "<label class='col-sm-2 control-label'>值</label>" +
                        "<input type='text' class='form-control' name='val' value='" + item.val + "'>" +
                        "</td><td></td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td colspan='2'>" +
                        "<label class='col-sm-2 control-label'>描述</label>" +
                        "<textarea name='content' rows='1' cols='200'>" + item.content + "</textarea>" +
                        "</td><td><button class='btn btn-default' onclick='delTable(" + item.id + ")'>删除</button></td>" +
                        "</tr>" +
                        "</table>"
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
                    location.href = 'list.html';
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

