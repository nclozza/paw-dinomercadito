<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/question.css'/>">
</head>
<body>
<c:url value="/answer" var="postPath"/>
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

    <div class="answer-container">
        <h1><spring:message code="new_question"/></h1>
        <br>
        <form:form modelAttribute="question" action="${postPath}" method="post" autocomplete="off">
            <div class="form-group">
                <form:hidden path="questionId" value="${question.questionId}"/>
                <h3><spring:message code="question_"/><c:out value="${question.question}"/></h3>
                <form:textarea path="answer" class="form-control input-size"/>
                <form:errors class="error" path="answer" element="p"><br><spring:message
                        code="question_error"/><br></form:errors>
                <br>
                <c:if test="${answer_error}">
                    <spring:message code="answer_error"/>
                </c:if>
                <br/>
                <br/>
            </div>
            <div>
                <a class="left-button btn" href="<c:url value="/profile"/>"><spring:message code="cancel"/></a>
                <input type="submit" class="btn btn-primary"
                       value="<spring:message code="answer"/>"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
