<%--
  Created by IntelliJ IDEA.
  User: nazarov
  Date: 04.08.17
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose subscriptions</title>
</head>
<body>
<form action="/usersubscribeconfirm" method="get">
    <table id="issuesList" border="1">
        <th>
            <td>Choose subscriptions from list</td>
        </th>
        <c:forEach items="${issues}" var="issue">
            <tr>
                <td><input type="checkbox" name="selectedItems" value="${issue.id}"/></td>
                <td>${issue.name}</td>
                <td>${issue.cost}</td>
                <td>${issue.weeksPeriod} weeks</td>
                <td><select name="subscriptionLength${issue.id}" >
                    <option value="oneMonth">1 month</option>
                    <option value="threeMonths">3 months</option>
                    <option value="sixMonths">6 month</option>
                    <option value="year">Year</option>
                </select></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input type="submit" value="Confirm">
</form>
</body>
</html>
