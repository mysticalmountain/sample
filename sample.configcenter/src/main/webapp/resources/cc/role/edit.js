$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");

    $("#id").val($.cookie("role-edit-id"));
    $("#id").attr("disabled", "disabled");

    var data = {id: $.cookie("role-edit-id")};
    var reqData = {
        reqId: "123456",
        data: data
    };
    var dataJson = JSON.stringify(reqData);

    var permissions;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryRole",
        data: dataJson,
        dataType: 'json',
        success: function (res) {
            permissions = res.data[0].permissions;
            $("#name").val(res.data[0].name);
        }
    });

    data = {
        reqId: "123456",
        id: $.cookie("role-edit-id")
    };
    dataJson = JSON.stringify(data);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryService",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $.each(data.data, function (i, item) {
                var isCheck = false;
                $.each(permissions, function (k, permission) {
                    if (permission.resource.id == item.resourceDto.id) {
                        isCheck = true;
                    }
                });
                if (isCheck) {
                    $("#services").append("<input checked name='serviceId' type='checkbox' value='" + item.code + "'/> &nbsp;&nbsp;" + item.content + "&nbsp;&nbsp;(" + item.code + ")" + "<br/>");
                } else {
                    $("#services").append("<input name='serviceId' type='checkbox' value='" + item.code + "'/> &nbsp;&nbsp;" + item.content + "&nbsp;&nbsp;(" + item.code + ")" + "<br/>");
                }
            });
        }
    });


    $("#save").click(function () {
        var serviceIds = $("input[name='serviceId']");
        var selectedServiceIds = new Array();
        var j = 0;
        for (var i = 0; i < serviceIds.size(); i++) {
            if (serviceIds.get(i).checked == true) {
                selectedServiceIds[j] = serviceIds.get(i).value;
                j++;
            }
        }
        var data = {
            id: $("#id").val(),
            name: $("#name").val(),
            services: selectedServiceIds
        };
        var reqData = {
            reqId: '123456',
            data: data
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/editRole",
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
