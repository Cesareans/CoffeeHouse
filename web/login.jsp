<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function login() {
        var username = document.getElementById("username").value;
        if(username==null || username === "") {
            alert("用户名不能为空");
            document.getElementById("username").focus();
            return;
        }
        var password = document.getElementById("password").value;
        if(password == null || password===""){
            alert("密码不能为空");
            document.getElementById("password").focus();
            return;
        }
        document.loginForm.submit();
    }
    function register() {
        document.loginForm.method="GET";
        document.loginForm.action="register.jsp";
        document.loginForm.submit();
    }
</script>
<html>
  <head>
    <title>欢迎光临</title>
  </head>
  <body>
    <h1 align="center">登陆</h1>
    <hr>
    <form action="getSession.jsp" name="loginForm" method="post" id="loginForm">
        <table align="center" width="300" border="0" cellspacing="10" cellpadding="0">
            <tr>
                <td align="right" class="dl">用户名：</td>
                <td width="200" align="center">
                    <input type="text" name="username" id="username">
                </td>
            </tr>
            <tr>
                <td align="right" class="dl">密码：</td>
                <td width="200" align="center">
                    <input type="password" name="password" id="password">
                </td>
            </tr>
            <tr>
                <td width="200" align="center">
                    <button onclick="login()" name="submitBtn">登陆</button>
                </td>
                <td width="200" align="center">
                    <button onclick="register()" name="registerBtn">注册</button>
                </td>
            </tr>
        </table>
    </form>
  </body>
</html>
