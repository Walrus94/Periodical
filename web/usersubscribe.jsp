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
    <form action="/usersubscribeconfirm" method="get">
        <table id="issuesList" border="1">
            <c:forEach items="${issues}" var="issue">
                <tr>
                    <td><input type="checkbox" name="selectedItems" value="${issue.id}"/></td>
                    <td>${issue.name}</td>
                    <td>${issue.monthlyCost}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Confirm">
    </form>
</head>
<body>

</body>
</html>
