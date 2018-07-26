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
<link href="CSS/checkOut.css" rel="stylesheet">
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
    $(function() {
         var output = "<div id=\"checkoutGoodsList\" class=\"checkout-goods-box\">\n" +
             "                <div class=\"xm-box\">\n" +
             "                    <div class=\"box-hd\">\n" +
             "                        <h2 class=\"title\">确认订单信息</h2>\n" +
             "                        <h2 class=\"title\"><br></h2>\n" +
             "                    </div>\n" +
             "                    <div class=\"box-bd\">\n" +
             "                        <dl class=\"checkout-goods-list\">\n" +
             "                            <dt class=\"clearfix\">\n" +
             "                                <span class=\"col col-1\"><h4>商品名称</h4></span>\n" +
             "                                <span class=\"col col-2\"><h4>购买价格</h4></span>\n" +
             "                                <span class=\"col col-3\"><h4>购买数量</h4></span>\n" +
             "                                <span class=\"col col-4\"><h4>小计</h4></span>\n" +
             "                            </dt>";
         $.ajax({
             url:"order",
             method:"get",
             success:function(result) {
                 var order = $.parseJSON(result);
                 var orderlist =[];
                 orderlist = order.orderlist;
                 console.log(orderlist);

                 for(var i = 0; i<order.length; i++) {
                      output+="<dd class=\"item clearfix\">\n" +
                          "                                <div class=\"item-row\">\n" +
                          "                                    <div class=\"col col-1\">\n" +
                          "                                        <div class=\"g-pic\">\n" +
                          "                                            <img src=\"images/卡布奇诺.jpg\" srcset=\"images/卡布奇诺.jpg\" width=\"40\" height=\"40\"/>\n" +
                          "                                        </div>\n" +
                          "                                        <div class=\"g-info\">\n" +
                          "                                            <a href=\"#\">卡布奇诺</a>\n" +
                          "                                        </div>\n" +
                          "                                    </div>\n" +
                          "\n" +
                          "                                    <div class=\"col col-2\">39元</div>\n" +
                          "                                    <div class=\"col col-3\">1</div>\n" +
                          "                                    <div class=\"col col-4\">39元</div>\n" +
                          "                                </div>\n" +
                          "                            </dd>";
                 }
             }

         });




    });
</script>

<html>
<head>
    <title>购物车 | 西西弗斯咖啡屋</title>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <div class="header-grid">
            <div class="header-grid-left animated wow slideInLeft" data-wow-delay=".5s">
                <%if(hasLogin){%>
                <ul>
                    <li><i class="glyphicon glyphicon-user" aria-hidden="true"></i><a href="information.jsp">个人信息</a></li>
                    <li><i class="glyphicon glyphicon-log-out" aria-hidden="true"></i><a href="register.jsp">退出</a></li>
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
            <div class="logo-nav-left1 animated wow zoomIn" data-wow-delay=".5s">
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
                    <p><a href="javascript:;" class="simpleCart_empty">清空购物车</a></p>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- //header -->
<!-- breadcrumbs -->
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
        <div class="checkout-box-ft">
            <!-- 商品清单 -->
            <form>
            <div id="checkoutGoodsList" class="checkout-goods-box">
                <div class="xm-box">
                    <div class="box-hd">
                        <h2 class="title">确认订单信息</h2>
                        <h2 class="title"><br></h2>
                    </div>
                    <div class="box-bd">
                        <dl class="checkout-goods-list">
                            <dt class="clearfix">
                                <span class="col col-1"><h4>商品名称</h4></span>
                                <span class="col col-2"><h4>购买价格</h4></span>
                                <span class="col col-3"><h4>购买数量</h4></span>
                                <span class="col col-4"><h4>小计</h4></span>
                            </dt>
                            <dd class="item clearfix">
                                <div class="item-row">
                                    <div class="col col-1">
                                        <div class="g-pic">
                                            <img src="images/卡布奇诺.jpg" srcset="images/卡布奇诺.jpg" width="40" height="40"/>
                                        </div>
                                        <div class="g-info">
                                            <a href="#">卡布奇诺</a>
                                        </div>
                                    </div>

                                    <div class="col col-2">39元</div>
                                    <div class="col col-3">1</div>
                                    <div class="col col-4">39元</div>
                                </div>
                            </dd>
                            <dd class="item clearfix">
                                <div class="item-row">
                                    <div class="col col-1">
                                        <div class="g-pic">
                                            <img src="images/美式咖啡.jpg" srcset="images/美式咖啡.jpg" width="40" height="40"/>
                                        </div>
                                        <div class="g-info">
                                            <a href="#">美式咖啡 </a>
                                        </div>
                                    </div>

                                    <div class="col col-2">49元</div>
                                    <div class="col col-3">1</div>
                                    <div class="col col-4">49元</div>
                                </div>
                            </dd>
                            <dd class="item clearfix">
                                <div class="item-row">
                                    <div class="col col-1">
                                        <div class="g-pic">
                                            <img src="images/焦糖玛奇朵.jpg" srcset="images/焦糖玛奇朵.jpg" width="40"
                                                 height="40"/>
                                        </div>
                                        <div class="g-info">
                                            <a href="#">
                                                焦糖玛奇朵 </a>
                                        </div>
                                    </div>

                                    <div class="col col-2">39元</div>
                                    <div class="col col-3">4</div>
                                    <div class="col col-4">156元</div>
                                </div>
                            </dd>
                        </dl>
                        <div class="checkout-count clearfix">
                            <!-- checkout-count-extend -->
                            <div class="checkout-price">
                                <p class="checkout-total">应付总额：<span><strong id="totalPrice">244</strong>元</span></p>
                            </div>
                            <!--  -->
                        </div>
                    </div>
                    <!-- 商品清单 END -->
                    <input type="hidden" id="couponType" name="Checkout[couponsType]">
                    <input type="hidden" id="couponValue" name="Checkout[couponsValue]">
                    <div class="checkout-confirm">

                        <a href="checkout.jsp" class="btn btn-lineDakeLight btn-back-cart">返回购物车</a>
                        <a href="order.jsp" input type="submit" class="btn btn-primary" id="checkoutToPay">立即下单</a>
                    </div>
                </div>
            </div>

            </form>

        </div>

        <script src="JS/base.min.js"></script>
        <script type="text/javascript" src="JS/address_all.js"></script>
        <script type="text/javascript" src="JS/checkout.min.js"></script>
    </div>

    <div class="clearfix"></div>
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
                                                                                                   alt=" " class="img-responsive"/></a>
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
</body>
</html>