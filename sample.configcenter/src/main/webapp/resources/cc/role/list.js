$(document).ready(function () {

    $.getScript("/resources/cc/common/menu.js");
    $("#new").click(function () {
        location.href = 'new.html';
    });

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryRole",
        data: "{reqId:'123456'}",
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#users").append("<tr><td>" + item.id + "</td><td><a href='javascript:edit(" + item.id + ");'>" + item.name + "</a></td></tr>");
            });
        }
    });


});

function edit(id) {
    $.cookie("role-edit-id", id);
    location.href = 'new.html';
}
