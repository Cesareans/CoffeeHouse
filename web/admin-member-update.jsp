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
    String username,gender,birthday,email,password;
    String telephone = request.getParameter("telephone");
    if(telephone == null)
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
        layui.use(["form", "laydate", "layer"], function () {
            var form = layui.form, laydate = layui.laydate, layer = layui.layer;

            laydate.render({
                elem: "#birthday",
                showBottom: false
            });
            $("#addBtn").bind("click", function () {
                if (validate()) {
                    $.ajax({
                        type: "post",
                        url: "/adminAddUser",
                        data: $("#userForm").serialize(),
                        success: function (result) {
                            if (result === "success")
                                layer.tips("新增用户成功", "#addBtn", {tips: 1, time: 2000, end: closeFrame});
                            else
                                layer.tips("新增用户失败", "#addBtn", {tips: 1, time: 2000});
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
            {id: "#username", msg: "请输入用户名"},
            {id: "#birthday", msg: "请输入生日"},
            {id: "#telephone", msg: "请输入电话"},
            {id: "#email", msg: "请输入邮箱"},
            {id: "#password", msg: "请输入密码"}
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
            <label for="username" class="layui-form-label">用户：</label>
            <div class="layui-input-block">
                <input id="username" name="username" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别：</label>
            <div class="layui-input-block">
                <input type="radio" name="gender" value="男" title="男" checked>
                <input type="radio" name="gender" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="birthday" class="layui-form-label">生日：</label>
            <div class="layui-input-block">
                <input id="birthday" name="birthday" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="telephone" class="layui-form-label">电话：</label>
            <div class="layui-input-block">
                <input id="telephone" name="telephone" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="email" class="layui-form-label">邮箱：</label>
            <div class="layui-input-block">
                <input id="email" name="email" type="text" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="password" class="layui-form-label">密码：</label>
            <div class="layui-input-block">
                <input id="password" name="password" type="text" autocomplete="off" class="layui-input">
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