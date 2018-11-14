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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/index.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/products.css'/>">
    <%@ include file="favicon.jsp" %>
</head>
<body>

<div class="first-container">
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
    <div class="container-fluid full-height text-center bg-1">
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
                                <c:choose>
                                    <c:when test="${error}">
                                        <input path="search" name="search" type="text" class="searchTerm form-control"
                                               placeholder="<spring:message code="wrong_input"/>">
                                    </c:when>
                                    <c:otherwise>
                                        <input path="search" name="search" type="text" class="searchTerm form-control"
                                               placeholder="<spring:message code="looking_for"/>">
                                    </c:otherwise>
                                </c:choose>
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
    <!-- Most visited posts -->
    <div class="container">
        <h1 class="carousel-title" ><spring:message code="top_visited"/></h1>
        <div id="postCarousel" class="carousel slide carousel-container" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <c:forEach items="${posts}" var="product" varStatus="loop">
                    <li data-target="#myCarousel" data-slide-to="${loop.index + 1}"></li>
                </c:forEach>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                    <a href="<c:url value="/post?postId=${firstPost.postId}"/>">
                    <img class="product img-responsive"
                         src="<c:url value="/images/${firstPost.productPosted.productName}.png"/>"
                         alt="${firstPost.productPosted.productName}">
                    </a>
                    <div class="carousel-caption">
                        <h2>${firstPost.productPosted.productName}</h2>
                        <h3>${firstPost.visits} <spring:message code="visits_"/></h3>
                    </div>
                </div>
                <c:forEach items="${posts}" var="post" varStatus="loop">
                    <div class="item">
                        <a href="<c:url value="/post?postId=${post.postId}"/>">
                        <img class="product img-responsive"
                             src="<c:url value="/images/${post.productPosted.productName}.png" />"
                             alt="${post.productPosted.productName}">
                        </a>
                        <div class="carousel-caption">
                            <h2>${post.productPosted.productName}</h2>
                            <h3>${post.visits} <spring:message code="visits_"/></h3>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#postCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#postCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</body>
</html>
