<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">

<head>
	<meta charset="UTF-8">
	<title><fmt:message key="title.home"/></title>
	<link rel="stylesheet" href="/pages/css/main_page.css" type="text/css">
	<link rel="stylesheet" href="/pages/css/header.css" type="text/css">
	<script>
		const todayId = ${todayId}
		const loggedIn = ${loggedIn}
	</script>
</head>

<body>
    <jsp:include page="header.jsp"/>

	<div class="control-panel">
		<c:if test="${loggedIn && !admin}">
			<label class="checkbox-container">
				<input type="checkbox" class="filter" value="true" onchange="filter()"/>
				<fmt:message key="checkbox.text"/>
			</label>
		</c:if>

		<div class="days-pagination">
			<c:forEach items="${days}" var="day">
				<a class="button" href="/cinema?day=${day.id}" state="1"><fmt:message key="${day.name}"/></a>
			</c:forEach>
		</div>

		<c:if test="${loggedIn}">
			<label class="sortingLabel"><fmt:message key="label.sorting"/> </label>
			<label>
				<select class="field" onchange="sorting(this)">
					<c:forEach items="${sortingOptions}" var="option">
						<option value="${option.value}"><fmt:message key="${option.name().toLowerCase()}"/></option>
					</c:forEach>
				</select>
			</label>
		</c:if>
	</div>

	<div class="d-table">
		<c:choose>
			<c:when test="${sessions.isEmpty()}">
				<div class="message"><fmt:message key="message.emptySchedule"/></div>
			</c:when>

			<c:otherwise>
				<c:forEach items="${sessions}" var="row">
					<div class="d-tr">
						<c:forEach items="${row}" var="filmSession">
							<div class="d-td">
								<div class="card">
									<c:choose>
										<c:when test="${loggedIn}">
											<fmt:message key="message.showOnly" var="showOnlyTitle"/>
											<a href="/cinema?day=${filmSession.dayId}&film=${filmSession.film.id}"
											   title="${showOnlyTitle} &quot${filmSession.film.title}&quot">
												<img src="/pages${filmSession.film.imagePath}">
											</a>
											<fmt:message key="message.buy" var="buyMessage"/>
											<a class="title" href="/cinema/tickets/${filmSession.id}"
											   title="${buyMessage}">${filmSession.film.title}</a>
										</c:when>

										<c:otherwise>
											<a>
												<img src="/pages${filmSession.film.imagePath}">
											</a>
											<span class="title">${filmSession.film.title}</span>
										</c:otherwise>
									</c:choose>
									<span><fmt:formatDate value="${filmSession.startTime}" pattern="HH:mm"/></span>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<script src="/pages/js/main.js"></script>
</body>

</html>