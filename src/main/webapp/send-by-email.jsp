<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/5/2024
  Time: 11:52 PM
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
    <title>Token send</title>
</head>
<body>
<form method="post" action="/send-token">
    <h1>Token send page</h1><br><br>
    <p>Please enter your email, so we can send you a token for password reset.</p>
    Your email: <input type="text" name="email"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
