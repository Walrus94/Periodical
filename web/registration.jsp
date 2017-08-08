<%--
  Created by IntelliJ IDEA.
  User: nazarov
  Date: 07.08.17
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/registration" method="post">
        Login:<input name="username"/><br/><br/>
        First name:<input name="firstName"/><br/><br/>
        Last name:<input name="lastName"/><br/><br/>
        Password:<input name="password" type="password"/><br/><br/>
        <input type="submit" value="register"/>
    </form>
</body>
</html>
