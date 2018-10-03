<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">

</head>
<body>
<div class="central-wrapper">
    <c:url value="/login" var="loginUrl"/>
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
            <h1><spring:message code="login"/></h1>
            <form class="form" action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
                <div class="form-group">
                    <label class="label" for="username"><spring:message code="username"/></label>
                    <input id="username" name="j_username" type="text" class="form-control"/>
                </div>
                <div class="form-group">
                    <label class="label" for="password"><spring:message code="password"/></label>
                    <input id="password" name="j_password" type="password" class="form-control"/>
                </div>
                <div class="checkbox">
                    <label class="checkbox-label">
                        <input name="j_rememberme" type="checkbox"/>
                        <spring:message code="remember_me"/>
                    </label>
                </div>
                <br>
                <div>
                    <button class="btn-lg btn btn-primary" type="submit"><spring:message code="submit"/></button>
                    <button class="btn-lg btn btn-danger"><spring:message code="cancel"/></button>
                </div>
                <br>
                <div>
                    <a href="#"><spring:message code="signup_redirect"/></a><br>
                    <a href="#"><spring:message code="auth_redirect"/></a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>