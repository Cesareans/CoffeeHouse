<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <h1 align="center">注册</h1>
    <hr>
    <form action="register" method="post">
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
                <td colspan="2" width="200" align="center">
                    <input type="submit" align="center" name="submitBtn" value="注册">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
