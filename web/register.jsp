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
    $(function () {
        layui.use("layer", function () {
            var layer = layui.layer;
        });
        var usertelok = false;
        var pswok = false;
        var confirmpswok = false;
        $(function () {
            $("#usertel").bind("blur", function () {
                var usertelval = $("#usertel").val();
                if (usertelval == null || usertelval === "" || usertelval === undefined) {
                    layer.tips("请输入用户名", "#usertel", {time: 1000});
                    usertelok = false;
                }
                else {
                    var myreg = /^1[34578]\d{9}$/;
                    if (!myreg.test(usertelval)) {
                        layer.tips("用户名必须为手机号码", "#usertel", {time: 1000});
                        usertelok = false;
                    }
                    else {
                        $.ajax({
                            url: "checktel",
                            data: {
                                "usertel": usertelval
                            },
                            success: function (result) {
                                if (result === "false") {
                                    layer.tips("用户名已经存在", "#usertel", {time: 1000});
                                    usertelok = false;
                                }
                                else if (result === "true") {
                                    layer.tips("用户名可以使用", "#usertel", {time: 1000});
                                    usertelok = true;
                                }
                            }
                        });
                    }
                }
            });
            $("#password").bind("blur", function () {
                var password = document.getElementById("password").value;
                var passwordRegx = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
                if (password == null || password === "" || password.undefined) {
                    layer.tips("请输入密码", "#password", {time: 1000});
                    pswok = false;
                }
                else if (!passwordRegx.test(password)) {
                    layer.tips("密码应为6-16位数字与字母的组合", "#password", {time: 1000});
                    pswok = false;
                }
                else {
                    layer.tips("密码符合要求", "#password", {time: 1000});
                    pswok = true;
                }
            });
            $("#confirmpsw").bind("blur", function () {
                var password = document.getElementById("password").value;
                var confirmpsw = document.getElementById("confirmpsw").value;
                if (password == null || password === "" || password.undefined) {
                    layer.tips("请输入密码", "#password", {time: 1000});
                    confirmpswok = false;
                }
                else if (password !== confirmpsw) {
                    layer.tips("密码不一致", "#confirmpsw", {time: 1000});
                    confirmpswok = false;
                }
                else {
                    layer.tips("密码一致", "#confirmpsw", {time: 1000});
                    confirmpswok = true;
                }
            });
            $('#registerBtn').bind("click", function () {
                if (usertelok && pswok && confirmpswok) {
                    document.registerForm.action = "/register";
                    document.registerForm.method = "post";
                    document.registerForm.submit();
                } else {
                    alert("注册失败，请检查信息的有效性");
                }
            });
        });
    });
</script>

<style>
    .login-form-grids input[type="button"] {
        outline: none;
        border: none;
        padding: 10px 0;
        font-size: 1em;
        color: #fff;
        display: block;
        width: 100%;
        background: #FFC229;
        margin: 1.5em 0 0;
    }
</style>

<html>
<head><title>注册 | 西西弗斯咖啡屋</title></head>
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
            <li class="active">注册页面</li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- register -->
<div class="login">
    <div class="container">
        <h3 class="animated wow zoomIn" data-wow-delay=".5s">注册个人账号</h3>
        <p class="est animated wow zoomIn" data-wow-delay=".5s">心情惬意，来杯咖啡吧</p>
        <div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">
            <form name="registerForm" id="registerForm">
                <input type="text" placeholder="手机号码" required=" " name="usertel" id="usertel">
                <input type="password" placeholder="密码" required=" " name="password" id="password">
                <input type="password" placeholder="确认密码" required=" " name="confirmpsw" id="confirmpsw">
                <input type="button" value="注册" id="registerBtn">
            </form>
        </div>
    </div>
</div>
<!-- //register -->
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
                            href="mailto:cesarean@foxmail.com">cesarean@foxmail.com
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