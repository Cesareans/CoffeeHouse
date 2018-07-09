<%@ page import="DebugUtil.Debug" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>

<link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon"/>
<link rel="stylesheet" href="./CSS/font.css">
<link rel="stylesheet" href="./CSS/xadmin.css">
<script type="text/javascript" src="./JS/jquery.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./JS/xadmin.js"></script>

<%
    boolean isAdd;
    String name,type,quantity,price,sales;
    String serialNumber = request.getParameter("serialNumber");
    if(serialNumber == null)
        isAdd = true;
    else {
        isAdd = false;//Update
    }
%>

<style>
    .layui-form-label {
        width: 60px;
        padding: 9px 0;
        text-align: center;
    }

    .layui-input-block {
        margin-left: 60px;
    }
</style>
<script>
    $(function () {
        layui.use(["form", "layer"], function () {
            var form = layui.form, layer = layui.layer;

            $("#addBtn").bind("click", function () {
                if (validate()) {
                    $.ajax({
                        type: "post",
                        url: "/adminAddMenu",
                        data: $("#userForm").serialize(),
                        success: function (result) {
                            if (result === "success")
                                layer.tips("新增餐点成功", "#addBtn", {tips: 1, time: 2000, end: closeFrame});
                            else
                                layer.tips("新增餐点失败", "#addBtn", {tips: 1, time: 2000});
                        }
                    });
                }
            });
            $("#cancelBtn").bind("click", function () {
                closeFrame();
            })
        });
    });

    function validate() {
        return verifyRequire([
            {id: "#name", msg: "请输入餐点名"},
            {id: "#quantity", msg: "请输入餐点库存"},
            {id: "#price", msg: "请输入餐点价格"}
        ]);
    }

    function verifyRequire(array) {
        for (var i in array) {
            if (!array.hasOwnProperty(i)) continue;
            var elem = $(array[i].id);
            if (elem.val() === "") {
                layer.tips(array[i].msg, array[i].id, {tips: 3});
                elem.focus();
                return false;
            }
        }
        return true;
    }

    function closeFrame() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }

</script>

<html>
<head>
    <title>Sisyphus-Admin</title>
</head>

<body>
<div class="x-body layui-anim layui-anim-up">
    <form class="layui-form layui-form-pane" id="userForm">
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">餐点名称：</label>
            <div class="layui-input-block">
                <input id="name" name="name" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="type" class="layui-form-label">餐点类型：</label>
            <div class="layui-input-block">
                <select id="type" name="type">
                    <option value="饮料">饮料</option>
                    <option value="甜品">甜品</option>
                    <option value="主食">主食</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="quantity" class="layui-form-label">餐点库存：</label>
            <div class="layui-input-block">
                <input id="quantity" name="quantity" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="price" class="layui-form-label">餐点单价：</label>
            <div class="layui-input-block">
                <input id="price" name="price" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="margin-bottom: 0;text-align: center">
            <button id="addBtn" name="addBtn" class="layui-btn" type="button">增加</button>
            <button id="cancelBtn" name="cancelBtn" class="layui-btn layui-btn-danger" type="button">取消</button>
        </div>
    </form>
</div>
</body>
</html>