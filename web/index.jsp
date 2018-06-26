<%--
  Created by IntelliJ IDEA.
  User: cesare
  Date: 25/06/2018
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎光临</title>
  </head>
  <body>
    <h1 align="center">登陆</h1>
    <hr>
    <form action="login" method="post">
        <table align="center" width="300" border="0" cellspacing="10" cellpadding="0">
            <tr>
                <td align="right" class="dl">username：</td>
                <td width="200" align="center">
                    <input type="text" name="username" id="username">
                </td>
            </tr>
            <tr>
                <td align="right" class="dl">password：</td>
                <td width="200" align="center">
                    <input type="password" name="password" id="password">
                </td>
            </tr>
            <tr>
                <td width="200" align="center">
                    <input type="submit" align="center" name="submitBtn" value="登陆">
                </td>
            </tr>
        </table>
    </form>
  </body>
</html>
