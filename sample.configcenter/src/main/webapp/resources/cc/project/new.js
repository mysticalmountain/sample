$(document).ready(function () {
    $("#header").load("/views/common/header.html");
    $("#footer").load("/views/common/footer.html");
    $("#navbar").load("/views/common/navbar.html");

    $("#a_projects").click(function () {
        location.href = 'list.html';
    });



    $("#save").click(function () {
        var data = {
            reqid: '123456',
            name: $("#name").val(),
            content: $("#content").val()
        }
        var dataJson = JSON.stringify(data);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/service/1002",
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
        var kei = $("input[name='kei']");
        var val = $("input[name='val']");
        var content = $("input[name='content']");
        var rows = new Array();
        for (var i = 0; i < kei.size(); i++) {
           var row = {
               kei: $("input[name='kei']").get(i).value,
               val: $("input[name='val']").get(i).value,
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
            url: "http://localhost:8080/service/1007",
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
        // alert(JSON.stringify(rows));
        // alert($("input[name='kei']").get(0).value);
        // $("input[name='kei']").each(function () {
        //     alert($(this).val());
        // })
    });

    $("#add").click(function () {
        var tableId = new Date().valueOf();
        $("#itemBody").append(
            "<table id='" + tableId + "' class='table'>" +
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

