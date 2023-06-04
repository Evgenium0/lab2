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
    <title>Home Page</title>
</head>
<body>
<h1>Welcome to the Home Page</h1>
<p>You are logged in as: <%= session.getAttribute("username") %></p>
<form action="/logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>