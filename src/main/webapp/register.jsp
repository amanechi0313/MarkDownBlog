<%--
  Created by IntelliJ IDEA.
  User: 周汰南
  Date: 26/12/2022
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="userdata" beanName="UserBean" class="Bean.UserBean" scope="page"></jsp:useBean>--%>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="http://localhost:8080/markdownblog/userregister" method="post">
    <label>User ID:</label>
    <input name="userId" type="text"><br>

    <label>User Name:</label>
    <input name="userName" type="text"><br>

    <label>User Password:</label>
    <input name="userPassword" type="password"><br>

    <input type="submit">
</form>
<script>

</script>
</body>
</html>