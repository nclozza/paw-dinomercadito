<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/index.css'/>">

</head>
<body>

<div class="first-container bg-1">
    <!-- Navbar -->
    <c:choose>
        <c:when test="${loggedIn}">
            <%@ include file="navbarLogout.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="navbarLogin.jsp" %>
        </c:otherwise>
    </c:choose>

    <!-- First Container -->
    <div class="container-fluid full-height text-center ">
        <div class="vertical-align">
            <div class="horizontal-align">
                <h1><spring:message code="search_products"/></h1>
                <h4><spring:message code="products_types"/></h4>
                <div class="wrap">
                    <c:url value="/index" var="searchUrl"/>
                    <form class="form" action="${searchUrl}" method="post"
                          enctype="application/x-www-form-urlencoded">
                        <div class="row">
                            <div class="input-group">
                                <input path="search" name="search" type="text" class="searchTerm form-control"
                                       placeholder="<spring:message code="looking_for"/>">
                                <span class="input-group-btn">
                                        <button type="submit" class="btn btn-default">
                                            <spring:message code="search"/>
                                        </button>
                                    </span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
