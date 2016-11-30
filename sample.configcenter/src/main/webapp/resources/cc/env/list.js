$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    $("#envNew").click(function () {
        location.href = 'new.html';
    });

    var data = {};
    var reqData = {
        reqId: "123456",
        data: data
    };
    var dataJson = JSON.stringify(reqData);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryEnv",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#envs").append("<tr><td>" + item.id + "</td><td><a href='javascript:edit(" + item.id + ");'>" + item.name + "</a></td><td>" + item.code + "</td></tr>");
            });
        }
    });
});
function edit(id) {
    $.cookie("env-edit-id", id);
    location.href = 'edit.html';
}

