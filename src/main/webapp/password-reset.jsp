<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/6/2024
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        response.getWriter().print(errorMessage);
    }
%>
<html>
<head>
    <title>Password Reset</title>
</head>
<body>
<form method="post" action="/reset-password">
    <h1>Password Reset Page</h1><br><br>
    Token: <input type="text" name="token"><br><br>
    Password: <input type="text" name="password"><br><br>
    Repeat Password: <input type="text" name="repeat-password"><br><br>
    <input type="submit" value="reset"><br><br>
</form>
</body>
</html>
