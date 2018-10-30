<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/question.css'/>">
</head>
<body>
<div class="central-wrapper">
    <!-- Navbar -->
    <c:choose>
        <c:when test="${loggedIn}">
            <%@ include file="navbarLogout.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="navbarLogin.jsp" %>
        </c:otherwise>
    </c:choose>

    <div class="questions-center">
        <div class="return">
            <a class="btn btn-primary" href="<c:url value="/post?filter=${filter}&&profile=${profile}&&postId=${postId}"/>"><spring:message code="go_back"/></a>
        </div>
        <h1 class="title"><spring:message code="questions"/></h1>
        <div class="questions">
            <c:choose>
                <c:when test="${empty questions}">
                    <br/>
                    <br/>
                    <p class="label"><spring:message code="no_questions_available"/></p>
                    <br/>
                    <br/>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${questions}" var="question" varStatus="loop">
                        <div class="question">
                            <p class="label">${question.userWhoAsk.username}: </p><p class="value"><c:out value="${question.question}"/></p>
                            <br>
                            <p class="label">${question.post.user.username}: </p><p class="value"><c:out value="${question.answer}"/></p>
                            <br>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <a class="btn btn btn-success button-margin button-position" role="button" href="<c:url value="/question?filter=${filter}&&profile=${profile}&&postId=${postId}" />">
            <spring:message code="ask"/>
        </a>
    </div>
</div>
</body>
</html>
