<%@ page import="Database.DBUser" %>
<%@ page import="Database.DBMenu" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Menu" %>
<%@ page import="Database.DBOrder" %>
<%@ page import="Entity.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,minimum-scale=0.4, initial-scale=0.8"/>

<link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon"/>
<link rel="stylesheet" href="./CSS/font.css">
<link rel="stylesheet" href="./CSS/xadmin.css">
<script type="text/javascript" src="./JS/jquery.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./JS/xadmin.js"></script>

<%
    String adminName = ((String) session.getAttribute("adminName"));

    DBUser dbUser = new DBUser();
    int usersNum = dbUser.getAllUsers().size();

    DBMenu dbMenu = new DBMenu();
    ArrayList<Menu> menus = dbMenu.getAllmenu();
    int menuItemsNum = menus.size();
    int stockInTotal = 0;
    for (Menu menuItem : menus) stockInTotal += menuItem.getQuantity();

    DBOrder dbOrder = new DBOrder();
    ArrayList<Order> orders = dbOrder.getAllOrders();
%>
<script>

</script>
<html>
<head>
    <title>Sisyphus-Admin</title>
</head>
<body>
<div class="x-body layui-anim layui-anim-up">
    <blockquote class="layui-elem-quote" style="padding: 5px 15px;">
        <div style="line-height: 38px">
            <span>欢迎管理员：<%=adminName%></span>
            <span style="float: right"><button id="logoutBtn" name="logoutBtn" class="layui-btn"
                                               type="button">退出</button></span>
        </div>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据统计</legend>
        <div class="layui-field-box">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside"
                             lay-arrow="none" style="width: 100%; height: 90px;">
                            <div carousel-item="">
                                <ul class="layui-row layui-col-space10 layui-this">
                                    <li class="layui-col-xs2">
                                        <a class="x-admin-backlog-body">
                                            <h3>会员数</h3>
                                            <p>
                                                <cite><%=usersNum%>
                                                </cite></p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs2">
                                        <a class="x-admin-backlog-body">
                                            <h3>餐点种类数</h3>
                                            <p>
                                                <cite><%=menuItemsNum%>
                                                </cite></p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs2">
                                        <a class="x-admin-backlog-body">
                                            <h3>餐点库存总量</h3>
                                            <p>
                                                <cite><%=stockInTotal%>
                                                </cite></p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs2">
                                        <a class="x-admin-backlog-body">
                                            <h3>订单总数</h3>
                                            <p>
                                                <cite><%=orders.size()%></cite></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>系统信息</legend>
        <div class="layui-field-box">
            <table class="layui-table">
                <tbody>
                <tr>
                    <th>Web服务器</th>
                    <td>Tomcat 9.0.8</td>
                </tr>
                <tr>
                    <th>JDK版本</th>
                    <td>1.8</td>
                </tr>
                <tr>
                    <th>MYSQL版本</th>
                    <td>MySQL 5.5.15</td>
                </tr>
                <tr>
                    <th>服务器架构模式</th>
                    <td>MVVM</td>
                </tr>
                <tr>
                    <th>后台前端框架</th>
                    <td>BootStrap 3.3.1 + LayUI 2.3.0 + JQuery 2.0.0</td>
                </tr>
                <tr>
                    <th>后台后端框架</th>
                    <td>J2EE</td>
                </tr>
                <tr>
                    <th>后台前端框架</th>
                    <td>LayUI 2.3.0 + JQuery 2.0.0</td>
                </tr>
                <tr>
                    <th>后台后端框架</th>
                    <td>J2EE</td>
                </tr>
                </tbody>
            </table>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>开发团队</legend>
        <div class="layui-field-box">
            <table class="layui-table">
                <tbody>
                <tr>
                    <th>小组队长</th>
                    <td>胡泽勇</td>
                    <td>22320162201037</td>
                </tr>
                <tr>
                    <th>小组成员</th>
                    <td>梁依帆</td>
                    <td>25120152201910</td>
                </tr>
                <tr>
                    <th>小组成员</th>
                    <td>刘畅</td>
                    <td>33920152203997</td>
                </tr>
                <tr>
                    <th>小组成员</th>
                    <td>刘予佳</td>
                    <td>24320162202877</td>
                </tr>
                <tr>
                    <th>小组成员</th>
                    <td>石新羽</td>
                    <td>34520162201351</td>
                </tr>
                <tr>
                    <th>官网</th>
                    <td colspan="2"><a id="jmpToFront" class='x-a' style="cursor: hand">访问官网</a></td>
                </tr>
                <tr>
                    <th>版权所有</th>
                    <td colspan="2">Copyright ©2018 Sisyphus.</td>
                </tr>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>
</body>
</html>