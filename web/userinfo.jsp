<%--
  Created by IntelliJ IDEA.
  User: nazarov
  Date: 07.08.17
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.username} info page</title>
    <table border="1">
        <tr>
            <td>Username</td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td>First name</td>
            <td>${user.firstName}</td>
        </tr>
        <tr>
            <td>Last name</td>
            <td>${user.lastName}</td>
        </tr>
    </table>
    <br>
    <table border="1">
        <c:forEach items="${subscriptions}" var="subscription">
            <tr>
                <td>${subscription.issue.name}</td>
                <td>${subscription.subscriptionDate}</td>
            </tr>
        </c:forEach>
    </table>
</head>
<body>

</body>
</html>
