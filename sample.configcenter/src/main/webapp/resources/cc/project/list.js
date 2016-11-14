$(document).ready(function () {
    projects();

    $("#projectNew").click(function () {
        location.href = 'new.html';
    });

    $("#a_projects").click(function () {
        location.href = 'list.html';
    });

    $("#linkNew").click(function () {
        location.href = "../version/list.html";
    });

    $("#header").load("/views/common/header.html");
    $("#footer").load("/views/common/footer.html");
    $("#navbar").load("/views/common/navbar.html");
});
function projects() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/1001",
        data: "{reqId:'123456'}",
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#projects").append("<tr><td>" + item.id + "</td><td><a href='javascript:edit(" + item.id + ");'>" + item.name + "</a></td><td>" + item.content + "</td><td>" + "<a href='javascript:del(" + item.id + ")'><span id='" + item.id + "' class='ui-icon ui-icon-trash'></span></a>" + "</td></tr>");
            });
        }
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
}

function del(id) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/1004",
        data: "{id:" + id + "}",
        dataType: 'json',
        success: function (result) {
            $("#errorCode").html('');
            $("#errorMessage").html('');
            $("#errorCode").html(result.errorCode);
            $("#errorMessage").html(result.errorMsg);
            $("#dialog-message").dialog('open');
            return false;
        },
        error: function (data) {
            alert(data.errorCode);
        }
    });
}

function edit(id) {
    $.cookie("project-edit-id", id);
    location.href = 'edit.html';
}
