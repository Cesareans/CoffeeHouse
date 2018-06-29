<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function submitToLogin() {
        console.log("prepare to login");
        var username = document.getElementById("username").value;
        if(username==null || username === "") {
            alert("用户名不能为空");
            document.getElementById("username").focus();
            return false;
        }
        var password = document.getElementById("password").value;
        if(password == null || password===""){
            alert("密码不能为空");
            document.getElementById("password").focus();
            return false;
        }
        document.loginForm.action = "loginServlet";
        document.loginForm.submit();
    }
    function submitToRegister() {
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
    <form name="loginForm" method="post" id="loginForm">
        <table  width="300" align="center" border="0" cellspacing="10" cellpadding="0">
            <tr>
                <td width="30%" align="right">
                    用户名：
                </td>
                <td width="70%" align="center">
                    <input size="30%" type="text" name="username" id="username">
                </td>
            </tr>
            <tr>
                <td width="30%" align="right">密码：</td>
                <td width="70%" align="center">
                    <input size="30%" type="password" name="password" id="password">
                </td>
            </tr>
            <tr>
                <td colspan="2" class="dl">
                    <table width="200" align="center">
                        <tr>
                            <td width="50%" align="center">
                                <button onclick="submitToLogin()" name="loginBtn">登陆</button>
                            </td>
                            <td width="50%" align="center">
                                <button onclick="submitToRegister()" name="registerBtn">注册</button>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <div id="return">
                    <td>

                    </td>
                </div>
            </tr>
        </table>
    </form>
  </body>
</html>
