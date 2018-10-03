<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
</head>
<body>

<c:url value="/authentication" var="authenticationPath"/>
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

    <div class="small-container">
        <h1><spring:message code="authenticate"/></h1>
        <br>
        <form:form class="form" modelAttribute="authenticationForm" action="${authenticationPath}" method="post">
            <div class="form-group">
                <form:label class="label" path="code"><spring:message code="code"/></form:label>
                <form:input path="code" type="text" class="form-control"/>
                <form:errors path="code" element="p"><br><spring:message
                        code="code_error"/></form:errors>
            </div>
            <div>
                <a href="<c:url value="/index"/>" class="btn-lg btn"><spring:message code="cancel"/></a>
                <button class="btn-lg btn btn-primary" type="submit"><spring:message code="validate"/></button>
            </div>
            <br>
            <div>
                <a href="<c:url value="/login"/> "><spring:message code="login_redirect"/></a><br>
                <a href="<c:url value="/signUp"/>"><spring:message code="signup_redirect"/></a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>