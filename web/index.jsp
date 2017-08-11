<%--
  Created by IntelliJ IDEA.
  User: nazarov
  Date: 26.07.17
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome!</title>
  </head>
  <body>
  <form action="/login" method="post">
      Name:<input name="username"/><br/><br/>
      Password:<input type="password" name="userpass"/><br/><br/>
      <input type="submit" value="Login"/>
      <a href="/registration.jsp" >Registration</a>
  </form>
  </body>
</html>
