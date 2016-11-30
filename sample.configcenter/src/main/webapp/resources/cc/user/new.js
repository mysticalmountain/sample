$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    var data = {
        reqId: "123456"
    };
    var dataJson = JSON.stringify(data);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryRole",
        data: dataJson,
        dataType: 'json',
        success: function (result) {
            $("#roles").empty();
            $.each(result.data, function (i, item) {
                $("#roles").append("<input type='checkbox' name='roleIds' value='" + item.id + "'/>&nbsp;" + item.name + "<br/>");
            });
        }
    });

    $("#save").click(function () {
        var profileDto = {
            age: $("#age").val(),
            sex: $("#sex").val(),
            address: $("#address").val()
        };
        var authorityDto = {
            accountNo: $("#accountNo").val(),
            password: $("#password").val()
        };
        var roleIds = $("*[name='roleIds']");
        var roleDtos = new Array();
        var index = 0;
        for (var i = 0; i < roleIds.size(); i++) {
            var row = {
                id: roleIds.get(i).value
            };
            if (roleIds.get(i).checked == true) {
                roleDtos[index] = row;
                index++;
            }
        }
        var userDto = {
            name: $("#name").val(),
            username: $("#username").val(),
            userType: 'PERSONAL',
            profileDto: profileDto,
            authorityDto: authorityDto,
            roleDtos: roleDtos
        };
        var reqData = {
            reqId: '123456',
            data: userDto
        };
        var dataJson = JSON.stringify(reqData);
        alert(dataJson);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/editUser",
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

    $("#dialog-message").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                if ($("#errorCode").text() == 000000) {
                    $(this).dialog("close");
                } else {
                    $(this).dialog("close");
                }
            }
        }
    });
});
