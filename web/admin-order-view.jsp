<%@ page import="Entity.User" %>
<%@ page import="Database.DBUser" %>
<%@ page import="Database.DBOrder" %>
<%@ page import="Entity.Order" %>
<%@ page import="DebugUtil.Debug" %>
<%@ page import="Entity.OrderItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,userTel-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>

<link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon"/>
<link rel="stylesheet" href="./CSS/font.css">
<link rel="stylesheet" href="./CSS/xadmin.css">
<script type="text/javascript" src="./JS/jquery.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./JS/xadmin.js"></script>

<%
    DBOrder dbOrder = new DBOrder();
    String orderSN = request.getParameter("orderSN");
    Order order = dbOrder.getOrderBySerial(orderSN);
    dbOrder.close();
    ArrayList<OrderItem> orderItems = order.getOrderlist();
    Double sum = orderItems.stream().map(i->i.getMealPrice()*i.getQuantity()).reduce(0.0,(a,b)->a+b);
    String[] pieces = order.getDate().split(" ");
    String date = pieces[0];
    String time = pieces[1];
%>

<script>
    var iframeIndex;
    $(function () {
        iframeIndex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.iframeAuto(iframeIndex);
        layui.use(["form", "laydate", "layer"], function () {
            var form = layui.form, laydate = layui.laydate, layer = layui.layer;

            laydate.render({
                elem: "#birthday",
                showBottom: false,
                max: 0
            });
            $("#OKBtn").bind("click", function () {
                parent.layer.close(iframeIndex); //再执行关闭
            })
        });
        $("#orderSN").val("<%=order.getOrderSN()%>");
        $("#tel").val("<%=order.getUserTel()%>");
        $("#date").val("<%=date%>");
        $("#time").val("<%=time%>");
        $("#sum").val("<%=sum%>");
        var innerHtml = "<thead><tr><th>餐点名</th><th>单价</th><th>点单量</th></tr></thead><tbody>";
        <%for(OrderItem item : order.getOrderlist()){%>
        innerHtml+="<tr><td><%=item.getMealName()%></td><td><%=item.getMealPrice()%></td><td><%=item.getQuantity()%></td></tr>";
        <%}%>
        innerHtml += "</tbody>";
        $("#orderListTable").html(innerHtml);

    });

</script>

<html style="height: <%=398+39*orderItems.size()%>">
<head>
    <title>Sisyphus-Admin</title>
</head>

<body>
<div class="x-body layui-anim layui-anim-up">
    <form class="layui-form layui-form-pane" id="orderTable" lay-filter="orderTable">
        <div class="layui-form-item">
            <label for="orderSN" class="layui-form-label">订单号：</label>
            <div class="layui-input-block">
                <input id="orderSN" type="text" autocomplete="off" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="tel" class="layui-form-label">手机号：</label>
            <div class="layui-input-block">
                <input id="tel" type="text" autocomplete="off" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="date" class="layui-form-label">日期：</label>
            <div class="layui-input-block">
                <input id="date" type="text" autocomplete="off" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="time" class="layui-form-label">时间：</label>
            <div class="layui-input-block">
                <input id="time" type="text" autocomplete="off" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item">
            <table class="layui-table" id="orderListTable">

            </table>
        </div>
        <div class="layui-form-item">
            <label for="sum" class="layui-form-label">总价：</label>
            <div class="layui-input-block">
                <input id="sum" type="text" autocomplete="off" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item" style="margin-bottom: 0;text-align: center">
            <button id="OKBtn" class="layui-btn" type="button">确定</button>
        </div>
    </form>
</div>
</body>
</html>