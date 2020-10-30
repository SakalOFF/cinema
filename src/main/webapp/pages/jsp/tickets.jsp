<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.purchase"/></title>
    <link rel="stylesheet" href="/pages/css/header.css">
    <link rel="stylesheet" href="/pages/css/tickets.css">
    <script src="https://yastatic.net/jquery/3.3.1/jquery.min.js"></script>
    <script>
        const seatsInARow = ${seatsInARow}
        const bookedSeats = ${bookedSeats}
        const admin = ${admin}
        const filmSessionId = ${filmSession}
    </script>
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="container">
        <div class="row">
            <div class="seatsSchema">
                <a class="screen"><fmt:message key="screen"/></a>
                <c:forEach begin="1" end="${rowsQuantity}" var="i">
                    <div class="row" id="${i}">
                        <div class="row-number">
                            <span>${i}</span>
                        </div>
                        <c:forEach begin="1" end="${seatsInARow}" var="j">
                            <a class="seat" onclick="chooseSeat(this)" state="1" id="${i}-${j}">${j}</a>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${!admin}">
                <div class="buttonContainer">
                    <c:if test="${message}">
                        <span><fmt:message key="message.error.buy"/></span>
                    </c:if>
                    <a class="buyButton" onclick="buyTickets()"><fmt:message key="button.purchase"/></a>
                </div>
            </c:if>
        </div>
    </div>

    <script src="/pages/js/tickets.js"></script>
</body>
</html>