<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% response.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <link rel="stylesheet" href="../../styles/style.css">
    <link rel="stylesheet" href="../../styles/stylemenu.css">
    <title>View User</title>
</head>

<body>
<c:set var="local" value="${sessionScope.local}"/>
<jsp:include page="fragments/menu.jsp"/>

<center>
    <div class="viewst">
        <table>
            <tr>
                <th>${local.getValue('contact')}:</th>
            </tr>
            <tr>
                <td>${user.surname}</td>
                <td>${user.name}</td>
                <td>${user.address}</td>
                <td>${user.birthday}</td>
            </tr>
        </table>
    </div>

    <div class="viewst">
        <table>
            <tr>
                <th>${local.getValue('contacts')}:</th>
            </tr>
            <tr>
                <td>${local.getValue('mail')}</td>
                <td>${user.getContact(contTypes[0])}</td>
            </tr>
            <tr>
                <td>${local.getValue('phone')}</td>
                <td>${user.getContact(contTypes[1])}</td>
            </tr>
            <tr>
                <td>${local.getValue('home.phone')}</td>
                <td>${user.getContact(contTypes[2])}</td>
            </tr>
            <tr>
                <td>${local.getValue('skype')}</td>
                <td>${user.getContact(contTypes[3])}</td>
            </tr>
            <tr>
                <td>${local.getValue('icq')}</td>
                <td>${user.getContact(contTypes[4])}</td>
            </tr>
            <tr>
                <td>${local.getValue('mobile')}</td>
                <td>${user.getContact(contTypes[5])}</td>
            </tr>
            <tr>
                <td>${local.getValue('group')}:</td>
                <td>${user.stringGroups()}</td>
            </tr>
        </table>
        <p>&nbsp;</p>
        <input id="add" onclick="window.history.back()" value="${local.getValue('cancel')}">
    </div>
</center>
</body>
</html>
