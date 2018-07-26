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
    if (userTel != null) {
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
    //显示菜单
    $.ajax({
        url: "dessert",
        method: "get",
        success: function (result) {
            var menu = $.parseJSON(result);
            var output = "";
            for (var i = 0; i < 3; i++) {
                output += "<div class=\"col-md-4 products-right-grids-bottom-grid\">";
                for (var j = 0; j < 3; j++) {
                    output += "<div class=\"new-collections-grid1 products-right-grid1 animated wow slideInUp\" data-wow-delay=\".5s\">\n" +
                        "\t\t\t\t\t\t\t<div class=\"new-collections-grid1-image\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"single.jsp\" class=\"product-image\"><img src=\"" + menu[j + 3 * i].pictureUrl + "\" alt=\" \" class=\"img-responsive\"></a>\n" +
                        "\t\t\t\t\t\t\t\t<div class=\"new-collections-grid1-image-pos products-right-grids-pos\">\n" +
                        "\t\t\t\t\t\t\t\t\t<a href=\"single.jsp\">详细信息</a>\n" +
                        "\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t<div class=\"new-collections-grid1-right products-right-grids-pos-right\">\n" +
                        "\t\t\t\t\t\t\t\t\t<div class=\"rating\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div class=\"rating-left\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/2.png\" alt=\" \" class=\"img-responsive\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div class=\"rating-left\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/2.png\" alt=\" \" class=\"img-responsive\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div class=\"rating-left\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/2.png\" alt=\" \" class=\"img-responsive\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div class=\"rating-left\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/1.png\" alt=\" \" class=\"img-responsive\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div class=\"rating-left\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/1.png\" alt=\" \" class=\"img-responsive\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div class=\"clearfix\"> </div>\n" +
                        "\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t<h4><a class=\"name\" href=\"single.jsp\">" + menu[j + 3 * i].name + "</a></h4>\n" +
                        "\t\t\t\t\t\t\t<p></p>\n" +
                        "\t\t\t\t\t\t\t<div class=\"simpleCart_shelfItem products-right-grid1-add-cart\">\n" +
                        "\t\t\t\t\t\t\t\t<p><i>￥"+Math.round(menu[j + 3 * i].price/0.8)+"</i> <span class=\"price\">￥" + menu[j + 3 * i].price + "</span><a class=\"add\" style='cursor: pointer'>加入购物车</a></p>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t<div hidden>"+menu[j+3*i].serialNumber+"</div>" +
                        "\t\t\t\t\t\t</div>";
                }
                output += "</div>";
            }
            output += "<div class=\"clearfix\"> </div>";
            $('#products-right-grids-bottom').html(output);
        }
    });

    //商品提交至购物车
    function updateCart(price, name, qty, serialNumber, e) {
        $.ajax({
            url:"cart",
            method:"post",
            data:{
                "price":price,
                "name":name,
                "qty":qty,
                "serialNumber":serialNumber
            },
            success:function(result) {
                if(result==="true") {
                    layer.tips('添加购物车成功',$(e), {
                        tips: 3
                    });
                } else{
                    layer.tips('添加购物车失败',$(e), {
                        tips: 3
                    });
                }
            }
        });
    }

    //点击加入购物车事件
    $(function () {
        layui.use("layer" , function () {
            var layer = layui.layer;
        });
        var price;
        var name;
        var qty = 1;
        var serialNumber;
        //添加至购物车
        $('#products-right-grids-bottom').bind("click", function (e) {
            price = e.target.previousSibling.innerText;
            price = price.slice(1,3);
            name = e.target.parentNode.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.firstChild.innerText;
            serialNumber = e.target.parentNode.parentNode.nextSibling.nextSibling.innerText;
            updateCart(price, name, qty, serialNumber, e.target);
        });
        $("#add1").bind("click",function() {
            price = 15;
            name = "法式闪电泡芙";
            serialNumber = "0019";
            updateCart(price, name, qty, serialNumber, this);
        });
        $("#add2").bind("click",function() {
            price = 25;
            name = "浓醇三重黑巧克力蛋糕";
            serialNumber = "0020";
            updateCart(price, name, qty, serialNumber, this);
        });
        $("#add3").bind("click",function() {
            price = 28;
            name = "香浓巧克力麦芬";
            serialNumber = "0021";
            updateCart(price, name, qty, serialNumber, this);
        });
    });


</script>

<!--
<script type='text/javascript'>//<![CDATA[
$(window).load(function(){
$( "#slider-range" ).slider({
range: true,
min: 0,
max: 500,
values: [ 0, 100 ],
slide: function( event, ui ) { $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
}
});
$("#amount").val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );
});
</script>
<script type="text/javascript">
$(".quick_links_panel li").mouseenter(function(){
$(this).children(".mp_tooltip").animate({left:-92,queue:true});
$(this).children(".mp_tooltip").css("visibility","visible");
$(this).children(".ibar_login_box").css("display","block");
});
$(".quick_links_panel li").mouseleave(function(){
$(this).children(".mp_tooltip").css("visibility","hidden");
$(this).children(".mp_tooltip").animate({left:-121,queue:true});
$(this).children(".ibar_login_box").css("display","none");
});
$(".quick_toggle li").mouseover(function(){
$(this).children(".mp_qrcode").show();
});
$(".quick_toggle li").mouseleave(function(){
$(this).children(".mp_qrcode").hide();
});

// 元素以及其他一些变量
var eleFlyElement = document.querySelector("#flyItem"), eleShopCart = document.querySelector("#shopCart");
var numberItem = 0;
// 抛物线运动
var myParabola = funParabola(eleFlyElement, eleShopCart, {
speed: 400, //抛物线速度
curvature: 0.0008, //控制抛物线弧度
complete: function() {
eleFlyElement.style.visibility = "hidden";
eleShopCart.querySelector("span").innerHTML = ++numberItem;
}
});
// 绑定点击事件
if (eleFlyElement && eleShopCart) {

[].slice.call(document.getElementsByClassName("btnCart")).forEach(function(button) {
button.addEventListener("click", function(event) {
// 滚动大小
var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft || 0,
scrollTop = document.documentElement.scrollTop || document.body.scrollTop || 0;
eleFlyElement.style.left = event.clientX + scrollLeft + "px";
eleFlyElement.style.top = event.clientY + scrollTop + "px";
eleFlyElement.style.visibility = "visible";

// 需要重定位
myParabola.position().move();
});
});
}
</script>
-->
<html>
<head>
    <title>甜品 | 西西弗斯咖啡屋</title>
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
            <li class="active">甜品</li>
        </ol>
    </div>
</div>
<div class="products">
    <div class="container">
        <div class="col-md-4 products-left">
            <div class="categories animated wow slideInUp" data-wow-delay=".5s" style="margin-top: 0">
            <h3>菜单</h3>
            <ul class="cate">
                <li><a href="furniture.html">季度精选</a> <span>(15)</span></li>
                <li><a href="furniture.html">全部</a> <span>(16)</span></li>
                <ul>
                    <li><a href="furniture.html">烘焙</a> <span>(2)</span></li>
                    <li><a href="furniture.html">蛋糕</a> <span>(5)</span></li>
                    <li><a href="furniture.html">酸奶</a> <span>(0)</span></li>
                    <li><a href="furniture.html">早安新一天</a> <span>(1)</span></li>
                </ul>
            </ul>
        </div>
        <div class="new-products animated wow slideInUp" data-wow-delay=".5s">
            <h3>新品上市</h3>
            <div class="new-products-grids">
                <div class="new-products-grid">
                    <div class="new-products-grid-left">
                        <a href="single.jsp"><img src="images/新品1.jpg" alt=" " class="img-responsive"/></a>
                    </div>
                    <div class="new-products-grid-right">
                        <h4><a href="single.jsp">法式闪电泡芙</a></h4>
                        <div class="rating">
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="simpleCart_shelfItem new-products-grid-right-add-cart">
                            <p><span class="item_price">￥15.00</span><a href="javascript:;" id="add1" class="add_cart_large btnCart">加入购物车</a>
                            </p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="new-products-grid">
                    <div class="new-products-grid-left">
                        <a href="single.jsp"><img src="images/新品2.jpg" alt=" " class="img-responsive"/></a>
                    </div>
                    <div class="new-products-grid-right">
                        <h4><a href="single.jsp">浓醇三重黑巧克力蛋糕</a></h4>
                        <div class="rating">
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/1.png" alt=" " class="img-responsive">
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="simpleCart_shelfItem new-products-grid-right-add-cart">
                            <p><span class="item_price">￥25.00</span><a href="javascript:;" id="add2" class="add_cart_large btnCart">加入购物车</a>
                            </p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="new-products-grid">
                    <div class="new-products-grid-left">
                        <a href="single.jsp"><img src="images/新品3.jpg" alt=" " class="img-responsive"/></a>
                    </div>
                    <div class="new-products-grid-right">
                        <h4><a href="single.jsp">香浓巧克力麦芬</a></h4>
                        <div class="rating">
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/2.png" alt=" " class="img-responsive">
                            </div>
                            <div class="rating-left">
                                <img src="images/1.png" alt=" " class="img-responsive">
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="simpleCart_shelfItem new-products-grid-right-add-cart">
                            <p><span class="item_price">￥28.00</span><a href="javascript:;" id = "add3" class="add_cart_large btnCart">加入购物车</a>
                            </p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <div class="men-position animated wow slideInUp" data-wow-delay=".5s">
            <a href="single.jsp"><img src="images/星级烘焙.png" alt=" " class="img-responsive"/></a>
        </div>
    </div>
    <div class="col-md-8 products-right">
        <div class="products-right-grid">
            <div class="products-right-grids-position animated wow slideInRight" data-wow-delay=".5s">
                <img src="images/甜品主页1.jpg" alt=" " class="img-responsive"/>
                <div class="products-right-grids-position1">
                    <h4>不只是甜品<br>更是一种生活方式</h4>
                    <p>灵感源自美国西海岸饮食文化，精选来自世界各地的高品质原料，造型有趣，始于经典又融入现代流行元素，每一款都藏着小转折和小惊喜。
                        气息香醇可人，轻咬一口，满口酥软与幸福感，唤醒夏日的慵懒，给味蕾带来层层惊喜。</p>
                </div>
            </div>
        </div>
        <%--菜单内容-JS生成--%>
        <div class="products-right-grids-bottom animated wow zoomIn" id="products-right-grids-bottom"
             data-wow-delay=".5s" style="margin-top: 42px"></div>
    </div>
    <div class="clearfix"></div>
</div>
</div>

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
<!-- //footer -->

<!--右侧贴边导航quick_links.js控制-->
<!--
<div class="mui-mbar-tabs">
	<div class="quick_link_mian">
		<div class="quick_links_panel">
			<div id="quick_links" class="quick_links">
				<li>
					<a href="#" class="my_qlinks"><i class="setting"></i></a>
					<div class="ibar_login_box status_login">
						<div class="avatar_box">
							<p class="avatar_imgbox"><img src="images/portrait.jpg" /></p>
							<ul class="user_info">
								<li>用户名：Hu</li>
								<li>级&nbsp;别：普通会员</li>
							</ul>
						</div>
						<div class="login_btnbox">
							<a href="#" class="login_order">我的订单</a>
							<a href="#" class="login_favorite">我的收藏</a>
						</div>
						<i class="icon_arrow_white"></i>
					</div>
				</li>
				<li id="shopCart">
					<a href="#" class="message_list" ><i class="message"></i><div class="span">购物车</div><span class="cart_num">1</span></a>
				</li>
				<li>
					<a href="#" class="history_list"><i class="view"></i></a>
					<div class="mp_tooltip" style=" visibility:hidden;">我的资产<i class="icon_arrow_right_black"></i></div>
				</li>
				<li>
					<a href="#" class="mpbtn_histroy"><i class="zuji"></i></a>
					<div class="mp_tooltip">我的足迹<i class="icon_arrow_right_black"></i></div>
				</li>
				<li>
					<a href="#" class="mpbtn_wdsc"><i class="wdsc"></i></a>
					<div class="mp_tooltip">我的收藏<i class="icon_arrow_right_black"></i></div>
				</li>
				<li>
					<a href="#" class="mpbtn_recharge"><i class="chongzhi"></i></a>
					<div class="mp_tooltip">我要充值<i class="icon_arrow_right_black"></i></div>
				</li>
			</div>
			<div class="quick_toggle">
				<li>
					<a href="#"><i class="kfzx"></i></a>
					<div class="mp_tooltip">客服中心<i class="icon_arrow_right_black"></i></div>
				</li>
				<li>
					<a href="#none"><i class="mpbtn_qrcode"></i></a>
					<div class="mp_qrcode" style="display:none;"><img src="images/qrcode.png" width="130" height="150" /><i class="icon_arrow_white"></i></div>
				</li>
				<li><a href="#top" class="return_top"><i class="top"></i></a></li>
			</div>
		</div>
		<div id="quick_links_pop" class="quick_links_pop hide"></div>
	</div>
</div>
-->


</body>
</html>