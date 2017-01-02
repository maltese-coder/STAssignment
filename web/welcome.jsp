<%--
  Created by IntelliJ IDEA.
  User: Matthew
  Date: 28-Dec-16
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Menu</title>
</head>
<body>

<%
    if(session.getAttribute("username")== null)
    {
        response.sendRedirect("index.jsp");
    }
%>
    Welcome ${username}!!

    <form action="Logout">
        <input type="submit" value="Log Out">

    </form>
</body>
</html>
