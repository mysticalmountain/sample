$(document).ready(function () {

    $("#header").load("/views/common/header.html");
    $("#footer").load("/views/common/footer.html");
    $("#navbar").load("/views/common/navbar.html");

    $("#a_projects").click(function () {
        location.href = '../project/list.html';
    });

    $("#versionNew").click(function () {
        location.href = "new.html";
    });

    $("#linkNew").click(function () {
        location.href = "list.html";
    });

    versions();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/1001",
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
            project: $("#project").val()
        };
        var reqData = {
            reqid: new Date().valueOf(),
            data: data
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/1021",
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

    $("#query").click(function () {
        queryVersions($("#projectId").val());
    });

});

function versions() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/1022",
        data: "{reqId:'123456'}",
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#versions").append("<tr><td>" + item.id + "</td><td>" + item.name + "</td><td>" + item.content + "</td><td>" + item.project.name + "</td><td>" + "<a href='javascript:del(" + item.id + ")'><span id='" + item.id + "' class='ui-icon ui-icon-trash'></span></a>" + "</td></tr>");
            });
        }
    });
}

function queryVersions(projectId) {
    $("#versions").empty();
    var project = {
      projectId: $("#projectId").val()
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
                $("#versions").append("<tr><td>" + item.id + "</td><td><a href='javascript:edit(" + item.id + ");'>" + item.name + "</a></td><td>" + item.content + "</td><td>" + item.project.name + "</td><td>" + "<a href='javascript:del(" + item.id + ")'><span id='" + item.id + "' class='ui-icon ui-icon-trash'></span></a>" + "</td></tr>");
            });
        }
    });
}
