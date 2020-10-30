<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.registration"/></title>
    <link rel="stylesheet" href="/pages/css/login.css" type="text/css">
    <link rel="stylesheet" href="/pages/css/header.css" type="text/css">
</head>

<body>

<jsp:include page="header.jsp"/>

<c:if test="${invalidData || userExists}">
    <div class="message">
        <p>
            <c:choose>
            <c:when test="${invalidData}">
                <fmt:message key="message.error.invalidInput"/>
            </c:when>
            <c:when test="${userExists}">
                <fmt:message key="message.error.userExists"/>
            </c:when>
            </c:choose>
        </p>
    </div>
</c:if>

<form action="/cinema/registration" method="post" class="my_form">
    <div class="group">
        <label><fmt:message key="login"/>:</label>
        <label>
            <input type="text" name="username">
        </label>
    </div>
    <div class="group">
        <label><fmt:message key="password"/>:</label>
        <label>
            <input type="password" name="password">
        </label>
    </div>
    <div class="group">
        <fmt:message key="button.register" var="buttonText"/>
        <div style="text-align: center;"><input class="button" type="submit" value="${buttonText}"/></div>
    </div>
</form>
</body>

</html>