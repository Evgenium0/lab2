<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>Welcome to the Home Page!</h1>
<p>
    <% String username = (String)session.getAttribute("username");
        if (username != null) { %>
    Hello, <%= username %>! <a href="logout">Logout</a>
    <% } else { %>
    You are not logged in. <a href="login.jsp">Login</a>
    <% } %>
</p>
</body>
</html>