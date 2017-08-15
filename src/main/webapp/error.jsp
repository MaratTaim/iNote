<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="styles/error.css" type="text/css"/>
</head>
<body style="background-image: url('/img/error_page.png')">
<c:set var="local" value="${sessionScope.local}"/>
<div id="main">
    <p class="text">Oops,<br/> something went wrong.</p>
    <p class="text">${wrongAction}</p>
    <c:if test="${pageContext.exception != null}">
        <p class="small">Request from ${pageContext.errorData.requestURI} is failed</p>
        <p class="small">Status code: ${pageContext.errorData.statusCode}</p>
        <p class="small">Exception: ${pageContext.exception}</p>
        <p class="small">Message from exception: ${pageContext.exception.message}</p>
    </c:if>
    <a id="error_page" onclick="window.history.back()">RETRY</a>
</div>

</body>
</html>
