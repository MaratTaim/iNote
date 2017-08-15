<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/style.css">
    <link rel="stylesheet" href="../../styles/stylemenu.css">
    <title>Birthday</title>
</head>
<body>
<c:set var="local" value="${sessionScope.local}"/>
<jsp:include page="fragments/menu.jsp"/>
<div id="secdiv">
    <center><table>
        <tr>
            <th>${local.getValue('surname')}</th>
            <th>${local.getValue('name')}</th>
            <th>${local.getValue('address')}</th>
            <th>${local.getValue('birthday')}</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach var="user" items="${list}">
            <tr>
                <td hidden>${user.id}</td>
                <td>${user.surname}</td>
                <td>${user.name}</td>
                <td>${user.address}</td>
                <td>${user.birthday}</td>
                <td><a href="?id=${user.id}&command=view"><img src="../../img/view.png"></a></td>
            </tr>
        </c:forEach>
    </table></center>
</div>
</body>
</html>
