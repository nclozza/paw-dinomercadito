<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
</head>
<body>
<c:url value="/changePassword" var="postPath"/>
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

    <div class="change-password-container">
        <h1><spring:message code="change_password"/></h1>
        <br>
        <form:form modelAttribute="changePassword" action="${postPath}" method="post" autocomplete="off">
            <div>
                <form:hidden path="code" value="${code}"/>
            </div>
            <div class="form-group">
                <form:label class="label" path="password"><spring:message code="password"/></form:label>
                <form:input type="password" path="password" class="form-control"/>
                <form:errors class="error" path="password" element="p"><br><spring:message
                        code="username_error"/><br></form:errors>
            </div>
            <div class="form-group">
                <form:label class="label" path="repeatPassword"><spring:message code="repeat_password"/></form:label>
                <form:input type="password" path="repeatPassword" class="form-control"/>
                <br>
                <c:if test="${different_password}">
                    <p><spring:message code="repeat_password_error"/></p>
                </c:if>
                <c:if test="${invalid_code}">
                    <p><spring:message code="invalid_code"/></p>
                </c:if>
            </div>
            <div>
                <a class="left-button btn" href="<c:url value="/index"/>"><spring:message code="cancel"/></a>
                <input type="submit" class="btn btn-primary"
                       value="<spring:message code="change_password"/>"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>