<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/5/2024
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%

    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        response.getWriter().print(errorMessage);
    }
%>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="/registration">
    <h1>Registration</h1><br><br>
    name: <input type="text" name="name"><br><br>
    surname: <input type="text" name="surname"><br><br>
    year: <input type="text" name="year"><br><br>
    email: <input type="text" name="email"><br><br>
    password: <input type="text" name="password"><br><br>
    <input type="submit" value="sign-up">
</form>
</body>
</html>

</body>
</html>
