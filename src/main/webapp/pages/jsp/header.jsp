<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<div class="header">
    <a href="/cinema" class="logo">KinOFF</a>
    <c:if test="${loggedIn}">
        <a href="/cinema/logout" class="red_button"><fmt:message key="button.logout"/></a>
    </c:if>
    <c:if test="${!loggedIn && !logIn}">
        <a href="/cinema/login" class="red_button"><fmt:message key="button.login"/></a>
    </c:if>
    <c:if test="${logIn}">
        <a href="/cinema/registration" class="red_button"><fmt:message key="button.register"/></a>
    </c:if>
    <c:if test="${admin}">
        <a href="/cinema/redactor" class="red_button redactor"><fmt:message key="button.redactor"/></a>
    </c:if>
    <select class="locale" onchange="changeLocale(this.options[this.selectedIndex])">
        <option value="en"><fmt:message key="lang.english"/></option>
        <option value="ukr"><fmt:message key="lang.ukrainian"/></option>
    </select>
    <c:if test="${admin}">
        <a class="adminLabel">admin</a>
    </c:if>
    <script>
        const currentLocale = '${pageContext.response.locale}'
    </script>
    <script src="/pages/js/header.js"></script>
</div>
