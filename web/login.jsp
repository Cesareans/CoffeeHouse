<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script src="JS/jquery.min.js"></script>
<link href="CSS/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="JS/bootstrap.min.js"></script>

<%
    Cookie[] cookies = request.getCookies();
    String usertel ="",password = "";
    for(Cookie cookie:cookies){
        if(cookie.getName().equals("usertel"))
            usertel = cookie.getValue();
        else if(cookie.getName().equals("password"))
            password = cookie.getValue();
    }
%><!--从cookie中获取用户端保存的登陆信息-->
<script>
    $(function(){
       $("#loginBtn").bind("click" , function(){
           var usertel = document.getElementById("usertel").value;
           if(usertel==null || usertel === "") {
               alert("用户名不能为空");
               document.getElementById("usertel").focus();
               return;
           }
           var password = document.getElementById("password").value;
           if(password == null || password===""){
               alert("密码不能为空");
               document.getElementById("password").focus();
               return;
           }
           $.ajax({
               url:"/login",
               data:{
                   "usertel":usertel,
                   "password":password
               },
               success:function (result) {
                   console.log("success");
                   if(result == null || result === "fail"){
                       $("#return").html("<font color=\"red\">登陆失败，请重试</font>");
                   }else if(result ==="success") {
                       window.location="index.jsp";
                   }
               }
           });
       });
       $("#registerBtn").bind("click" , function () {
            window.location.assign("register.html");
        });
    });
</script>

<html>
  <head>
    <title>欢迎光临</title>
  </head>
  <body>
    <h1 align="center">登陆</h1>
    <form name="loginForm" method="post" id="loginForm">
        <table class="table" width="300" align="center" border="0" cellspacing="10" cellpadding="0">
            <tr>
                <td width="30%" align="right">
                    <span class="text-muted">用户名：</span>
                </td>
                <td width="70%" align="center">
                    <input size="30%" type="text" name="usertel" id="usertel" class="form-control" value="<%=usertel%>">
                </td>
            </tr>
            <tr>
                <td width="30%" align="right">
                    <span class="text-muted">密码：</span>
                </td>
                <td width="70%" align="center">
                    <input size="30%" type="password" name="password" id="password" class="form-control" value="<%=password%>">
                </td>
            </tr>
            <tr>
                <td colspan="2" class="dl">
                    <table width="200" align="center">
                        <tr>
                            <td width="50%" align="center">
                                <button type="button" name="loginBtn" id="loginBtn" class="btn btn-success">登陆</button>
                            </td>
                            <td width="50%" align="center">
                                <button type="button" name="registerBtn" id="registerBtn" class="btn btn-success">注册</button>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <div id="return"></div>
                </td>
            </tr>
        </table>
    </form>
  </body>
</html>

