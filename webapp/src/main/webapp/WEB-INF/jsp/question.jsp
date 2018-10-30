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
<c:url value="/question" var="postPath"/>
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

    <div class="ask-container">
        <h1><spring:message code="new_question"/></h1>
        <br>
        <form:form modelAttribute="question" action="${postPath}" method="post" autocomplete="off">
            <div class="form-group">
                <form:hidden path="postId" value="${post.postId}"/>
                <form:hidden path="filter" value="${filter}"/>
                <form:hidden path="profile" value="${profile}"/>
                <form:textarea path="question" class="form-control input-size"/>
                <form:errors class="error" path="question" element="p"><br><spring:message
                        code="question_error"/><br></form:errors>
                <br/>
                <c:if test="${same_user_question}">
                    <p><spring:message code="same_user_question_error"/></p>
                </c:if>
                <br/>
            </div>
            <div>
                <a class="left-button btn" href="<c:url value="/post?filter=${filter}&&postId=${post.postId}&&profile=${profile}"/>"><spring:message code="cancel"/></a>
                <input type="submit" class="btn btn-primary"
                       value="<spring:message code="ask"/>"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
