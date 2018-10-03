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

        <div class="add-funds-container">
            <h1><spring:message code="show_add_funds"/></h1>
            <c:url value="/profile/addFunds" var="postPath"/>
            <form:form class="form" modelAttribute="addFundsForm" action="${postPath}" method="post">
                <div class="form-group">
                    <form:label class="label" path="funds">
                    </form:label>
                    <form:input type="text" path="funds" class="form-control add-funds-input"/>
                    <form:errors class="error" path="funds" element="p"><br><spring:message
                            code="funds_error"/></form:errors>
                </div>
                <div>
                    <button class="btn-lg btn"><spring:message code="cancel"/></button>
                    <button type="submit" class="btn-lg btn btn-primary"><spring:message code="accept"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
