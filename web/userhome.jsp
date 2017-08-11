<%--
  Created by IntelliJ IDEA.
  User: nazarov
  Date: 02.08.17
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
    <form action="/userhome">
        <table name="subscriptionsTable" border="1">
            <tr>
                <th>Subscription name</th>
                <th>Subscription date</th>
                <th>Expiration date</th>
            </tr>
            <c:forEach items="${subscriptions}" var="subscription" >
                <tr>
                    <td>${subscription.issue.name}</td>
                    <td>${subscription.subscriptionDate}</td>
                    <td>${subscription.expirationDate}</td>
                    <td>
                        <form action="/unsubscribe" method="post">
                            <input type="hidden" name="subscriptionid" value="${subscription.id}">
                            <input type="submit" value="Unsubscribe">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3">Payment per month:</td>
                <td>${monthlyPayment}</td>
            </tr>
        </table>
    </form>
    <br>
    <form action="/usersubscribe" method="post">
        <input type="submit" value="Subscribe">
    </form>

    <form action="/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
