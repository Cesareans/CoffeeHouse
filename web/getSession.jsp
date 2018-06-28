<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
    String username = (String)session.getAttribute("username");
    String password = ((String) session.getAttribute("password"));
%>
<html>
<head>
    <title>getSession</title>
</head>
<body>
    <h1>Database Info</h1>
    <hr>
    <h5>username:<%=username%></h5>
    <h5>password:<%=password%></h5>
</body>
</html>
