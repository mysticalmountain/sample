$(document).ready(function () {
    $("#header").load("/views/common/header.html");
    $("#footer").load("/views/common/footer.html");
    $("#navbar").load("/views/common/navbar.html");
    $("#errorTip").hide();

    $("#login").click(function () {
        var data = {
            accountNo: $("#accountNo").val(),
            password: $("#password").val()
        };
        var reqData = {
            id: '123456',
            data: data
        }
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/user/login",
            data: dataJson,
            dataType: 'json',
            success: function (result) {
                if (false == result.success) {
                    $("#errorMsg").empty();
                    $("#errorMsg").append(result.errorMsg);
                    $("#errorTip").show();

                } else {
                    location.href = "/views/project/list.html";
                }
                // alert(result)
            }
        });
    });
});

