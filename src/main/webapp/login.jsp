<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>iNote.com</title>
    <link rel="stylesheet" href="../../styles/style2.css" type="text/css" />
</head>
<body>
<fmt:setBundle basename="lang" var="language"/>

<div id="login">
    <form action="inote" method="post">
        <input type="hidden" name="command" value="login"/>
        <fieldset class="clearfix">
            <p><span class="fontawesome-user"></span><input type="text" name="login" value="${param.login}"
                            maxlength="25" required placeholder="<fmt:message key="login" bundle="${language}"/>"></p>
            <p><span class="fontawesome-lock"></span><input type="password" name="password" value="${param.password}"
                            maxlength="45" required placeholder="<fmt:message key="pass" bundle="${language}"/>"></p>
            <p><input type="submit" value="<fmt:message key="insert" bundle="${language}"/>"></p>
        </fieldset>
        <c:if test="${not empty errorLoginPass}">
            <h6 style="color: #d44179"><fmt:message key="error.login" bundle="${language}"/></h6>
        </c:if>
    </form>
    <p><fmt:message key="no.account" bundle="${language}"/> &nbsp;&nbsp;
        <a href="register.jsp"><fmt:message key="register" bundle="${language}"/></a><span class="fontawesome-arrow-right"></span></p>
</div>
</body>
</html>
