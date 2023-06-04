<%--
  Created by IntelliJ IDEA.
  User: Zakhar Zakharenko
  Date: 18.04.2023
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<form action="login" method="post">
  <%--@declare id="username"--%><%--@declare id="password"--%><label for="username">Username:</label>
  <input type="text" name="username" required>
  <br>
  <label for="password">Password:</label>
  <input type="password" name="password" required>
  <br>
  <input type="submit" value="Login">
</form>
</body>
</html>
