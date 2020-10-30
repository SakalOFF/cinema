<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.redactor"/></title>
    <link rel="stylesheet" href="/pages/css/redactor.css">
    <link rel="stylesheet" href="/pages/css/header.css">
    <script src="https://yastatic.net/jquery/3.3.1/jquery.min.js"></script>
    <script>
        const todayId = ${todayId}
    </script>
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="control-panel">
        <div class="days-pagination">
            <c:forEach items="${days}" var="day">
                <a href="/cinema/redactor?day=${day.id}" state="1"><fmt:message key="${day.name}"/></a>
            </c:forEach>
        </div>
    </div>
    <div class="d-table">
        <c:forEach items="${sessions}" var="row">
            <div class="d-tr">
                <c:forEach items="${row}" var="filmSession">
                    <div class="d-td">
                        <c:choose>
                            <c:when test="${filmSession != null}">
                                <div class="card closeable" sessionId="${filmSession.id}">
                                    <div class="close" onclick="deleteSession(this.parentElement.getAttribute('sessionId'))"></div>
                                    <a>
                                        <img src="/pages/${filmSession.film.imagePath}" alt="">
                                    </a>
                                    <span class="title">${filmSession.film.title}"</span>
                                    <span><fmt:formatDate value="${filmSession.startTime}" pattern="HH:mm"/></span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="card buttonContainer" onclick="document.location.href = '/cinema/sessions'">
                                    <div class="addButton"><fmt:message key="button.addSession"/></div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <script src="/pages/js/redactor.js"></script>
</body>
</html>