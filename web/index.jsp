<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎光临</title>
</head>
<body>
    <table align="right" width="120" border="0"  cellpadding="0" cellspacing="10">
        <tr>
            <td>
                <button onclick="window.location='login.jsp'" name="openLoginBtn">登陆</button>
            </td>
            <td>
                <button onclick="window.location='register.jsp'" name="openRegisterBtn">注册</button>
            </td>
        </tr>
        <tr>
            <td colspan="2"><a href="getSession.jsp">getSession</a> </td>
        </tr>
    </table>
</body>
</html>
