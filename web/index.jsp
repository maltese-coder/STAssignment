<%--
  Created by IntelliJ IDEA.
  User: Matthew
  Date: 07-Dec-16
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <h1>Welcome</h1>
  <h4>Sign in</h4>
  <form action="Login" method="POST">
    Enter username : <input type="text" id="username" name="username" ><br>
    Enter password : <input type="password" id = "password" name = "password"><br>
    <input type="submit" id = "loginBtn" value="Log In">
  </form>
  </body>
</html>
