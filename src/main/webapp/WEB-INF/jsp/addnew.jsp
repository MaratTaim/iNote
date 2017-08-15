<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% response.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <link rel="stylesheet" href="../../styles/style.css">
    <link rel="stylesheet" href="../../styles/stylemenu.css">
    <title>Add new User</title>
</head>
<body>
<c:set var="local" value="${sessionScope.local}"/>
<jsp:include page="fragments/menu.jsp"/>
<center>
    <div id="secdiv">
        <c:if test="${sessionScope.client.equals('ADMINISTRATOR')}">
            <form id="user" method="post" action="inote">
                <input type="hidden" name="command" value="add"/>

                <h3>${local.getValue('contact')}:</h3>
                <input type="hidden" name="id" value="${user.id}">
                <input type="text" placeholder="${local.getValue('surname')}" name="surname" maxlength="45">
                <input type="text" placeholder="${local.getValue('name')}" name="name" maxlength="45" required>
                <input type="text" placeholder="${local.getValue('address')}" name="address" maxlength="60">
                <input type="date" placeholder="${local.getValue('birthday')}" name="birthday" required>

                <h3>${local.getValue('contacts')}:</h3>
                <input type="text"  pattern="[\w+\-.]{1,}@.+\.(.+)" placeholder="${local.getValue('mail')}" maxlength="45" name="${contTypes[0].name()}" >
                <input type="text" placeholder="${local.getValue('phone')}" maxlength="45" name="${contTypes[1].name()}" >
                <input type="text" placeholder="${local.getValue('home.phone')}" maxlength="45" name="${contTypes[2].name()}" >
                <input type="text" placeholder="${local.getValue('skype')}" maxlength="45" name="${contTypes[3].name()}" >
                <input type="text" placeholder="${local.getValue('icq')}" maxlength="45" name="${contTypes[4].name()}" >
                <input type="text" placeholder="${local.getValue('mobile')}" maxlength="45" name="${contTypes[5].name()}" >

                <h3>${local.getValue('group')}:</h3>
                <input type="text" placeholder="${local.getValue('few.groups')}" name="groups"
                       maxlength="111" value="${user.stringGroups()}">
                <p></p>
                <input id="add" type="submit" value="${local.getValue('save')}">
                <input id="back" onclick="window.history.back()" value="${local.getValue('cancel')}">
            </form>
        </c:if>
        <c:if test="${sessionScope.client.equals('GUEST') || sessionScope.client.equals(null)}">
            <h2 style="color: #f60">${local.getValue('no.access')}</h2>
        </c:if>
    </div>
</center>
</body>
</html>
