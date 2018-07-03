<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="./JS/jquery.min.js"></script>
<link href="./CSS/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="./JS/bootstrap.min.js"></script>
<style>
    body{
        padding-top: 70px;
    }
</style>
<html>
<head>
    <title>欢迎光临</title>
</head>
<body>
<nav class="nav navbar-default navbar-fixed-top">
    <button onclick="window.location='login.jsp'" name="openLoginBtn" class="btn btn-success">登陆</button>
    <button onclick="window.location='register.html'" name="openRegisterBtn" class="btn btn-success">注册</button>
</nav>

    <a href="getSession.jsp">getSession</a>
</body>
</html>
