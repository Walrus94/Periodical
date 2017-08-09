<%--
  Created by IntelliJ IDEA.
  User: nazarov
  Date: 03.08.17
  Time: 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Administration Page</title>
</head>
<body>
        <table id="userstable" border="1" style="float: left">
            <thead>
                <tr>User list</tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td><form action="/userinfo" method="post">
                        <input type="hidden" name="userid" value="${user.id}">
                        <input type="submit" value="Show information">
                    </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table id="issuestable" border=1" style="float: left">
            <thead>
                <tr>Issues list</tr>
            </thead>
            <c:forEach items="${issues}" var="issue">
                <tr>
                    <td>${issue.name}</td>
                    <td>${issue.monthlyCost}</td>
                    <td><form action="/deleteissue" method="post">
                            <input type="hidden" name="issueid" value="${issue.id}">
                            <input type="submit" value="X">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <form action="/addissue" method="post">
        Issue name:<input name="issue"/><br/><br/>
        Subscription cost:<input type="number" step="0.01" name="cost"/><br/><br/>
        <input type="submit" value="Add Issue">
    </form>
    <br>
    <form action="/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
