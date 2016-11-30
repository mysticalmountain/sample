$(document).ready(function () {
    $.getScript("/resources/cc/common/menu.js");


    var reqData = {
        reqId: "123456",
        id: '10000000'
    };
    var dataJson = JSON.stringify(reqData);

    // var setting = {
    //     view: {
    //         showIcon: showIconForTree
    //     },
    //     data: {
    //         simpleData: {
    //             enable: true
    //         }
    //     }
    // };
    var setting = {
        // check: {
        //     enable: true
        // },
        data: {
            simpleData: {
                enable: true,
                idKey: 'name'
            }
        },
        callback: {
            onClick: zTreeOnClick,
            onCheck: zTreeOnCheck
        }
    };

    function zTreeOnCheck(event, treeId, treeNode) {
        alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
    };

    function zTreeOnClick(event, treeId, treeNode) {
        alert(treeNode.tId + ", " + treeNode.name);
    };

    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/service/queryMenu",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $.fn.zTree.init($("#treeDemo"), setting, data.data);
        }
    });
});
