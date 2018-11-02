<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
</head>
<body>
<c:url value="/forgotPassword" var="postPath"/>
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

    <div class="buy-container">
        <h1><spring:message code="enter_email"/></h1>
        <br>
        <form:form modelAttribute="forgotPassword" action="${postPath}" method="post" autocomplete="off">
        <div class="form-group">
            <form:input type="text" path="email" class="form-control"/>
            <form:errors class="error" path="email" element="p"><br><spring:message
                    code="email_error"/><br></form:errors>
            <br/>
            <c:if test="${wrong_email}">
                <p><spring:message code="email_not_found"/></p>
            </c:if>
            <br/>
        </div>
        <div>
            <a class="left-button btn" href="<c:url value="/login"/>"><spring:message code="cancel"/></a>
            <input type="submit" class="btn btn-primary" value="<spring:message code="send"/>"/>
        </div>
    </div>
    </form:form>
</div>
</body>
</html>

