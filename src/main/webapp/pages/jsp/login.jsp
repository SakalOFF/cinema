<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.login"/></title>
    <link rel="stylesheet" href="/pages/css/login.css" type="text/css">
    <link rel="stylesheet" href="/pages/css/header.css" type="text/css">
</head>

<body>

<jsp:include page="header.jsp"/>

<c:if test="${invalidData}">
    <div>
        <div class="message">
            <p><fmt:message key="message.error.invalidData"/></p>
        </div>
    </div>
</c:if>

<form action="${requestScope['javax.servlet.forward.request_uri']}?${requestScope['javax.servlet.forward.query_string']}" method="post" class="my_form">
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
        <fmt:message key="button.login" var="buttonText"/>
        <div style="text-align: center;"><input class="button" type="submit" value="${buttonText}"/></div>
    </div>
</form>
</body>

</html>