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
        <%-- ADD FUNDS --%>
        <div>
            <c:url value="/profile/addFunds" var="postPath"/>
            <form:form class="form" modelAttribute="addFundsForm" action="${postPath}" method="post">

                <form:label class="label" path="funds">
                    <spring:message code="add_funds"/>
                </form:label>
                <form:input type="text" path="funds" />
                <form:errors class="error" path="funds" element="p"><p/><spring:message
                        code="funds_error"/></form:errors>

                <div>
                    <button type="submit"><spring:message code="accept"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
