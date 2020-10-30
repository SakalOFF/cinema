<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Додати сеанс</title>
    <link rel="stylesheet" href="/pages/css/header.css">
    <link rel="stylesheet" href="/pages/css/login.css">
</head>
<body>

    <jsp:include page="header.jsp"/>

    <c:if test="${error}">
        <div>
            <div class="message">
                <p><fmt:message key="message.error.invalidInput"/></p>
            </div>
        </div>
    </c:if>
    <form method="post" class="my_form">
        <div class="group">
            <label>
                День:
                <select name="dayId">
                    <c:forEach items="${days}" var="day">
                        <option value="${day.id}"><fmt:message key="${day.name}"/></option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="group">
            <label>
                Фільм:
                <select name="filmId">
                    <c:forEach items="${films}" var="film">
                        <option value="${film.id}">${film.title}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="group">
            <label>
                Час початку:
                <input name="startTime">
            </label>
        </div>
        <div class="group">
            <fmt:message key="button.addSession" var="addSessionText"/>
            <div style="text-align: center;"><input class="button" type="submit" value="${addSessionText}"/></div>
        </div>
    </form>

</body>
</html>