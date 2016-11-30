$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    $("#id").val($.cookie("user-edit-id"));
    $("#id").attr("disabled", "disabled");

    var userDto = {
        id: $.cookie("user-edit-id")
    };
    var reqData = {
        reqId: "123456",
        data: userDto
    };
    var dataJson = JSON.stringify(reqData);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryUser",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                $("#name").val(item.name);
                $("#username").val(item.username);
            });
        }
    });

    data = {
        userId: $.cookie("user-edit-id")
    };
    reqData = {
        reqId: "123456",
        data: data
    }
    dataJson = JSON.stringify(reqData);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryProfile",
        data: dataJson,
        dataType: 'json',
        success: function (result) {
            $("#sex").val(result.data[0].sex);
            $("#age").val(result.data[0].age);
            $("#address").val(result.data[0].address);
        }
    });

    data = {
        reqId: '123456',
        id: $.cookie("user-edit-id")
    };
    dataJson = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryUserAuthority",
        data: dataJson,
        dataType: 'json',
        success: function (result) {
            $("#accountNo").val(result.data[0].accountNo);
            $("#password").val(result.data[0].password);
        }
    });

    var userRoles;
    var data = {
        reqId: '123456',
        id: $.cookie("user-edit-id")
    };
    var dataJson = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryUserRole",
        data: dataJson,
        dataType: 'json',
        success: function (res) {
            userRoles = res;
            // alert(userRoles.data[0].id);
        }
    });

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryRole",
        data: dataJson,
        dataType: 'json',
        success: function (result) {
            $("#roles").empty();
            $.each(result.data, function (i, item) {
                var isCheck = false;
                while (userRoles == undefined) {
                    continue;
                }
                $.each(userRoles.data, function (k, userRole) {
                    if (userRole.id == item.id) {
                        isCheck = true;
                    }
                });
                if (isCheck) {
                    $("#roles").append("<input checked type='checkbox' name='roleIds' value='" + item.id + "'/>&nbsp;" + item.name + "<br/>");
                } else {
                    $("#roles").append("<input type='checkbox' name='roleIds' value='" + item.id + "'/>&nbsp;" + item.name + "<br/>");
                }
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

function sleep(numberMillis, condition) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (condition == undefined) {
        now = new Date();
        if (now.getTime() > exitTime)    return;
    }
}
