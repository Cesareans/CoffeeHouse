<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords" content="café, cafe, coffee shop, 西西弗斯,Sisyphus"/>

<link href="./images/favicon.png" rel="shortcut icon"/>
<link href="CSS/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
<link href="CSS/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
      rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic'
      rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="CSS/jquery.countdown.css"/>
<link href="CSS/animate.min.css" rel="stylesheet">
<link href="lib/layui/css/layui.css" rel="stylesheet">

<script src="JS/jquery.min.js"></script>
<script src="JS/simpleCart.min.js"></script>
<script type="text/javascript" src="JS/bootstrap-3.1.1.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="JS/wow.min.js"></script>
<script src="JS/bootstrap-3.1.1.min.js"></script>

<%
    String userTel = ((String) session.getAttribute("usertel"));
    boolean hasLogin = false;
    if(userTel != null){
        hasLogin = true;
    }
%>

<script type="application/x-javascript">
    addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    }
</script>
<script>
    new WOW().init();
    var list;
    var node;
    var qty;
    var Node;
    var total;
    var totalNode;
    var serialNumber;
    //获取购物车表中的信息并显示
    $.ajax({
        url: "cart",
        method: "get",
        success: function (result) {
            var cart = $.parseJSON(result);
            var menu = cart["menu"];
            var items = cart["items"];
            list = items["orderlist"];
            $("#number").text(list.length);

            for (var i = 0; i < list.length; i++) {
                for (var j = 0; j < menu.length; j++) {
                    if (list[i].mealSerialNumber === menu[j].serialNumber) {
                        list[i].name = menu[j].name;
                        list[i].pictureUrl = menu[j].pictureUrl;
                        list[i].price = menu[j].price;
                        break;
                    }
                }
            }
            var output = "<thead>\n" +
                "                <tr>\n" +
                "                    <th>序号</th>\n" +
                "                    <th>商品</th>\n" +
                "                    <th>数量</th>\n" +
                "                    <th>商品姓名</th>\n" +
                "                    <th>单价</th>\n" +
                "                    <th>总价</th>\n" +
                "                    <th>删除</th>\n" +
                "                </tr>\n" +
                "          </thead>";
            for (var k = 0; k < list.length; k++) {
                output += "<tr>\n" +
                    "    <td class=\"invert\">" + (k + 1) + "</td>\n" +
                    "\t\n" +
                    "    <td class=\"invert-image\">\n" +
                    "\t\t<a href=\"single.jsp\">\n" +
                    "\t\t\t<img src=\"" + list[k].pictureUrl + "\" alt=\" \" class=\"img-responsive\"/>\n" +
                    "\t\t</a>\n" +
                    "    </td>\n" +
                    "\t\n" +
                    "    <td class=\"invert\">\n" +
                    "        <div class=\"quantity\">\n" +
                    "            <div class=\"quantity-select\">\n" +
                    "                <div class=\"entry value-minus\" disabled='true'>-</div>\n" +
                    "                <div class=\"entry value\"><span>" + list[k].quantity + "</span></div>\n" +
                    "                <div id='plus' class=\"entry value-plus active\">+</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </td>\n" +
                    "\t\n" +
                    "    <td class=\"invert\">" + list[k].name + "</td>\n" +
                    "    <td class=\"invert\">￥" + (list[k].price).toFixed(2) + "</td>\n" +
                    "    <td class=\"invert\">￥" + (list[k].price * list[k].quantity).toFixed(2) + "</td>\n" +
                    "    <td class=\"invert\">\n" +
                    "        <div class=\"rem\">\n" +
                    "            <div class=\"close1\">*</div>\n" +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>";
            }
            $("#cart").html(output);
        }
    });

    $(function () {
        //改变购买数量以及删除商品
        $("#cart").bind("click", function (e) {
            if (e.target.innerText === "+") {
                node = e.target.previousSibling.previousSibling.firstChild;
                qty = (Number)(node.innerText);
                qty++;
                $(node).text(qty);
                Node = e.target.parentNode.parentNode.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.innerText;
                total = list[Node-1].price*qty;
                serialNumber = list[Node-1].mealSerialNumber;
                totalNode = e.target.parentNode.parentNode.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling;
                $(totalNode).text("￥"+total.toFixed(2));
                updateQty(qty, serialNumber);
            }
            else if (e.target.innerText === "-") {
                node = e.target.nextSibling.nextSibling.firstChild;
                qty = (Number)(node.innerText);
                qty--;
                if (qty === 0) {
                    qty++;
                    $(e.target).attr("disabled",true);
                }
                $(node).text(qty);
                Node = e.target.parentNode.parentNode.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.innerText;
                total = list[Node-1].price*qty;
                serialNumber = list[Node-1].mealSerialNumber;
                totalNode = e.target.parentNode.parentNode.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling;
                $(totalNode).text("￥"+total.toFixed(2));
                updateQty(qty, serialNumber);
            }
            else if(e.target.innerText==="*"){
                Node = e.target.parentNode.parentNode.parentNode.firstChild.nextSibling.innerText;
                serialNumber = list[Node-1].mealSerialNumber;
                var row = e.target.parentNode.parentNode.parentNode;

                $(row).fadeOut('slow', function () {
                    $(row).remove();
                });

                $.ajax({
                    url:"delete",
                    method:"post",
                    data:{
                        "serialNumber":serialNumber
                    },
                    success:function(result) {}
                });

                $.ajax({
                    url: "cart",
                    method: "get",
                    success: function (result) {
                        var cart = $.parseJSON(result);
                        var menu = cart["menu"];
                        var items = cart["items"];
                        list = items["orderlist"];
                        $("#number").text(list.length);

                        for (var i = 0; i < list.length; i++) {
                            for (var j = 0; j < menu.length; j++) {
                                if (list[i].mealSerialNumber === menu[j].serialNumber) {
                                    list[i].name = menu[j].name;
                                    list[i].pictureUrl = menu[j].pictureUrl;
                                    list[i].price = menu[j].price;
                                    break;
                                }
                            }
                        }
                        var output = "<thead>\n" +
                            "                <tr>\n" +
                            "                    <th>序号</th>\n" +
                            "                    <th>商品</th>\n" +
                            "                    <th>数量</th>\n" +
                            "                    <th>商品姓名</th>\n" +
                            "                    <th>单价</th>\n" +
                            "                    <th>总价</th>\n" +
                            "                    <th>删除</th>\n" +
                            "                </tr>\n" +
                            "          </thead>";
                        for (var k = 0; k < list.length; k++) {
                            output += "<tr>\n" +
                                "    <td class=\"invert\">" + (k + 1) + "</td>\n" +
                                "\t\n" +
                                "    <td class=\"invert-image\">\n" +
                                "\t\t<a href=\"single.jsp\">\n" +
                                "\t\t\t<img src=\"" + list[k].pictureUrl + "\" alt=\" \" class=\"img-responsive\"/>\n" +
                                "\t\t</a>\n" +
                                "    </td>\n" +
                                "\t\n" +
                                "    <td class=\"invert\">\n" +
                                "        <div class=\"quantity\">\n" +
                                "            <div class=\"quantity-select\">\n" +
                                "                <div class=\"entry value-minus\" disabled='true'>-</div>\n" +
                                "                <div class=\"entry value\"><span>" + list[k].quantity + "</span></div>\n" +
                                "                <div id='plus' class=\"entry value-plus active\">+</div>\n" +
                                "            </div>\n" +
                                "        </div>\n" +
                                "    </td>\n" +
                                "\t\n" +
                                "    <td class=\"invert\">" + list[k].name + "</td>\n" +
                                "    <td class=\"invert\">￥" + (list[k].price).toFixed(2) + "</td>\n" +
                                "    <td class=\"invert\">￥" + (list[k].price * list[k].quantity).toFixed(2) + "</td>\n" +
                                "    <td class=\"invert\">\n" +
                                "        <div class=\"rem\">\n" +
                                "            <div class=\"close1\">*</div>\n" +
                                "        </div>\n" +
                                "    </td>\n" +
                                "</tr>";
                        }
                        $("#cart").html(output);
                    }
                });


            }
        });


        //提交订单,转为订单
        $("#ordercheck").bind("click", function() {
            $.ajax({
                url:"order",
                method:"post",
                success:function(result) {
                    if(result==="false") alert("失败");
                }
            });
        });
    });

    //更新数据库商品数量
    function updateQty(qty, serialNumber) {
        $.ajax({
            url:"cart",
            method:"post",
            data:{
                "qty":qty,
                "serialNumber":serialNumber
            },
            success:function(result) {
                if(result==="false") alert("fail");
            }
        });
    }
</script>

<html>
<head>
    <title>购物车 | 西西弗斯咖啡屋</title>
</head>
<body>
<div class="header">
    <div class="container">
        <div class="header-grid">
            <div class="header-grid-left animated wow slideInLeft" data-wow-delay=".5s">
                <%if(hasLogin){%>
                <ul>
                    <li><i class="glyphicon glyphicon-user" aria-hidden="true"></i><a href="information.jsp">个人信息</a></li>
                    <li><i class="glyphicon glyphicon-log-out" aria-hidden="true"></i><a href="logout">退出</a></li>
                </ul>
                <%}else{%>
                <ul>
                    <li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a href="login.jsp">登录</a></li>
                    <li><i class="glyphicon glyphicon-book" aria-hidden="true"></i><a href="register.jsp">注册</a></li>
                </ul>
                <%}%>
            </div>
            <div class="header-grid-right animated wow slideInRight" data-wow-delay=".5s">
                <ul class="social-icons">
                    <li><a href="#" class="facebook"></a></li>
                    <li><a href="#" class="twitter"></a></li>
                    <li><a href="#" class="g"></a></li>
                    <li><a href="#" class="instagram"></a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="logo-nav">
            <div class="logo-nav-left animated wow slideInLeft" data-wow-delay=".5s">
                <h1><a href="index.jsp">西西弗斯咖啡屋 </a></h1><span font-size="5px">心意，从这一杯开始</span>
            </div>
            <div class="logo-nav-left1 animated wow zoomIn"  data-wow-delay=".5s">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header nav_2">
                        <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                                data-target="#bs-megadropdown-tabs">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav">&emsp;
                            <li><a href="index.jsp">&emsp;&emsp;&emsp;&emsp;主页</a></li>
                            <li><a href="drinks.jsp">&nbsp;饮料</a></li>
                            <li><a href="desserts.jsp">&nbsp;甜品</a></li>
                            <li><a href="meals.jsp">&nbsp;主食</a></li>
                            <li><a href="mailto:cesarean@foxmail.com">&nbsp;联系我们</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="header-right animated wow slideInRight" data-wow-delay=".5s">
                <div class="cart box_1">
                    <a href="checkout.jsp">
                        <h3>
                            <div class="total">
                                <span class="simpleCart_total"></span> (<span id="simpleCart_quantity"
                                                                              class="simpleCart_quantity"></span> 个项目)
                            </div>
                            <img src="images/bag.png" alt=""/>
                        </h3>
                    </a>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
            <li class="active">购物车</li>
        </ol>
    </div>
</div>
<div class="checkout">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s">您的购物车里现有 <span id="number"></span><span> 件产品</span>
        </h3>
        <div class="checkout-right animated wow slideInUp" data-wow-delay=".5s">
            <table class="timetable_sub" id="cart">
            </table>
        </div>
        <div class="checkout-left">
            <div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
                <a href="index.jsp"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>返回点餐</a>
                <a href="ordercheck.jsp" id="ordercheck">
                    <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
                    确认订单
                </a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- //checkout -->
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-grids">
            <div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".5s">
                <h3>关于我们</h3>
                <p>西西弗斯咖啡屋是除“居住空间”和“职场”以外的第三空间。优雅舒适的环境，以及独一无二的香醇咖啡和法式浪漫餐点，让你与生活的繁琐保持距离。
                    <span>这里是属于每个人的第三空间，他们不在西西弗斯，就在去西西弗斯的路上。</span></p>
            </div>
            <div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".6s">
                <h3>联系我们</h3>
                <ul>
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>白城沙滩，思明区，厦门市<span>福建省</span>
                    </li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="mailto:info@example.com">cesarean@foxmail.com
                    </a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+178 5971 0072</li>
                </ul>
            </div>
            <div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".7s">
                <h3>门店展示</h3>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面1.png" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面2.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面3.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面4.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面1.png" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面2.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面3.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面4.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面1.png" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面2.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面3.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="index.jsp"><img src="images/店面4.jpg" alt=" " class="img-responsive"/></a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".8s">
                <h3>相关博客</h3>
                <div class="footer-grid-sub-grids">
                    <div class="footer-grid-sub-grid-left">
                        <a href="https://www.starbucks.com.cn/coffee-blog/the-origin-of-coffee-and-its-cultivation/"><img
                                src="images/博客1.jpg" alt=" " class="img-responsive"/></a>
                    </div>
                    <div class="footer-grid-sub-grid-right">
                        <h4>
                            <a href="https://www.starbucks.com.cn/coffee-blog/the-origin-of-coffee-and-its-cultivation/">咖啡的起源与培植</a>
                        </h4>
                        <p>Posted On 5/7/2018</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="footer-grid-sub-grids">
                    <div class="footer-grid-sub-grid-left">
                        <a href="https://www.starbucks.com.cn/coffee-blog/history-of-coffee/"><img src="images/博客2.jpg"
                                                                                                   alt=" "
                                                                                                   class="img-responsive"/></a>
                    </div>
                    <div class="footer-grid-sub-grid-right">
                        <h4><a href="https://www.starbucks.com.cn/coffee-blog/history-of-coffee/">咖啡的历史</a></h4>
                        <p>Posted On 5/7/2018</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="footer-logo animated wow slideInUp" data-wow-delay=".5s">
            <h2><a href="index.jsp">西西弗斯咖啡屋<span>心意，从这一杯开始</span></a></h2>
        </div>
        <div class="copy-right animated wow slideInUp" data-wow-delay=".5s">
            <p>Copyright ©2018 Sisyphus. All Rights Reserved.</p>
        </div>
    </div>
</div>
</body>
</html>