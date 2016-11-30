$(document).ready(function () {
    $("#header").load("/views/common/header.html");
    $("#footer").load("/views/common/footer.html");
    $("#navbar").load("/views/common/navbar.html");

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryService",
        data: "{reqId:'123456'}",
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#users").append("<tr><td>" + item.code + "</td><td>" + item.content + "</td><td>" + item.system + "</td><td>" + item.module + "</td><td>" + item.isWriteLog + "</td><td>" + item.isValidateReq + "</td><td>" + item.isIdempotent + "</td></tr>");
            });
        }
    });


});
