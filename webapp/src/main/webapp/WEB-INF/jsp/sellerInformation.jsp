<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

    <div class="purchase">
        <h1><spring:message code="successfully_purchase" /></h1>
        <div>
            <h2><spring:message code="seller_information" /></h2>
            <h3><spring:message code="username_" />
            <c:out value="${sellerUser.username}" /></h3>
            <h3><spring:message code="phone_" />
            <c:out value="${sellerUser.phone}" /></h3>
            <h3><spring:message code="email_" />
            <c:out value="${sellerUser.email}" /></h3>
        </div>

        <br>
        <a class="btn-lg btn btn-primary" role="button" href="<c:url value="/index" />">
            <spring:message code="accept"/>
        </a>
    </div>

</div>

</body>
</html>
