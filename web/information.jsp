<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords" content="café, cafe, coffee shop, 西西弗斯,Sisyphus"/>

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
      rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic'
      rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="CSS/jquery.countdown.css"/>
<link rel="stylesheet" href="./lib/layui/css/layui.css">
<link href="CSS/animate.min.css" rel="stylesheet">
<link href="CSS/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
<link href="CSS/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="./images/favicon.png" rel="shortcut icon"/>
<link href="lib/layui/css/layui.css" rel="stylesheet">

<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="JS/jquery.min.js"></script>
<script src="JS/simpleCart.min.js"></script>
<script type="text/javascript" src="JS/bootstrap-3.1.1.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="JS/wow.min.js"></script>
<script src="JS/bootstrap-3.1.1.min.js"></script>


<style>
    .layui-form-radio > i:hover, .layui-form-radioed > i {
        color: #cb754b;
    }
</style>

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
    $(function () {
        layui.use(["laydate", "form","layer"], function () {
            var layer = layui.layer,laydate = layui.laydate, form = layui.form;
            laydate.render({
                elem: "#birthday",
                showBottom: false,
                max: 0
            });
            $("#email").bind("blur", function () {
                if(checkMail())
                    layer.tips("可以使用", "#email", {time: 1000});
                else
                    layer.tips("邮箱不合法", "#email", {time: 1000});
            });
            $("#updateBtn").bind("click", function () {
                if(!checkMail()) {
                    layer.tips("邮箱不合法", "#email", {time: 1000});
                    return;
                }
                $.ajax({
                    method: "post",
                    url: "userinfo",
                    data: $("#profileForm").serialize(),
                    success: function (result) {
                        if (result == null || result === "false") {
                            layer.tips("个人信息完善失败!请检查信息的合法性!" , "#updateBtn",{
                                tips:3
                            });
                        } else if (result === "true") {
                            layer.tips("个人信息完善成功!" , "#updateBtn",{
                                tips:3
                            });
                        }
                    }
                });
            });
        });
        $.ajax({
            method: "get",
            url: "userinfo",
            success: function (result) {
                var jsonObj = $.parseJSON(result);
                layui.use("form", function () {
                    var form = layui.form;
                    form.val("profileForm", {
                        "username": jsonObj.name,
                        "birthday": jsonObj.birthday,
                        "email": jsonObj.email,
                        "gender": jsonObj.gender
                    });
                });
            }
        });
    });
    function checkMail() {
        var email = document.getElementById("email").value;
        var emailRegx = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*$/;
        return emailRegx.test(email);
    }
</script>

<html>
<head>
    <title>个人信息 | 西西弗斯咖啡屋</title>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <div class="header-grid">
            <div class="header-grid-left animated wow slideInLeft" data-wow-delay=".5s">
                <%if (hasLogin) {%>
                <ul>
                    <li><i class="glyphicon glyphicon-user" aria-hidden="true"></i><a href="information.jsp">个人信息</a>
                    </li>
                    <li><i class="glyphicon glyphicon-log-out" aria-hidden="true"></i><a href="logout">退出</a></li>
                </ul>
                <%} else {%>
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
                            <li><a href="mail.jsp">&nbsp;联系我们</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="header-right animated wow slideInRight" data-wow-delay=".5s" style="margin-top: 25px">

                <div class="cart box_1" style="margin-top: 0">
                    <a href="checkout.jsp" style="color: #d18762">购物车
                        <img src="images/bag.png" alt="" style="margin-top: 0"/>
                    </a>
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
            <li class="active">个人信息</li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- mail -->
<div class="mail animated wow zoomIn" data-wow-delay=".5s">
    <div class="container">
        <div class="mail-grids">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".6s">
                <ul>
                    <li><i class="glyphicon glyphicon-user" aria-hidden="true"></i><a href="information.jsp">个人信息</a>
                    </li>
                    <p><br></p>
                    <li><i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i><a
                            href="information.jsp">历史订单</a></li>
                    <p><br></p>
                    <li><i class="glyphicon glyphicon-cog" aria-hidden="true"></i><a href="information.jsp">其他设置</a>
                    </li>
                </ul>
            </div>

            <!-- /.navbar-collapse -->

            <div class="col-md-8 mail-grid-left animated wow slideInRight" data-wow-delay=".5s">
                <form id="profileForm" class="layui-form layui-form-pane" lay-filter="profileForm">
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">昵称：</label>
                        <div class="layui-input-block">
                            <input id="username" name="username" type="text" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别：</label>
                        <div class="layui-input-block">
                            <input name="gender" type="radio" value="男" title="男">
                            <input name="gender" type="radio" value="女" title="女">
                            <input name="gender" type="radio" value="保密" title="保密">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label for="birthday" class="layui-form-label">生日：</label>
                        <div class="layui-input-block">
                            <input id="birthday" name="birthday" type="text" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label for="email" class="layui-form-label">邮箱：</label>
                        <div class="layui-input-block">
                            <input id="email" name="email" type="text" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item" style="margin-bottom: 0;text-align: center">
                        <button id="updateBtn" name="updateBtn" class="layui-btn" type="button"
                                style="background-color: #cb754b">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- //mail -->


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
<!-- //footer -->
</body>
</html>