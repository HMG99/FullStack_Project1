<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/5/2024
  Time: 11:16 PM
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
    <title>Verification</title>
</head>
<body>
<form method="post" action="/verify">
    <h1>Verification</h1><br><br>
    Verification Code: <input type="text" name="verification-code"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
