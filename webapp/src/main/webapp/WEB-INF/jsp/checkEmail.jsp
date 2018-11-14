<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
    <%@ include file="favicon.jsp" %>
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

    <div class="buy-container">
        <h1><spring:message code="check_email"/></h1>
        <br>
        <div>
            <h4><spring:message code="email_description"/></h4>
        </div>
        <div>
            <br>
            <br>
            <a class="btn btn-primary" href="<c:url value="/index"/>"><spring:message code="go_back"/></a>
        </div>
    </div>
</div>
</body>
</html>