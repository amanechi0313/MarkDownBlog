<%--
  Created by IntelliJ IDEA.
  User: 周汰南
  Date: 27/12/2022
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/markdownblog/usermodify" method="post">
    <% String userId = (String)session.getAttribute("login"); %>
    <label>User ID:</label>
    <input placeholder=<%=userId%> disabled><br>

    <label>New User Name:</label>
    <input name="userName" type="text"><br>

    <label>New User Password:</label>
    <input name="userPassword" type="password"><br>

    <input type="submit">
</form>

</body>
</html>
