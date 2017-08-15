<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../styles/style.css">
    <link rel="stylesheet" href="../../styles/stylemenu.css">
    <title>Groups</title>
</head>
<body>
<c:set var="local" value="${sessionScope.local}"/>
<jsp:include page="fragments/menu.jsp"/>

<div id="mydiv">
    <form action="inote" method="get" class="search">
        <input type="hidden" name="command" value="group"/>
        <select name="gname" class="input" id="add">
            <option selected disabled>${local.getValue('select.group')}</option>
            <c:forEach var="group" items="${groups}">
                <option>${group}</option>
            </c:forEach>
        </select>
        <input type="submit" value="${local.getValue('send')}"/>
    </form>
</div>
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
                <td><a href="?id=${user.id}&command=view"><img src="../img/view.png"></a></td>
            </tr>
        </c:forEach>
    </table></center>
</div>
</body>
</html>
