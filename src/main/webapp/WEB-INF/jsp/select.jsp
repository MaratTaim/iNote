<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/taglib.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="styles/style.css"/>
    <link rel="stylesheet" href="styles/stylemenu.css" type="text/css"/>
    <title>iNote</title>
</head>
<body>
<c:set var="local" value="${sessionScope.local}"/>
<jsp:include page="fragments/menu.jsp"/>

<div id="mydiv">
    <form action="inote" method="get" class="search">
        <input type="hidden" name="command" value="search">
        <input type="search" name="search" placeholder="${local.getValue("search")}" class="input"/>
        <input type="submit" name="action" value="search" class="submit"/>
    </form>
</div>

<div id="secdiv">
    <center><table>
        <tr>
            <th>${local.getValue("surname")}</th>
            <th>${local.getValue("name")}</th>
            <th>${local.getValue("address")}</th>
            <th>${local.getValue("birthday")}</th>
            <th>${local.getValue("mail")}</th>
            <th>${local.getValue("group")}</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach var="user" items="${list}">
            <tr>
                <td hidden>${user.id}</td>
                <td>${user.surname}</td>
                <td>${user.name}</td>
                <td>${user.address}</td>
                <td>${user.birthday}</td>
                <td>${user.mail}</td>
                <td>
                    <select name="types">
                        <c:forEach var="group" items="${user.group}">
                            <option value="${group}">${group}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><a href="?id=${user.id}&command=view"><img src="../../img/view.png"></a></td>

                <c:if test="${sessionScope.client.equals('ADMINISTRATOR')}">
                <td><a href="?id=${user.id}&command=delete"><img src="../../img/delete.png"></a></td>
                <td><a href="?id=${user.id}&command=edit"><img src="../../img/pencil.png"></a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table></center>
    <p></p>
    <tag:localTag local="US" uriImg="../../img/en.png" value="English">
    </tag:localTag>
    <tag:localTag local="RU" uriImg="../../img/ru.png" value="Русский">
    </tag:localTag>
</div>
</body>
</html>