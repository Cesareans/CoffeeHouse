<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.User" %>
<%@ page import="Database.DBUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon"/>
<link rel="stylesheet" href="CSS/font.css">
<link rel="stylesheet" href="CSS/xadmin.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
<script src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="JS/xadmin.js"></script>

<script>

    $(function () {
        $("#delSelectedBtn").bind("click", delSelected());
        $("#addUserBtn").bind("click", addUser("添加用户", "admin-member-add", 640, 480));

    });
    layui.use(["laydate"], function () {
        var laydate = layui.laydate;
        var start = {
            min: laydate.now(),
            max: '2099-06-16 23:59:59',
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            min: laydate.now()
            , max: '2099-06-16 23:59:59'
            , istoday: false
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        $("#startDay").bind("click", function () {
            start.elem = this;
            laydate(start);
        });
        $("#endDay").bind("click", function () {
            end.elem = this;
            laydate(end);
        });
    });

    function delSelected() {
        layer.confirm('确认要删除吗？', function (index) {
            layer.msg('删除成功', {icon: 1});
        });
    }

    function addUser(title, url, w, h) {
        x_admin_show(title, url, w, h);
    }

    function showUser(title, url, id, w, h) {
        x_admin_show(title, url, w, h);
    }

    function editUser(title, url, id, w, h) {
        x_admin_show(title, url, w, h);
    }

    function disableUser(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {
            //发异步把用户状态进行更改
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="enableuser(this,id)" href="javascript:;" title="启用"><i class="layui-icon">&#xe62f;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="layui-btn layui-btn-disabled layui-btn-mini">已停用</span>');
            $(obj).remove();
            layer.msg('已停用!', {icon: 5, time: 1000});
        });
    }

    function enableuser(obj, id) {
        layer.confirm('确认要启用吗？', function (index) {
            //发异步把用户状态进行更改
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="disableUser(this,id)" href="javascript:;" title="停用"><i class="layui-icon">&#xe601;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>');
            $(obj).remove();
            layer.msg('已启用!', {icon: 6, time: 1000});
        });
    }

    function delUser(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }
</script>
<%
    int count = 5;
    DBUser dbUser = new DBUser();
    ArrayList<User> userList = dbUser.getAllUsers();//未考虑效率
%>

<html lang="en">
<head>
    <title>后台登录-管理员</title>
</head>
<body>
<div class="container">
    <div class="logo"><a href="admin-index.html">管理员系统</a></div>
    <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
    <ul class="layui-nav right">
        <li class="layui-nav-item">
            <a href="javascript:">管理员</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="">个人信息</a></dd>
                <dd><a href="">切换帐号</a></dd>
                <dd><a href="admin-login.html" id="quitLoginHref">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="admin-index.html">前台首页</a></li>
    </ul>
</div>
<div class="wrapper">
    <!--左导航栏-->
    <div class="left-nav">
        <div id="side-nav">
            <ul id="nav">
                <!-- 欢迎页面 -->
                <li class="list"><!--Delete "current" here-->
                    <a href="admin-index.html">
                        <i class="iconfont">&#xe761;</i>欢迎页面
                    </a>
                </li>
                <!-- 用户管理 -->
                <li class="list">
                    <a href="admin-member.jsp">
                        <i class="iconfont">&#xe70b;</i>用户管理
                    </a>
                </li>
                <!-- 菜单管理 -->
                <li class="list">
                    <a href=""><!--TODO:New html-->
                        <i class="iconfont">&#xe6a3;</i>
                        菜单管理
                    </a>
                </li>
                <!-- 订单查询 -->
                <li class="list">
                    <a href=""><!--TODO:New html-->
                        <i class="iconfont">&#xe6a3;</i>订单查询
                    </a>
                </li>
                <!-- 餐点欢迎度分析-->
                <li class="list">
                    <a href=""><!--TODO:New html-->
                        <i class="iconfont">&#xe6a3;</i>餐点欢迎度
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--右框体内容-->
    <div class="page-content">
        <div class="content">
            <!--筛选表单-->
            <form class="layui-form xbs">
                <div class="layui-form-pane" style="text-align: center;">
                    <div class="layui-form-item" style="display: inline-block;">
                        <label class="layui-form-label xbs768">日期范围</label>
                        <div class="layui-input-inline xbs768">
                            <input id="startDay" class="layui-input" placeholder="开始日" autocomplete="off">
                        </div>
                        <div class="layui-input-inline xbs768">
                            <input id="endDay" class="layui-input" placeholder="截止日" autocomplete="off">
                        </div>
                        <div class="layui-input-inline">
                            <input id="username" name="username" class="layui-input" placeholder="请输入用户名" type="text">
                        </div>
                        <div class="layui-input-inline" style="width:80px">
                            <button id="searchBtn" name="searchBtn" class="layui-btn"><i class="layui-icon">&#xe615;</i>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
            <!--操作栏-->
            <xblock>
                <button id="addUserBtn" name="addUserBtn" class="layui-btn"><i class="layui-icon">&#xe608;</i>添加
                </button>
                <button id="delSelectedBtn" name="delSelectedBtn" class="layui-btn layui-btn-danger"><i
                        class="layui-icon">&#xe640;</i>删除
                </button>
                <span id="dataSum" class="x-right" style="line-height:40px"></span>
            </xblock>
            <!--显示数据表-->
            <table class="layui-table">
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>用户名</th>
                    <th>性别</th>
                    <th>电话号码</th>
                    <th>密码</th>
                    <th>邮箱</th>
                    <th>创建时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="userInfoTable">
                <%
                    for (User user : userList) {
                %>
                <tr>
                    <td>
                        <input type="checkbox" value="1" name="">
                    </td>
                    <td>
                        <u style="cursor:pointer" onclick="showUser('张三','member-show.html','10001','360','400')">
                            <%=user.getUname()%>
                        </u>
                    </td>
                    <td>
                        <%=user.getGender()%>
                    </td>
                    <td>
                        <%=user.getTel()%>
                    </td>
                    <td>
                        <%=user.getUpassword()%>
                    </td>
                    <td>
                        <%=user.getEmail()%>
                    </td>
                    <td>
                        <%=user.getRegisterTime()%>
                    </td>
                    <td class="td-status">
                            <span class="layui-btn layui-btn-normal layui-btn-mini">
                                已启用
                            </span>
                    </td>
                    <td class="td-manage">
                        <a style="text-decoration:none" onclick="disableUser(this,'10001')" href="javascript:;"
                           title="停用">
                            <i class="layui-icon">&#xe601;</i>
                        </a>
                        <a title="编辑" href="javascript:;" onclick="editUser('编辑','member-edit.html','4','','510')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a style="text-decoration:none"
                           onclick="member_password('修改密码','member-password.html','10001','600','400')"
                           href="javascript:;" title="修改密码">
                            <i class="layui-icon">&#xe631;</i>
                        </a>
                        <a title="删除" href="javascript:;" onclick="delUser(this,'1')"
                           style="text-decoration:none">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="footer">
    <div class="copyright">Copyright ©2018 Sisyphus. All Rights Reserved.</div>
</div>
<div class="bg-changer">
    <div class="swiper-container changer-list">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img class="item" src="./images/a.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/b.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/c.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/d.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/e.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/f.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/g.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/h.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/i.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/j.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="./images/k.jpg" alt=""></div>
            <div class="swiper-slide"><span class="reset">初始化</span></div>
        </div>
    </div>
    <div class="bg-out"></div>
    <div id="changer-set"><i class="iconfont">&#xe696;</i></div>
</div>
</body>
</html>