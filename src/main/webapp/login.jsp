<%--
  Created by IntelliJ IDEA.
  User: 周汰南
  Date: 26/12/2022
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="http://localhost:8080/markdownblog/userlogin" method="post">
    <label>User ID:</label>
    <input name="userId" type="text"><br>

    <label>User Password:</label>
    <input name="userPassword" type="password"><br>

    <input type="submit">
</form>
</body>
</html>
