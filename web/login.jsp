<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    Cookie[] cookies = request.getCookies();
    String usertel ="",password = "";
    for(Cookie cookie:cookies){
        if(cookie.getName().equals("usertel"))
            usertel = cookie.getValue();
        else if(cookie.getName().equals("password"))
            password = cookie.getValue();
    }
%>
<script src="JS/jquery.min.js"></script>
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
    <hr>
    <form name="loginForm" method="post" id="loginForm">
        <table  width="300" align="center" border="0" cellspacing="10" cellpadding="0">
            <tr>
                <td width="30%" align="right">用户名：</td>
                <td width="70%" align="center">
                    <input size="30%" type="text" name="usertel" id="usertel" value="<%=usertel%>">
                </td>
            </tr>
            <tr>
                <td width="30%" align="right">密码：</td>
                <td width="70%" align="center">
                    <input size="30%" type="password" name="password" id="password" value="<%=password%>">
                </td>
            </tr>
            <tr>
                <td colspan="2" class="dl">
                    <table width="200" align="center">
                        <tr>
                            <td width="50%" align="center">
                                <button type="button" name="loginBtn" id="loginBtn">登陆</button>
                            </td>
                            <td width="50%" align="center">
                                <button type="button" name="registerBtn" id="registerBtn">注册</button>
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
