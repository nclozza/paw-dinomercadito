<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>DinoMercadito | Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile.css'/>">

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

    <div>
        <spring:message code="successfully_purchase" />

        <div>
            <spring:message code="seller_information" />
            <spring:message code="username_" />
            <c:out value="${sellerUser.username}" />
            <spring:message code="phone_" />
            <c:out value="${sellerUser.phone}" />
            <spring:message code="email_" />
            <c:out value="${sellerUser.email}" />
        </div>

        <a class="btn btn-info" role="button" href="<c:url value="/index" />">
            <spring:message code="accept"/>
        </a>
    </div>

</div>

</body>
</html>
