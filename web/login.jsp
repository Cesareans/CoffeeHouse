<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var xmlhttp;
    function submitToLogin() {
        console.log("prepare to login");
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
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = checkLogin;
        xmlhttp.open("post" , "loginServlet" , true);
        xmlhttp.setRequestHeader("Content-Type" , "application/x-www-form-urlencoded");
        xmlhttp.send("username=" + username + "&password=" + password);
    }
    function checkLogin() {
        console.log("xmlhttp.readyState = " + xmlhttp.readyState + " &&  xmlhttp.status = " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200){
            var response = xmlhttp.responseText;
            if(response == null || response === "fail"){
                document.getElementById("return").innerHTML = "<font color=\"red\">登陆失败，请重试</font>";
            }else if(response ==="success") {
                window.location="index.jsp";
            }
        }
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
                <td colspan="2">
                    <div id="return"></div>
                </td>
            </tr>
        </table>
    </form>
  </body>
</html>
